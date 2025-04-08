package com.example.newsfeed.user.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateUserRequestDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDate birthday;

    private String hobby;
}
