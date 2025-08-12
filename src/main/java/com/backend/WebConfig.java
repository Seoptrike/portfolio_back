//package com.backend;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry
//        .addMapping("/**")
//        .allowedOrigins("http://localhost:5173", "https://portfolio-front-dun.vercel.app")
//        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        .allowedHeaders("*")
//        .allowCredentials(true);
//  }
//
//  @Bean
//  public CookieSerializer cookieSerializer() {
//    DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//
//    // 이 부분이 진짜 해결책입니다.
//    serializer.setSameSite("None");
//
//    // HTTPS에서만 쿠키를 전송하도록 설정합니다.
//    serializer.setUseSecureCookie(true);
//
//    return serializer;
//  }
//}
