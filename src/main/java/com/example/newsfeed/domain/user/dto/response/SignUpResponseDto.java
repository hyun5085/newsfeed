package com.example.newsfeed.domain.user.dto.response;

import com.example.newsfeed.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 회원 가입 응답 Dto
 */
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

    /**
     * Instantiates a new Signup response dto.
     *
     * @param id       the id
     * @param username the username
     * @param email    the email
     * @param birthday the birthday
     * @param hobby    the hobby
     */
    public SignUpResponseDto(Long id, String username, String email, LocalDate birthday, String hobby) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
    }

    /**
     * 회원가입 응답 메소드
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
