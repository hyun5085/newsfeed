package com.example.newsfeed.common;

import com.example.newsfeed.cookiesession.filter.JwtAuthFilter;
import com.example.newsfeed.cookiesession.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig2 {

    private final JwtUtil jwtUtil;

    // JWT 인증 필터를 Bean으로 등록
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil);
    }

    // Spring Security 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                // CSRF 보안 기능 비활성화 (REST API에서 권장)
                .csrf(AbstractHttpConfigurer::disable)

                // URL 별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // 인증 없이 접근 가능한 URL 설정 (화이트리스트)
                        .requestMatchers("/", "/api/login", "/api/users/signup", "/api/users/*").permitAll()
                        // 그 외 모든 요청은 인증된 사용자만 접근 허용
                        .anyRequest().authenticated()
                )

                // 세션을 사용하지 않고 JWT 기반으로 인증 처리
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // UsernamePasswordAuthenticationFilter 전에 JWT 인증 필터 적용
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
