package com.backend.domain.guestbook;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempFeedbackDTO {
  private String sessionId;
  private String guestName;
  private String category;
  private String message;
  private String username; // 포트폴리오 주인
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
}
