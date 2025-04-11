package com.example.newsfeed.domain.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 회원가입 요청 Dto
 */
@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @Past(message = "생일은 과거 날짜여야 합니다.")
    private LocalDate birthday;

    private String hobby;
}
