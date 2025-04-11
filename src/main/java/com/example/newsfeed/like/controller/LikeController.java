package com.example.newsfeed.like.controller;

import com.example.newsfeed.cookiesession.util.JwtUtil;
import com.example.newsfeed.like.dto.LikeResponseDto;
import com.example.newsfeed.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtUtil jwtUtil;

    @PostMapping("/comments/{commentId}/likes")
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
