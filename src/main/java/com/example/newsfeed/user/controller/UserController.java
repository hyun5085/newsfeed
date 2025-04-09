package com.example.newsfeed.user.controller;

import com.example.newsfeed.common.Const;
import com.example.newsfeed.cookiesession.dto.LoginResponseDto;
import com.example.newsfeed.user.dto.request.DeleteUserRequestDto;
import com.example.newsfeed.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import com.example.newsfeed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signUp(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        UserResponseDto responseDto = userService.updateUser(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        UserResponseDto responseDto = userService.updatePassword(id, requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @Valid @RequestBody DeleteUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        userService.delete(id, requestDto, loginUserId);
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
