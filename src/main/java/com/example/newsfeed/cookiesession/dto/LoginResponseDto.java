package com.example.newsfeed.cookiesession.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String username;   // 유저 이름
    private String email;  // 작성자 Email

    public LoginResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
