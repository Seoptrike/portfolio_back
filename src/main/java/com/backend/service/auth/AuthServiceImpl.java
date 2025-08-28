package com.backend.service.auth;

import com.backend.domain.auth.LoginResponseDTO;
import com.backend.domain.user.UsersVO;
import com.backend.mapper.AuthMapper;
import com.backend.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public LoginResponseDTO login(String username, String password) {
        LoginResponseDTO res = new LoginResponseDTO();
        UsersVO user = authMapper.findByUsername(username);
        if (user == null) {
            res.setResult(0); // 유저 정보 없음
            return res;
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            res.setResult(1); // 비밀번호 오류
            return res;
        }
        if (user.getRole() == 99) {
            res.setResult(3); // 탈퇴회원
            return res;
        }
        res.setResult(2);
        res.setToken(jwtUtil.generateToken(user));
        authMapper.updateLastLogin(user.getUserId());
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
        System.out.println(">>> LoginMapper class: " + authMapper.getClass());
    }
}
