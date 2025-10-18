package com.backend.domain.guestbook;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GuestbookResponseDTO {
  private int guestbookId;
  private int guestId;
  private int hostId;
  private String message;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String hostname;
  private String guestname;
}
