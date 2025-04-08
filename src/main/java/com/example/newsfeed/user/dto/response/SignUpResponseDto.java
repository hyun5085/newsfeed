package com.example.newsfeed.user.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpResponseDto {

    private final Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private final String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @Past(message = "생일은 과거 날짜여야 합니다.")
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
