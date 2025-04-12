package com.example.newsfeed.domain.like.controller;

import com.example.newsfeed.common.util.JwtUtil;
import com.example.newsfeed.domain.like.dto.LikeResponseDto;
import com.example.newsfeed.domain.like.dto.LikeUpdateResponseDto;
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

    /**
     * 좋아요 생성 API
     *
     * @param commentId
     * @param authorizationHeader
     * @return
     */
    @PostMapping
    public ResponseEntity<LikeUpdateResponseDto> createLike(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);

        LikeUpdateResponseDto likeDto = likeService.createLike(commentId, userId);
        return ResponseEntity.status(201).body(likeDto);
    }

    /**
     * 좋아요 전체 조회 API
     * @param commentId
     * @param authorizationHeader
     * @return
     */
    @GetMapping
    public ResponseEntity<LikeResponseDto> getLikeInfo(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);

        LikeResponseDto likeAll = likeService.getLikeInfo(commentId, userId);
        return ResponseEntity.ok(likeAll);
    }

    /**
     * 좋아요 삭제 API
     * 자신의 좋아요만 삭제 가능
     * @param commentId
     * @param authorizationHeader
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteLike(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);

        likeService.deleteLike(commentId, userId);
        return ResponseEntity.ok().build();
    }
}
