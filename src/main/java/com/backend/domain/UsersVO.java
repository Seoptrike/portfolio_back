package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UsersVO {
    private int user_id;
    private String username;
    private String password;
    private String phone;
    private String photo;
    private String github_url;
    private int role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
