package com.example.newsfeed.follow.dto;

import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowResponseDto {

    private String message;
    private Long followerId;
    private Long followingId;

//    public FollowResponseDto(String message, User followerId, User followingId) {
//        this.message = message;
//        this.followerId = followerId;
//        this.followingId = followingId;
//    }

        public FollowResponseDto(String message, Long followerId, Long followingId) {
        this.message = message;
        this.followerId = followerId;
        this.followingId = followingId;
    }


    //    2
//    private Long id; //private가 안되는 이유 final을 임시 삭제
//
//    private final User email;
//
//    public FollowResponseDto(Long id, User email) {
//        this.id = id;
//        this.email = email;
//    }


//      1
//      private fianl Long id;
//    private final Follow follower;
//
//    private final Follow following;
//
//    public FollowResponseDto(Long followId, Follow follower, Follow following) {
//        this.followId = followId;
//        this.follower = follower;
//        this.following = following;
    }

