package com.example.newsfeed.domain.like.controller;

import com.example.newsfeed.common.util.JwtUtil;
import com.example.newsfeed.domain.like.dto.LikeResponseDto;
import com.example.newsfeed.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/{commentId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<LikeResponseDto> createLike(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        LikeResponseDto likeDto = likeService.createLike(commentId, userId);
        return ResponseEntity.ok(likeDto);
    }

}
