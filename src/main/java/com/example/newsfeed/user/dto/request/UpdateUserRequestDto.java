package com.example.newsfeed.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 회원 정보 수정 요청 Dto
 */
@Getter
public class UpdateUserRequestDto {

    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Past(message = "생일은 과거 날짜여야 합니다.")
    private LocalDate birthday;

    private String hobby;
}
