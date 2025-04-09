package com.example.newsfeed.follow.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowRequestDto {

    private Long followerId;
    private Long followingId;

        public FollowRequestDto(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

}
