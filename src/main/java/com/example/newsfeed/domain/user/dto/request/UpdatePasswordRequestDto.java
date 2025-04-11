package com.example.newsfeed.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 비밀번호 요청 Dto
 */
@Getter
public class UpdatePasswordRequestDto {

    private final Long id;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String oldPassword;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private final String newPassword;

    /**
     * 비밀번호 변경
     *
     * @param id          the id
     * @param oldPassword the old password
     * @param newPassword the new password
     */
    public UpdatePasswordRequestDto(Long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
