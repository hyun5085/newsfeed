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
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signUp(
                requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail(), requestDto.getBirthday(), requestDto.getHobby());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
//    살려주세요 살려주시라요 살려주시지요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUser(
            @Valid @RequestBody UpdateUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        UserResponseDto responseDto = userService.updateUser(requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/users")
    public ResponseEntity<UserResponseDto> updatePassword(
            @Valid @RequestBody UpdatePasswordRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        UserResponseDto responseDto = userService.updatePassword(requestDto, loginUserId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> delete(
            @Valid @RequestBody DeleteUserRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        Long loginUserId = loginUser.getId();
        userService.delete(requestDto, loginUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
