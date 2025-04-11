package com.example.newsfeed.domain.follow.dto.response;

import lombok.Getter;

@Getter
public class FollowResponseDto {

    private Long id;
    private String message;
    private String followerEmail;
    private String followedEmail;

    public FollowResponseDto(Long id, String followerEmail, String followedEmail, String message) {
        this.id = id;
        this.message = message;
        this.followerEmail = followerEmail;
        this.followedEmail = followedEmail;
    }

}
