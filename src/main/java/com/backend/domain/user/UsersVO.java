package com.backend.domain.user;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsersVO {
  private int userId;
  private String username;
  private String password;
  private String phone;
  private String photo;
  private String photoUrlId;
  private String githubUrl;
  private int role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
