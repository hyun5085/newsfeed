package com.example.newsfeed.user.dto.request;

import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    private final Long id;

    private final String oldPassword;

    private final String newPassword;

    public UpdatePasswordRequestDto(Long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
