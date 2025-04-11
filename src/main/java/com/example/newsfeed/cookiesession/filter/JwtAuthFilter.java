package com.example.newsfeed.cookiesession.filter;

import com.example.newsfeed.cookiesession.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/api/users/signup", "/api/login", "/api/logout"};
    private final JwtUtil jwtUtil;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();

        log.info("JWT 인증 필터 실행, 요청 URI: {}", requestURI);

        if (!isWhiteList(requestURI)) {
            // 요청 헤더에서 Authorization 토큰 추출
            String authHeader = httpRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요합니다.");
                return;
            }

            String token = authHeader.substring(7); // "Bearer " 제거

            // JWT 유효성 검사
            if (!jwtUtil.validateToken(token)) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
                return;
            }
            setAuthentication(token);

            // 토큰에서 사용자 정보(email 등) 꺼내서 사용할 수도 있음
            String email = jwtUtil.getEmailFromToken(token);
            log.info("JWT 인증 성공. 사용자 이메일: {}", email);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
    private void setAuthentication(String token) {
        String email = jwtUtil.getEmailFromToken(token);

        // 임시 권한 부여 (필요하면 권한을 변경할 수 있음)
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        // 현재 보안 컨텍스트에 인증 정보 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}