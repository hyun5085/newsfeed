package com.example.newsfeed.user.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestDto {

    private final String username;

    private final String email;

    private final String password;

    private final LocalDate birthday;

    private final String hobby;

    public SignUpRequestDto(String username, String email, String password, LocalDate birthday, String hobby) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.hobby = hobby;
    }
}
