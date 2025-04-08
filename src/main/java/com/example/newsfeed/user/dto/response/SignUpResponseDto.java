package com.example.newsfeed.user.dto.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final LocalDate birthday;

    private final String hobby;

    public SignUpResponseDto(Long id, String username, String email, LocalDate birthday, String hobby) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
    }
}
