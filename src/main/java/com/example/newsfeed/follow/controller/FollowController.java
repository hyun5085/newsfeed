package com.example.newsfeed.follow.controller;

import com.example.newsfeed.cookiesession.util.JwtUtil;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/follow")
    public ResponseEntity<FollowResponseDto> follow(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody FollowRequestDto requestDto
            ) {
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = jwtUtil.extractUserId(token);

        FollowResponseDto followResponseDto = followService.follow(userId, requestDto);


        return new ResponseEntity<>(followResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/unfollow/{id}")
    public ResponseEntity<UnfollowResponseDto> unfollow(
//            @RequestHeader("Authorization") String token,
            @PathVariable Long id
            ) {
//        Long loginUserId = jwtUtil.extractUserId(token);

//        FollowRequestDto followRequestDto = new FollowRequestDto(id);
        UnfollowResponseDto unfollowResponseDto = followService.unfollow(id);

        return new ResponseEntity<>(unfollowResponseDto, HttpStatus.OK);
    }

}
