package com.example.newsfeed.comment.dto.response;

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
}
