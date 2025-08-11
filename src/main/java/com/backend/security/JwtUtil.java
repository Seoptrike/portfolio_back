package com.backend.security;

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
    return Jwts.builder()
        .setSubject(user.getUsername())
        .claim("username", user.getUsername())
        .claim("user_id", user.getUserId())
        .claim("role", user.getRole() == 0 ? "ADMIN" : "USER") // ← role을 문자열로
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
