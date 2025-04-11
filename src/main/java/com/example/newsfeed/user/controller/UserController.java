package com.example.newsfeed.user.controller;

import com.example.newsfeed.cookiesession.util.JwtUtil;
import com.example.newsfeed.user.dto.request.DeleteUserRequestDto;
import com.example.newsfeed.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import com.example.newsfeed.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    /**
     * Sign up response entity.
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
     * Find by id response entity.
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
     * Update user response entity.
     *
     * @param token      the token
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto requestDto
    ) {
        Long loginUserId = jwtUtil.extractUserId(token); // JwtUtil에서 userId 추출
        UserResponseDto responseDto = userService.updateUser(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Update password response entity.
     *
     * @param token      the token
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        Long loginUserId = jwtUtil.extractUserId(token);
        UserResponseDto responseDto = userService.updatePassword(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete response entity.
     *
     * @param token      the token
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody DeleteUserRequestDto requestDto
    ) {
        Long loginUserId = jwtUtil.extractUserId(token);
        userService.delete(id, requestDto, loginUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
