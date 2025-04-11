package com.example.newsfeed.cookiesession.controller;

import com.example.newsfeed.cookiesession.dto.LoginRequestDto;
import com.example.newsfeed.cookiesession.dto.LoginSuccessResponseDto;
import com.example.newsfeed.cookiesession.service.LoginService;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.cookiesession.util.JwtUtil;
import com.example.newsfeed.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil; // ✅ 추가해줘야 함


    @PostMapping("/login")
    public ResponseEntity<LoginSuccessResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) {



        // 로그인 시도
        System.out.println("Attempting to log in user: " + loginRequestDto.getEmail());
        User loginUser = loginService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );


        // 로그인 실패 시 예외 발생
        if (loginUser == null) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 로그인 성공 시 로그 출력
        System.out.println("Login Successful - User ID: " + loginUser.getId());
        System.out.println("Login Successful - User Email: " + loginUser.getEmail());


        // JWT 생성
        String jwtToken = jwtUtil.createToken(loginUser.getEmail(), loginUser.getId());

        // 생성된 토큰을 로그로 출력
        System.out.println("Generated JWT Token: " + jwtToken);

        // ✅ JWT를 응답 헤더에 담기
        response.setHeader("Authorization", "Bearer " + jwtToken);


        LoginSuccessResponseDto successResponse = new LoginSuccessResponseDto(loginUser, jwtToken);


        return ResponseEntity.ok(successResponse);
    }


}
