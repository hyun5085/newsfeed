package com.example.newsfeed.domain.comment.dto.response;

import com.example.newsfeed.domain.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 응답 DTO
 */
@Getter
public class CommentResponseDto {

    private final Long id;
    private final Long boardId;
    private final Long userId;
    private final String userName;
    private final String contents;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public CommentResponseDto(Long id,
                              Long boardId,
                              Long userId,
                              String userName,
                              String contents,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    /**
     * Comment 엔티티를 CommentResponseDto로 변환
     */
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
