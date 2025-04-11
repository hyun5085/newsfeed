package com.example.newsfeed.follow.dto;

import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.user.entity.User;
import lombok.Getter;

@Getter
public class FollowResponseDto {

    private Long id;
    private String message;
    private String followerEmail;
    private String followedEmail;

//    public FollowResponseDto( String followerEmail, String followedEmail, String message) {
//        this.message = message;
//        this.followerEmail = followerEmail;
//        this.followedEmail = followedEmail;
//    }


    public FollowResponseDto(Long id, String followerEmail, String followedEmail, String message) {
        this.id = id;
        this.message = message;
        this.followerEmail = followerEmail;
        this.followedEmail = followedEmail;
    }

    public FollowResponseDto(String message) {
        this.message = message;
    }
}
