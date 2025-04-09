package com.example.newsfeed.follow.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowRequestDto {

    private String followerEmail;
    private String followedEmail;

    public FollowRequestDto(String followerEmail, String followedEmail) {
        this.followerEmail = followerEmail;
        this.followedEmail = followedEmail;
    }
}
