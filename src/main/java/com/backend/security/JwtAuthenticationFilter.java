package com.backend.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor; // 롬복 사용
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component; // 컴포넌트로 등록
import org.springframework.web.filter.OncePerRequestFilter;

@Component // 스프링 빈으로 등록
@RequiredArgsConstructor // final 필드 생성자 자동 생성
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ 1. 헤더와 쿠키에서 토큰을 찾는 로직을 별도 메서드로 분리
        String token = resolveToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            Claims claims = jwtUtil.parseToken(token);
            // 1. 토큰에서 필요한 모든 정보를 추출합니다.
            Long userId = claims.get("user_id", Long.class);
            String username = claims.get("username", String.class);
            String role = claims.get("role", String.class);
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            // 2. 모든 정보를 담는 CustomUserDetails 객체를 생성합니다.
            CustomUserDetails userDetails = new CustomUserDetails(userId, username, authorities);

            // 3. Authentication 객체의 principal 자리에 userDetails 객체를 통째로 넣습니다.
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 4. SecurityContextHolder에 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * ✅ 2. Request에서 토큰을 해결하는 메서드
     *
     * @param request HttpServletRequest
     * @return 찾은 토큰 (없으면 null)
     */
    private String resolveToken(HttpServletRequest request) {
        // 1순위: Authorization 헤더에서 'Bearer' 토큰 찾기
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 접두사 제거
        }

        // 2순위: 헤더에 없으면 쿠키에서 'token' 찾기
        if (request.getCookies() != null) {
            for (var cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}