package com.example.newsfeed.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private final String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private final String password;

    @Past(message = "생일은 과거 날짜여야 합니다.")
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
