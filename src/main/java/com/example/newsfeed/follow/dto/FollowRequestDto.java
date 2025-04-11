package com.example.newsfeed.follow.dto;

import com.example.newsfeed.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FollowRequestDto {

    @NotNull(message = "사용자의 Id를 입력해주세요")
    private Long followedId;

    public FollowRequestDto(Long followedId) {
        this.followedId = followedId;
    }

}
