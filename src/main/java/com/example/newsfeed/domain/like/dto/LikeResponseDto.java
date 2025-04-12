package com.example.newsfeed.domain.like.dto;

import com.example.newsfeed.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LikeResponseDto {
    // 댓글 id
    private Long commentId;
    // 총 좋아요 개수
    private long totalLikes;
    // 현재 유저가 해당 댓글에 좋아요를 눌렀는지 여부
    private boolean likedByCurrentUser;
    // 좋아요한 userList
    private List<LikedUserDto> likedUsers;


    public static LikeResponseDto of(Comment comment,
                                     long totalLikes,
                                     boolean likedByCurrentUser,
                                     List<LikedUserDto> likedUsers) {
        return new LikeResponseDto(
                comment.getId(),
                totalLikes,
                likedByCurrentUser,
                likedUsers
        );
    }
}
