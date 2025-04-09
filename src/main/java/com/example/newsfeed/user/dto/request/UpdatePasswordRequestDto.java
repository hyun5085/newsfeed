package com.example.newsfeed.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    private final Long id;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String oldPassword;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String newPassword;

    public UpdatePasswordRequestDto(Long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
