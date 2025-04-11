package com.example.newsfeed.domain.like.dto;

import com.example.newsfeed.domain.like.entity.Like;
import lombok.Getter;

/**
 * 댓글 좋아요 응답 DTO
 */
@Getter
public class LikeResponseDto {

    private final Long commentId;
    private final Long userId;
    private final String userName;
    private boolean liked;

    public LikeResponseDto(Long commentId, Long userId, String userName,boolean liked) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.liked = liked;
    }

    public static LikeResponseDto from(Like like){
        return new LikeResponseDto(
                like.getComment().getId(),
                like.getUser().getId(),
                like.getUser().getUsername(),
                true
        );
    }
}
