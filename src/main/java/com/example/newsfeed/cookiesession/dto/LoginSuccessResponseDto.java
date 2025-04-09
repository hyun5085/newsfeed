package com.example.newsfeed.cookiesession.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class LoginSuccessResponseDto {

    private String username;   // 유저 이름
    private String email;  // 작성자 Email

    public LoginSuccessResponseDto(User user) {

        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
