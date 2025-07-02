package com.backend.controller;

import com.backend.domain.LoginRequestDTO;
import com.backend.domain.UsersVO;
import com.backend.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(authService.login(dto.getUsername(), dto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersVO vo) {
        authService.register(vo);
        return ResponseEntity.ok().build(); // 또는 상태 메시지 반환 가능
    }
}
