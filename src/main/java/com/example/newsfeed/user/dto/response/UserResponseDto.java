package com.example.newsfeed.user.dto.response;

import com.example.newsfeed.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 유저 응답 Dto
 */
@Getter
public class UserResponseDto {

    private final Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private final String username;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @Past(message = "생일은 과거 날짜여야 합니다.")
    private final LocalDate birthday;

    private final String hobby;

    /**
     * Instantiates a new User response dto.
     *
     * @param user the user
     */
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.hobby = user.getHobby();
    }

    /**
     * 회원가입 저장
     *
     * @param user the user
     * @return the signup response dto
     */
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
