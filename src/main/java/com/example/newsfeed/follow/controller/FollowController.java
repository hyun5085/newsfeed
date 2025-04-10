package com.example.newsfeed.follow.controller;

import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.dto.UnfollowResponseDto;
import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.follow.service.FollowService;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<FollowResponseDto> follow(
            @RequestBody FollowRequestDto requestDto
            ) {

        FollowResponseDto followResponseDto = followService.follow(requestDto);


        return new ResponseEntity<>(followResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/unfollow/{followerEmail}/{followedEmail}")
    public ResponseEntity<UnfollowResponseDto> unfollow(
   //         @RequestHeader("Authorization") String token,
            @PathVariable String followerEmail,
            @PathVariable String followedEmail
            ) {

        FollowRequestDto followRequestDto = new FollowRequestDto(followerEmail, followedEmail);
        UnfollowResponseDto unfollowResponseDto = followService.unfollow(followRequestDto);

        return new ResponseEntity<>(unfollowResponseDto, HttpStatus.OK);
    }

}
