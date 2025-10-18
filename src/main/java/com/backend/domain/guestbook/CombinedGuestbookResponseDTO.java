package com.backend.domain.guestbook;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedGuestbookResponseDTO {
  private String id; // DB의 경우 guestbookId, Redis의 경우 sessionId
  private String guestname;
  private String message;
  private LocalDateTime createdAt;
  private boolean isTemporary; // true: Redis 임시 피드백, false: DB 저장된 피드백
  private String sessionId; // Redis 피드백의 경우만 값 존재
}
