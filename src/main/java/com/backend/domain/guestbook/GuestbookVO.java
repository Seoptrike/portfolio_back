package com.backend.domain.guestbook;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GuestbookVO {
  private int guestbookId;
  private int guestId;
  private int hostId;
  private String message;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
