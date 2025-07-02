package com.backend.service.auth;

import com.backend.domain.LoginResponseDTO;
import com.backend.domain.UsersVO;

public interface AuthService {
    LoginResponseDTO login(String username, String password);

    void register(UsersVO vo);
}
