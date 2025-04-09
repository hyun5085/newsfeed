package com.example.newsfeed.follow.dto;

import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowResponseDto {

    private String message;
    private Long followerId;
    private Long followingId;

        public FollowResponseDto(String message, Long followerId, Long followingId) {
        this.message = message;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    }

