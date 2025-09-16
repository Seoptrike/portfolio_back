package com.backend.domain.guestbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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