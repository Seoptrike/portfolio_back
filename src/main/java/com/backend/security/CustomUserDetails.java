package com.backend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserDetails implements UserDetails {
    // 우리가 추가한 커스텀 getter: 사용자 ID를 반환
    @Getter
    private final Long userId;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long userId, String username, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }
    // --- UserDetails 인터페이스의 필수 구현 메서드들 ---
    /**
     * 사용자에게 부여된 권한 목록을 반환합니다. (e.g., "ROLE_USER", "ROLE_ADMIN")
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    /**
     * 사용자의 비밀번호를 반환합니다. 토큰 기반 인증에서는 사용하지 않으므로 null을 반환합니다.
     */
    @Override
    public String getPassword() {
        return null;
    }
    /**
     * 사용자의 이름을 반환합니다. Spring Security에서 사용자를 식별하는 주요 값 중 하나입니다.
     */
    @Override
    public String getUsername() {
        return username;
    }
    // --- 계정 상태 관련 메서드들 (보통 true로 설정) ---
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
