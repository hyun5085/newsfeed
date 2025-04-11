package com.example.newsfeed.cookiesession.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class LoginSuccessResponseDto {

    private Long id;
    private String username;   // 유저 이름
    private String email;  // 작성자 Email
    private String token;

    public LoginSuccessResponseDto(User user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.token = token;

    }
}
