package com.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        //허용할 출처(Origin 설정)
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "https://seoportfolio.vercel.app"
        ));

        // 허용할 HTTP 메서드 설정
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // 허용할 요청 헤더 설정
        config.setAllowedHeaders(Arrays.asList("*"));

        // 브라우저에 노출할 헤더 설정 (예: JWT 토큰)
        config.setExposedHeaders(Arrays.asList("Authorization", "Authorization-refresh"));

        // 자격 증명(쿠키, 인증 헤더 등) 허용 여부
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 위 설정 적용

        return source;
    }
}
