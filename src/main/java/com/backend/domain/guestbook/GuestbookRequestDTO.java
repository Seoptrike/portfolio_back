package com.backend.domain.guestbook;

import lombok.Data;

@Data
public class GuestbookRequestDTO {
  private String username; // urlname
  private int guestbookId;
  private String loginName; // loginname
  private String message;
  private Integer category; // 피드백 카테고리 (enum code)
}
