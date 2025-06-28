package com.backend.domain;

import java.time.LocalDateTime;

public class GuestbookVO {
    private int guestbook_id;
    private int user_id;
    private String message;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
