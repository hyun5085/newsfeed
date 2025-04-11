package com.example.newsfeed.like.dto;

import com.example.newsfeed.like.entity.Like;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    // 응답에 무엇을..
    // 좋아요한사람 이름 , id값, 총 좋아요개수?
    // 댓글 번호!
    private final Long contentId;
    private final Long userId;
    private final String userName;
    private final boolean liked;

    public LikeResponseDto(Long contentId, Long userId, String userName, boolean liked) {
        this.contentId = contentId;
        this.userId = userId;
        this.userName = userName;
        this.liked = liked;
    }

//    public static LikeResponseDto from(Like like){
//        return new LikeResponseDto(
//                like.getComment().getId(),
//                like.getUser().getId(),
//                like.getUser().getUsername()
//        )
//    }
}
