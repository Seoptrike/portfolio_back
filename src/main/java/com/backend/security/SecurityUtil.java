package com.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
  // 이제 principal은 CustomUserDetails 객체입니다.
  private static CustomUserDetails getUserDetails() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
      // 인증 정보가 없거나, 타입이 맞지 않으면 예외 또는 null 처리
      return null;
    }
    return (CustomUserDetails) authentication.getPrincipal();
  }

  public static Long getCurrentUserId() {
    CustomUserDetails userDetails = getUserDetails();
    return (userDetails != null) ? userDetails.getUserId() : null; // 혹은 예외 발생
  }

  public static String getCurrentUsername() {
    CustomUserDetails userDetails = getUserDetails();
    return (userDetails != null) ? userDetails.getUsername() : null;
  }
}
