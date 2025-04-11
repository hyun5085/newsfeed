package com.example.newsfeed.domain.follow.dto.request;

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
