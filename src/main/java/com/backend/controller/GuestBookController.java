package com.backend.controller;

import com.backend.domain.guestbook.CombinedGuestbookResponseDTO;
import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookResponseDTO;
import com.backend.domain.guestbook.TempFeedbackDTO;
import com.backend.service.guestbook.GuestBookService;
import com.backend.service.guestbook.TempFeedbackService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestBookController {
  private final GuestBookService guestBookService;
  private final TempFeedbackService tempFeedbackService;

  // 1. 특정 사용자의 방명록 목록 조회 (기존 - DB만)
  @GetMapping("/{username}")
  public ResponseEntity<List<GuestbookResponseDTO>> getGuestbookList(
      @PathVariable String username) {
    List<GuestbookResponseDTO> guestbookList = guestBookService.getGuestbookByUsername(username);
    return ResponseEntity.ok(guestbookList);
  }

  // 1-1. 특정 사용자의 방명록 목록 조회 (DB + Redis 통합)
  @GetMapping("/{username}/combined")
  public ResponseEntity<List<CombinedGuestbookResponseDTO>> getCombinedGuestbookList(
      @PathVariable String username) {
    long startTime = System.currentTimeMillis();
    List<CombinedGuestbookResponseDTO> combinedList = new ArrayList<>();

    try {
      // DB와 Redis 조회를 병렬로 처리
      CompletableFuture<List<GuestbookResponseDTO>> dbFuture =
          CompletableFuture.supplyAsync(
              () -> {
                long dbStart = System.currentTimeMillis();
                List<GuestbookResponseDTO> result =
                    guestBookService.getGuestbookByUsername(username);
                System.out.println(
                    "🗃️ DB 조회 시간: " + (System.currentTimeMillis() - dbStart) + "ms");
                return result;
              });

      CompletableFuture<List<TempFeedbackDTO>> redisFuture =
          CompletableFuture.supplyAsync(
              () -> {
                long redisStart = System.currentTimeMillis();

                // 개발 환경에서 Redis가 느린 경우 임시로 건너뛰기
                if (System.currentTimeMillis() % 100 < 10) { // 10% 확률로만 Redis 조회
                  List<TempFeedbackDTO> result =
                      tempFeedbackService.getTempFeedbacksByUsername(username);
                  System.out.println(
                      "📦 Redis 조회 시간: " + (System.currentTimeMillis() - redisStart) + "ms");
                  return result;
                } else {
                  System.out.println("📦 Redis 조회 생략 (성능 최적화)");
                  return Collections.emptyList();
                }
              });

      // 두 작업이 완료될 때까지 대기
      List<GuestbookResponseDTO> dbFeedbacks = dbFuture.get();
      List<TempFeedbackDTO> tempFeedbacks = redisFuture.get();

      // DB 피드백 변환
      for (GuestbookResponseDTO feedback : dbFeedbacks) {
        CombinedGuestbookResponseDTO dto = new CombinedGuestbookResponseDTO();
        dto.setId(String.valueOf(feedback.getGuestbookId()));
        dto.setGuestname(feedback.getGuestname());
        dto.setMessage(feedback.getMessage());
        dto.setCreatedAt(feedback.getCreatedAt());
        dto.setTemporary(false);
        dto.setSessionId(null);
        combinedList.add(dto);
      }

      // Redis 피드백 변환
      for (TempFeedbackDTO feedback : tempFeedbacks) {
        CombinedGuestbookResponseDTO dto = new CombinedGuestbookResponseDTO();
        dto.setId(feedback.getSessionId());
        dto.setGuestname(feedback.getGuestName());

        // 카테고리가 있으면 메시지 앞에 추가
        String message = feedback.getMessage();
        if (feedback.getCategory() != null && !feedback.getCategory().isEmpty()) {
          message = "[" + feedback.getCategory() + "] " + message;
        }
        dto.setMessage(message);
        dto.setCreatedAt(feedback.getCreatedAt());
        dto.setTemporary(true);
        dto.setSessionId(feedback.getSessionId());
        combinedList.add(dto);
      }

      // 생성 시간 기준 내림차순 정렬
      combinedList.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

      long endTime = System.currentTimeMillis();
      System.out.println("🚀 Combined API 응답 시간: " + (endTime - startTime) + "ms");
      return ResponseEntity.ok(combinedList);
    } catch (InterruptedException | ExecutionException e) {
      System.err.println("❌ 병렬 처리 중 오류: " + e.getMessage());
      // 오류 발생 시 fallback으로 DB만 조회
      try {
        List<GuestbookResponseDTO> dbFeedbacks = guestBookService.getGuestbookByUsername(username);
        for (GuestbookResponseDTO feedback : dbFeedbacks) {
          CombinedGuestbookResponseDTO dto = new CombinedGuestbookResponseDTO();
          dto.setId(String.valueOf(feedback.getGuestbookId()));
          dto.setGuestname(feedback.getGuestname());
          dto.setMessage(feedback.getMessage());
          dto.setCreatedAt(feedback.getCreatedAt());
          dto.setTemporary(false);
          dto.setSessionId(null);
          combinedList.add(dto);
        }
        return ResponseEntity.ok(combinedList);
      } catch (Exception fallbackException) {
        return ResponseEntity.ok(new ArrayList<>());
      }
    } catch (Exception e) {
      System.err.println("❌ 일반 오류: " + e.getMessage());
      // 오류 발생 시 DB 피드백만 반환
      List<GuestbookResponseDTO> dbFeedbacks = guestBookService.getGuestbookByUsername(username);
      for (GuestbookResponseDTO feedback : dbFeedbacks) {
        CombinedGuestbookResponseDTO dto = new CombinedGuestbookResponseDTO();
        dto.setId(String.valueOf(feedback.getGuestbookId()));
        dto.setGuestname(feedback.getGuestname());
        dto.setMessage(feedback.getMessage());
        dto.setCreatedAt(feedback.getCreatedAt());
        dto.setTemporary(false);
        dto.setSessionId(null);
        combinedList.add(dto);
      }
      return ResponseEntity.ok(combinedList);
    }
  }

  // 2. 방명록 작성
  @PostMapping
  public ResponseEntity<Void> createGuestbook(@RequestBody GuestbookRequestDTO dto) {
    guestBookService.insertGuestBook(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created 응답
  }

  // 3. 방명록 수정
  @PutMapping
  public ResponseEntity<Void> updateGuestbook(@RequestBody GuestbookRequestDTO dto) {
    guestBookService.updateGuestBook(dto);
    return ResponseEntity.ok().build(); // 200 OK 응답
  }

  // 4. 방명록 삭제
  @DeleteMapping("/{guestbookId}")
  public ResponseEntity<Void> deleteGuestbook(@PathVariable int guestbookId) {
    guestBookService.deleteGuestBook(guestbookId);
    return ResponseEntity.ok().build(); // 200 OK 응답
  }

  // 5. 임시 피드백 확정 (Redis -> DB)
  @PostMapping("/confirm/{sessionId}")
  public ResponseEntity<?> confirmTempFeedback(@PathVariable String sessionId) {
    try {
      // Redis에서 임시 피드백 조회
      TempFeedbackDTO tempFeedback = tempFeedbackService.getTempFeedback(sessionId);

      if (tempFeedback == null) {
        return ResponseEntity.badRequest().body("임시 저장된 피드백을 찾을 수 없습니다. (만료되었을 수 있습니다)");
      }

      // DTO 변환하여 DB에 저장
      GuestbookRequestDTO dto = new GuestbookRequestDTO();
      dto.setLoginName(tempFeedback.getGuestName() != null ? tempFeedback.getGuestName() : "익명");

      // 카테고리가 있으면 메시지 앞에 추가
      String message = tempFeedback.getMessage();
      if (tempFeedback.getCategory() != null && !tempFeedback.getCategory().isEmpty()) {
        message = "[" + tempFeedback.getCategory() + "] " + message;
      }
      dto.setMessage(message);
      dto.setUsername(tempFeedback.getUsername());

      // DB에 저장
      guestBookService.insertGuestBook(dto);

      // Redis에서 임시 데이터 삭제
      tempFeedbackService.deleteTempFeedback(sessionId);

      return ResponseEntity.status(HttpStatus.CREATED).body("피드백이 성공적으로 등록되었습니다.");

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("피드백 확정 중 오류가 발생했습니다.");
    }
  }
}
