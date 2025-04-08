package com.example.newsfeed.user.controller;

import com.example.newsfeed.common.Const;
import com.example.newsfeed.cookiesession.dto.LoginResponseDto;
import com.example.newsfeed.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import com.example.newsfeed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signUp(
                requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail(), requestDto.getBirthday(), requestDto.getHobby());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto responseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        userService.updateUser(
                id,requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword(),
                requestDto.getBirthday(), requestDto.getHobby(), responseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto responseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword(), responseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto responseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        userService.delete(id, requestDto.getPassword(), responseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
