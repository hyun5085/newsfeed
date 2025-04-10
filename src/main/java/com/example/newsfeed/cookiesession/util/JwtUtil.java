package com.example.newsfeed.cookiesession.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Spring이 자동으로 관리해주는 객체(=Bean) 로 등록
public class JwtUtil {
    private static final String SECRET_KEY_STRING = "my-very-secret-jwt-key-that-is-long-enough!";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    private Key key;

    @PostConstruct // 스프링이 해당 Bean을 다 만들고 난 뒤에 실행되는 메서드
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    }


    // JWT 생성 (이메일과 id 추가)
    public String createToken(String email, Long id) {
        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)  // 사용자 ID를 claim으로 추가
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 이메일 꺼내기
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // JWT에서 사용자 ID 추출
    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class); // ID를 추출
    }

}
