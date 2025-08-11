package com.backend.service.auth;

import com.backend.domain.auth.LoginResponseDTO;
import com.backend.domain.user.UsersVO;
import com.backend.mapper.AuthMapper;
import com.backend.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired private AuthMapper AuthMapper;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;
  @Autowired private AuthMapper authMapper;

  @Override
  public LoginResponseDTO login(String username, String password) {
    LoginResponseDTO res = new LoginResponseDTO();
    UsersVO user = AuthMapper.findByUsername(username);

    if (user == null) {
      res.setResult(0);
      return res;
    }
    if (!passwordEncoder.matches(password, user.getPassword())) {
      res.setResult(1);
      return res;
    }
    res.setResult(2);
    res.setToken(jwtUtil.generateToken(user));
    return res;
  }

  @Override
  public void register(UsersVO vo) {
    // 비밀번호 암호화
    String encodedPassword = passwordEncoder.encode(vo.getPassword());
    vo.setPassword(encodedPassword); // VO에 다시 세팅

    authMapper.register(vo); // 암호화된 비밀번호로 저장
  }

  @PostConstruct
  public void init() {
    System.out.println(">>> LoginMapper class: " + AuthMapper.getClass());
  }
}
