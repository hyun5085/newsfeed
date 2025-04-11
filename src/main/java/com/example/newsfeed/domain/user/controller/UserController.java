package com.example.newsfeed.domain.user.controller;

import com.example.newsfeed.common.util.JwtUtil;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.user.dto.request.DeleteUserRequestDto;
import com.example.newsfeed.domain.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.domain.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.domain.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.domain.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.domain.user.dto.response.UserResponseDto;
import com.example.newsfeed.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 컨트롤러
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입
     *
     * @param requestDto the request dto
     * @return the response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signUp(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 회원 식별번호 조회
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     * 회원 정보 변경
     *
     * @param token      the token
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto requestDto
    ) {
        String token = authorizationHeader.substring(7); // "Bearer " 제거
        Long loginUserId = jwtUtil.extractUserId(token); // JwtUtil에서 userId 추출
        UserResponseDto responseDto = userService.updateUser(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 비밀번호 변경
     *
     * @param token      the token
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        String token = authorizationHeader.substring(7); // "Bearer " 제거
        Long loginUserId = jwtUtil.extractUserId(token);
        UserResponseDto responseDto = userService.updatePassword(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    /**
     * 삭제 기능
     *
     * @param authorizationHeader the authorization header
     * @param id                  the id
     * @param requestDto          the request dto
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id,
            @Valid @RequestBody DeleteUserRequestDto requestDto
    ) {
        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long loginUserId = jwtUtil.extractUserId(token);
        userService.delete(id, requestDto, loginUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
