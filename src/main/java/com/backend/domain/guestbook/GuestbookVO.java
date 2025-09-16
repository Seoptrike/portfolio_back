package com.backend.domain.guestbook;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GuestbookVO {
  private int guestbookId;
  private Integer guestId;  // nullable로 변경
  private int hostId;
  private String message;
  private String guestName;  // 익명 사용자 이름
  private Integer category;   // 피드백 카테고리 (enum code)
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
