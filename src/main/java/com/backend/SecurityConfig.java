package com.backend;

import com.backend.security.JwtAuthenticationFilter;
import com.backend.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Security 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        http.cors(Customizer.withDefaults()) // ✅ CORS 활성화
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화 → JWT용
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(
                                                "/api/auth/**", // 로그인, 회원가입
                                                "/swagger-ui/**", // Swagger UI 정적 리소스
                                                "/v3/api-docs/**", // Swagger API 문서
                                                "/swagger-resources/**", // Swagger 리소스
                                                "/webjars/**", // Swagger에서 사용하는 JS/CSS 등
                                                "/api/total/**",
                                                "/api/career/**",
                                                "/api/project/**",
                                                "/api/about/**",
                                                "/api/resume/**",
                                                "/api/guest/**")
                                        .permitAll()
                                        .requestMatchers("/api/user/**")
                                        .permitAll() // 임시
                                        .requestMatchers("/api/admin/**")
                                        .hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated())
                .exceptionHandling(
                        ex ->
                                ex.authenticationEntryPoint(
                                        (req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)));
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // 3. AuthenticationManager 빈 등록 (필수)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    // 4. 필요 시 AuthenticationProvider 정의 (UserDetailsService 있을 경우)
}
