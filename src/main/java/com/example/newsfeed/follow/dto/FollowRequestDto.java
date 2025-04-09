package com.example.newsfeed.follow.dto;

import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowRequestDto {

    private Long followerId;
    private Long followingId;

//    public FollowRequestDto(User followerId, User followingId) {
//        this.followerId = followerId;
//        this.followingId = followingId;
//    }

        public FollowRequestDto(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    //    2
//    private User email;
//
//    public FollowRequestDto(User email) {
//        this.email = email;
//    }

    //    1
    //    private User follower;
//
//    public FollowRequestDto(User follower) {
//        this.follower = follower;
//    }
}
