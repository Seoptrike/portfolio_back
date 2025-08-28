package com.backend.security;

import com.backend.domain.auth.RoleType;
import com.backend.domain.user.UsersVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private static final String SECRET_KEY =
      "my-super-secret-jwt-key-my-super-secret-jwt-key"; // 최소 256bit (32자 이상)
  private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

  private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

  public String generateToken(UsersVO user) {
    RoleType roleType = RoleType.fromCode(user.getRole());
    if (roleType == RoleType.WITHDRAWN) {
      throw new IllegalStateException("Withdrawn user cannot get a token");
    }

    return Jwts.builder()
        .setSubject(user.getUsername())
        .claim("username", user.getUsername())
        .claim("userId", user.getUserId())
        .claim("roles", java.util.List.of(roleType.getRoleName())) // ← role을 문자열로
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  public boolean validateToken(String token) {
    try {
      parseToken(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
