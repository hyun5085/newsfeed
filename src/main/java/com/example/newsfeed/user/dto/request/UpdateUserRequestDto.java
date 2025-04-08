package com.example.newsfeed.user.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private String username;

    private String email;

    private String password;
}
