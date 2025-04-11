package com.example.newsfeed.domain.auth.dto.response;

import com.example.newsfeed.domain.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long id;
    private String username;   // 유저 이름
    private String email;  // 작성자 Email

    public LoginResponseDto(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
