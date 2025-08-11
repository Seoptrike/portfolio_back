package com.backend.controller;

import com.backend.domain.auth.LoginRequestDTO;
import com.backend.domain.auth.LoginResponseDTO;
import com.backend.domain.user.UsersVO;
import com.backend.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto, HttpServletResponse response) {
    LoginResponseDTO res = authService.login(dto.getUsername(), dto.getPassword());

    if (res.getResult() != 2) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    // ✅ JWT를 쿠키로 내려보내기
    Cookie cookie = new Cookie("token", res.getToken());
    cookie.setHttpOnly(true);
    cookie.setSecure(true); // HTTPS 환경에서만 전송
    cookie.setPath("/");
    cookie.setMaxAge(60 * 60); // 1시간

    // 쿠키 추가
    response.addCookie(cookie);

    // 보안상 응답 본문에서는 토큰 제거 (선택)
    res.setToken(null);

    return ResponseEntity.ok(res);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UsersVO vo) {
    authService.register(vo);
    return ResponseEntity.ok().build(); // 또는 상태 메시지 반환 가능
  }

  @GetMapping("/loginCheck")
  public ResponseEntity<Map<String, Object>> loginCheck(Authentication auth) {
    Map<String, Object> result = new HashMap<>();

    if (auth == null || !auth.isAuthenticated()) {
      result.put("status", "GUEST");
      return ResponseEntity.ok(result);
    }

    result.put("status", "LOGIN");
    result.put("username", auth.getName()); // 토큰에 담긴 사용자 아이디
    return ResponseEntity.ok(result);
  }

  @GetMapping("/hostCheck/{username}")
  public ResponseEntity<String> checkIsHost(@PathVariable String username, Authentication auth) {
    if (auth == null || !auth.isAuthenticated()) {
      return ResponseEntity.ok("NOT_LOGIN"); // ❌ 로그인 안 된 사용자
    }

    if (!username.equals(auth.getName())) {
      return ResponseEntity.ok("NOT_HOST"); // ❌ 로그인은 했지만 호스트 아님
    }

    return ResponseEntity.ok("HOST"); // ✅ 본인 페이지
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletResponse response) {
    Cookie cookie = new Cookie("token", null);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(0); // 즉시 만료시킴
    response.addCookie(cookie);
    return ResponseEntity.ok("로그아웃 완료");
  }
}
