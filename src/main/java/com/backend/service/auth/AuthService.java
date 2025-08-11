package com.backend.service.auth;

import com.backend.domain.auth.LoginResponseDTO;
import com.backend.domain.user.UsersVO;

public interface AuthService {
  LoginResponseDTO login(String username, String password);

  void register(UsersVO vo);
}
