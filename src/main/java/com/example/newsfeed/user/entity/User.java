package com.example.newsfeed.user.entity;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.user.security.PasswordConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String hobby;

    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param requestDto the request dto
     */
    public User(SignUpRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.birthday = requestDto.getBirthday();
        this.hobby = requestDto.getHobby();
    }

    /**
     * Update user.
     *
     * @param id         the id
     * @param requestDto the request dto
     */
    public void updateUser(Long id, UpdateUserRequestDto requestDto) {
        if (!requestDto.getPassword().equals(password)){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        this.id = id;
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        this.birthday = requestDto.getBirthday();
        this.hobby = requestDto.getHobby();
    }

    /**
     * Update password.
     *
     * @param id         the id
     * @param requestDto the request dto
     */
    public void updatePassword(Long id, UpdatePasswordRequestDto requestDto) {
        if (!requestDto.getOldPassword().equals(getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        this.id = id;
        this.password = requestDto.getNewPassword();
    }

    /**
     * Validate password.
     *
     * @param id    the id
     * @param input the input
     */
    public void validatePassword(Long id, String input) {
        if (!this.password.equals(input)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        this.id = id;
    }
}
