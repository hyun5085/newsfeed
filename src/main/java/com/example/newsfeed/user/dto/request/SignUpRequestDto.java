package com.example.newsfeed.user.dto.request;

import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.entity.User;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
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

    public static SignUpResponseDto from(User user) {
        return new SignUpResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBirthday(),
                user.getHobby()
        );
    }
}
