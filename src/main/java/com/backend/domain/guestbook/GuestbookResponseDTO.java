package com.backend.domain.guestbook;

import lombok.Data;

import java.time.LocalDateTime;

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
