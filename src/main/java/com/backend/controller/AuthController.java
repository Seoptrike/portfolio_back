package com.backend.controller;

import com.backend.domain.auth.LoginRequestDTO;
import com.backend.domain.auth.LoginResponseDTO;
import com.backend.domain.user.UsersVO;
import com.backend.security.CustomUserDetails;
import com.backend.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto,
                                   HttpServletRequest req, HttpServletResponse res) {
        LoginResponseDTO out = authService.login(dto.getUsername(), dto.getPassword());
        if (out.getResult() != 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(out);
        }

        // 프로덕션(HTTPS) 판단: 프록시 헤더까지 고려
        boolean https = req.isSecure() || "https".equalsIgnoreCase(req.getHeader("X-Forwarded-Proto"));
        String sameSite = https ? "None" : "Lax"; // 로컬(http)에서는 Lax, 프로덕션은 None

        ResponseCookie cookie = ResponseCookie.from("token", out.getToken()) // ← 필터의 쿠키명과 동일
                .httpOnly(true)
                .secure(https)
                .sameSite(sameSite)
                .path("/")
                .maxAge(60 * 60)   // 1h
                .build();

        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        out.setToken(null); // 바디엔 토큰 제거(선택)
        return ResponseEntity.ok(out);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
        boolean https = req.isSecure() || "https".equalsIgnoreCase(req.getHeader("X-Forwarded-Proto"));
        String sameSite = https ? "None" : "Lax";

        ResponseCookie clear = ResponseCookie.from("token", "")
                .httpOnly(true).secure(https).sameSite(sameSite)
                .path("/").maxAge(0).build();

        res.addHeader(HttpHeaders.SET_COOKIE, clear.toString());
        return ResponseEntity.ok("로그아웃 완료");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersVO vo) {
        authService.register(vo);
        return ResponseEntity.ok().build(); // 또는 상태 메시지 반환 가능
    }

    @GetMapping("/loginCheck")
    public ResponseEntity<Map<String, Object>> loginCheck(@AuthenticationPrincipal CustomUserDetails auth) {
        Map<String, Object> result = new HashMap<>();
        if (auth == null) {
            result.put("status", "GUEST");
            return ResponseEntity.ok(result);
        }
        result.put("status", "LOGIN");
        result.put("username", auth.getUsername());
        result.put("userId", auth.getUserId()); // ✅ 정상 동작
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


}
