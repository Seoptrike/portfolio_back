package com.backend.domain.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
  private int result;
  private String token;
}
