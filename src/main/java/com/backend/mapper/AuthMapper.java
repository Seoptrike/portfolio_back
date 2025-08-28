package com.backend.mapper;

import com.backend.domain.user.UsersVO;

public interface AuthMapper {

  // username으로 사용자 조회
  UsersVO findByUsername(String username);

  // 회원가입
  void register(UsersVO user);

  void updateLastLogin(int userId);
}
