package com.example.newsfeed.follow.controller;

import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<FollowResponseDto> follow(@RequestBody FollowRequestDto requestDto) {

        FollowResponseDto followResponseDto = followService.follow(requestDto);

        return new ResponseEntity<>(followResponseDto, HttpStatus.OK);
    }


}