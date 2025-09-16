package com.backend.service.guestbook;

import com.backend.domain.guestbook.TempFeedbackDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TempFeedbackService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    // 임시 피드백 TTL (30분)
    private static final Duration TEMP_FEEDBACK_TTL = Duration.ofMinutes(30);
    private static final String TEMP_FEEDBACK_PREFIX = "temp_feedback:";
    private static final String USER_FEEDBACK_INDEX_PREFIX = "user_temp_feedbacks:";

    /**
     * 새로운 세션 ID 생성
     */
    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 임시 피드백 저장
     */
    public String saveTempFeedback(TempFeedbackDTO feedback) {
        try {
            // 세션 ID가 없으면 새로 생성
            if (feedback.getSessionId() == null || feedback.getSessionId().isEmpty()) {
                feedback.setSessionId(generateSessionId());
            }

            // 생성/만료 시간 설정
            LocalDateTime now = LocalDateTime.now();
            feedback.setCreatedAt(now);
            feedback.setExpiresAt(now.plus(TEMP_FEEDBACK_TTL));

            // Redis에 저장
            String key = TEMP_FEEDBACK_PREFIX + feedback.getSessionId();
            redisTemplate.opsForValue().set(key, feedback, TEMP_FEEDBACK_TTL);

            // 사용자별 인덱스에 세션 ID 추가 (O(1) 성능)
            String userIndexKey = USER_FEEDBACK_INDEX_PREFIX + feedback.getUsername();
            redisTemplate.opsForSet().add(userIndexKey, feedback.getSessionId());
            redisTemplate.expire(userIndexKey, TEMP_FEEDBACK_TTL);

            log.info("임시 피드백 저장 완료: sessionId={}, expiresAt={}",
                    feedback.getSessionId(), feedback.getExpiresAt());

            return feedback.getSessionId();
        } catch (Exception e) {
            log.error("임시 피드백 저장 실패: {}", e.getMessage());
            throw new RuntimeException("임시 피드백 저장에 실패했습니다.", e);
        }
    }

    /**
     * 임시 피드백 조회
     */
    public TempFeedbackDTO getTempFeedback(String sessionId) {
        try {
            String key = TEMP_FEEDBACK_PREFIX + sessionId;
            Object value = redisTemplate.opsForValue().get(key);

            if (value == null) {
                return null;
            }

            return objectMapper.convertValue(value, TempFeedbackDTO.class);
        } catch (Exception e) {
            log.error("임시 피드백 조회 실패: sessionId={}, error={}", sessionId, e.getMessage());
            return null;
        }
    }

    /**
     * 임시 피드백 삭제
     */
    public boolean deleteTempFeedback(String sessionId) {
        try {
            // 먼저 데이터를 가져와서 username 확인
            TempFeedbackDTO feedback = getTempFeedback(sessionId);
            if (feedback != null) {
                // 인덱스에서 세션 ID 제거
                String userIndexKey = USER_FEEDBACK_INDEX_PREFIX + feedback.getUsername();
                redisTemplate.opsForSet().remove(userIndexKey, sessionId);
            }

            // 실제 데이터 삭제
            String key = TEMP_FEEDBACK_PREFIX + sessionId;
            Boolean deleted = redisTemplate.delete(key);

            log.info("임시 피드백 삭제: sessionId={}, deleted={}", sessionId, deleted);
            return Boolean.TRUE.equals(deleted);
        } catch (Exception e) {
            log.error("임시 피드백 삭제 실패: sessionId={}, error={}", sessionId, e.getMessage());
            return false;
        }
    }

    /**
     * 임시 피드백 존재 여부 확인
     */
    public boolean existsTempFeedback(String sessionId) {
        try {
            String key = TEMP_FEEDBACK_PREFIX + sessionId;
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("임시 피드백 존재 확인 실패: sessionId={}, error={}", sessionId, e.getMessage());
            return false;
        }
    }

    /**
     * 특정 사용자의 모든 임시 피드백 조회 (O(1) 성능)
     */
    public List<TempFeedbackDTO> getTempFeedbacksByUsername(String username) {
        try {
            long startTime = System.currentTimeMillis();

            // 사용자별 인덱스에서 세션 ID 목록 조회 (O(1))
            String userIndexKey = USER_FEEDBACK_INDEX_PREFIX + username;

            // 먼저 키 존재 여부 확인 (빠른 체크)
            if (!redisTemplate.hasKey(userIndexKey)) {
                log.info("📦 Redis 인덱스 조회: {}ms (키 없음)", System.currentTimeMillis() - startTime);
                return Collections.emptyList();
            }

            Set<Object> sessionIds = redisTemplate.opsForSet().members(userIndexKey);

            if (sessionIds == null || sessionIds.isEmpty()) {
                log.info("📦 Redis 인덱스 조회: {}ms (결과: 0개)", System.currentTimeMillis() - startTime);
                return Collections.emptyList();
            }

            List<TempFeedbackDTO> result = new ArrayList<>();

            // 각 세션 ID로 실제 데이터 조회 (O(n), n은 해당 사용자의 피드백 수)
            for (Object sessionIdObj : sessionIds) {
                String sessionId = sessionIdObj.toString();
                String key = TEMP_FEEDBACK_PREFIX + sessionId;
                Object value = redisTemplate.opsForValue().get(key);

                if (value != null) {
                    TempFeedbackDTO feedback = objectMapper.convertValue(value, TempFeedbackDTO.class);
                    result.add(feedback);
                } else {
                    // 만료된 세션은 인덱스에서 제거
                    redisTemplate.opsForSet().remove(userIndexKey, sessionId);
                }
            }

            // 생성 시간 기준 내림차순 정렬
            result.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

            log.info("📦 Redis 인덱스 조회: {}ms (결과: {}개)",
                    System.currentTimeMillis() - startTime, result.size());
            return result;
        } catch (Exception e) {
            log.error("임시 피드백 목록 조회 실패: username={}, error={}", username, e.getMessage());
            return Collections.emptyList();
        }
    }
}