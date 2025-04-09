package com.example.newsfeed.user.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeleteUserRequestDto {

    private Long id;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
