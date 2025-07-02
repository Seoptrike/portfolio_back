package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class GuestbookVO {
    private int guestbook_id;
    private int user_id;
    private String message;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
