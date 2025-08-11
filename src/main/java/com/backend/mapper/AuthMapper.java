package com.backend.mapper;

import com.backend.domain.user.UsersVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

  // 로그인 (username + password 일치 여부)
  UsersVO login(UsersVO user);

  // username으로 사용자 조회
  UsersVO findByUsername(String username);

  // 회원가입
  int register(UsersVO user);
}
