package com.example.newsfeed.comment.dto.response;

import com.example.newsfeed.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

// 댓글 생성 시 응답 dto
@Getter
public class CreateCommentResponseDto {

    private final Long id;

    private final Long boardId;

    private final Long userId;
    private final String userName;
    private final String contents;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;


    public CreateCommentResponseDto(Long id, Long boardId, Long userId, String userName, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.createdAt = createdAt;

    }
    // comment 엔티티를 CreateCommentResponseDto로 변환
    // save 메서드에서 사용됨
    public static CreateCommentResponseDto from(Comment comment){
        return new CreateCommentResponseDto(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getContents(),
                comment.getCreatedAt()
        );
    }
}
