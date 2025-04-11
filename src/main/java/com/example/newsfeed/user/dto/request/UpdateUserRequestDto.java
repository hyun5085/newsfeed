package com.example.newsfeed.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

/**
 * The type Update user request dto.
 */
@Getter
public class UpdateUserRequestDto {

    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Past(message = "생일은 과거 날짜여야 합니다.")
    private LocalDate birthday;

    private String hobby;
}
