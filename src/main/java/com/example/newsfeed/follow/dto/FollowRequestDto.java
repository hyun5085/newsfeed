package com.example.newsfeed.follow.dto;

import com.example.newsfeed.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FollowRequestDto {

    @NotBlank(message = "사용자의 Id를 입력해주세요")
    private Long followerId;

    @NotBlank(message = "사용자의 Id를 입력해주세요")
    private Long followedId;

    public FollowRequestDto(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    //    public FollowRequestDto(String followerEmail, String followedEmail) {
//        this.followerEmail = followerEmail;
//        this.followedEmail = followedEmail;
//
//    }
}
