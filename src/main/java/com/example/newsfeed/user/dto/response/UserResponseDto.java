package com.example.newsfeed.user.dto.response;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final LocalDate birthday;

    private final String hobby;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.hobby = user.getHobby();
    }
}
