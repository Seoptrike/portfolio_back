package com.backend.controller;

import com.backend.domain.guestbook.TempFeedbackDTO;
import com.backend.service.guestbook.TempFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/temp-feedback")
@RequiredArgsConstructor
public class TempFeedbackController {

    private final TempFeedbackService tempFeedbackService;

    /**
     * 임시 피드백 저장
     */
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveTempFeedback(@RequestBody TempFeedbackDTO feedback) {
        Map<String, Object> response = new HashMap<>();

        try {
            String sessionId = tempFeedbackService.saveTempFeedback(feedback);

            response.put("success", true);
            response.put("sessionId", sessionId);
            response.put("message", "임시 저장되었습니다. (30분간 유효)");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("임시 피드백 저장 실패: {}", e.getMessage());

            response.put("success", false);
            response.put("message", "임시 저장에 실패했습니다.");

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 임시 피드백 조회
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<Map<String, Object>> getTempFeedback(@PathVariable String sessionId) {
        Map<String, Object> response = new HashMap<>();

        try {
            TempFeedbackDTO feedback = tempFeedbackService.getTempFeedback(sessionId);

            if (feedback == null) {
                response.put("success", false);
                response.put("message", "임시 저장된 피드백을 찾을 수 없습니다. (만료되었을 수 있습니다)");
                return ResponseEntity.notFound().build();
            }

            response.put("success", true);
            response.put("feedback", feedback);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("임시 피드백 조회 실패: {}", e.getMessage());

            response.put("success", false);
            response.put("message", "조회에 실패했습니다.");

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 임시 피드백 삭제
     */
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Map<String, Object>> deleteTempFeedback(@PathVariable String sessionId) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean deleted = tempFeedbackService.deleteTempFeedback(sessionId);

            response.put("success", deleted);
            response.put("message", deleted ? "삭제되었습니다." : "삭제할 데이터를 찾을 수 없습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("임시 피드백 삭제 실패: {}", e.getMessage());

            response.put("success", false);
            response.put("message", "삭제에 실패했습니다.");

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 임시 피드백 존재 여부 확인
     */
    @GetMapping("/{sessionId}/exists")
    public ResponseEntity<Map<String, Object>> checkTempFeedback(@PathVariable String sessionId) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean exists = tempFeedbackService.existsTempFeedback(sessionId);

            response.put("exists", exists);
            response.put("sessionId", sessionId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("임시 피드백 존재 확인 실패: {}", e.getMessage());

            response.put("exists", false);
            response.put("error", "확인에 실패했습니다.");

            return ResponseEntity.badRequest().body(response);
        }
    }
}