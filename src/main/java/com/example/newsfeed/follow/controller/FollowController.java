package com.example.newsfeed.follow.controller;

import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.service.FollowService;
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
//            @AuthenticationPrincipal UserDetails userDetails
            ) {

        FollowResponseDto followResponseDto = followService.follow(requestDto);

        return new ResponseEntity<>(followResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/unfollow/{followerEmail}/{followedEmail}")
    public ResponseEntity<FollowResponseDto> unfollow(
            @PathVariable String followerEmail,
            @PathVariable String followedEmail
//            @AuthenticationPrincipal UserDetails userDetails
            ) {

        FollowRequestDto followRequestDto = new FollowRequestDto(followerEmail, followedEmail);
        FollowResponseDto followResponseDto = followService.unfollow(followRequestDto);

        return new ResponseEntity<>(followResponseDto, HttpStatus.OK);
    }


}