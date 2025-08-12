package com.backend.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor; // 롬복 사용
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component; // 컴포넌트로 등록
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // 로그인에서 발급한 쿠키명과 반드시 동일해야 함
    private static final String COOKIE_NAME = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = resolveToken(request);
        if (token != null) {
            log.debug("JWT token detected");
        } else {
            log.debug("JWT token not found in Authorization header nor cookie");
        }

        try {
            if (token != null && jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.parseToken(token);
                Long userId = claims.get("user_id", Long.class);
                String username = claims.get("username", String.class);
                String role = claims.get("role", String.class);

                var authorities = List.<GrantedAuthority>of(new SimpleGrantedAuthority("ROLE_" + role));
                var principal = new CustomUserDetails(userId, username, authorities);

                var auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);

                var ctx = SecurityContextHolder.createEmptyContext();
                ctx.setAuthentication(auth);
                SecurityContextHolder.setContext(ctx);
            } else {
                SecurityContextHolder.clearContext(); // 유효하지 않으면 컨텍스트 정리
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            log.warn("JWT processing failed: {}", e.getMessage());
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        // 1) Authorization: Bearer
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        // 2) Cookie
        if (request.getCookies() != null) {
            for (var c : request.getCookies()) {
                if (COOKIE_NAME.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    // (선택) 로그인/회원가입/헬스체크 등은 필터 스킵
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.startsWith("/api/auth/login")
                || uri.startsWith("/api/auth/register")
                || uri.startsWith("/actuator");
    }
}
