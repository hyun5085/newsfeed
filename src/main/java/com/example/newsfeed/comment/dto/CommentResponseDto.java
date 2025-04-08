package com.example.newsfeed.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

// 댓글 응답 dto
@Getter
public class CommentResponseDto {

    private final Long id;

    private final Long boardId;

    private final Long userId;

    private final String userName;
    private final String contents;

    // 날짜 포맷 설정 일단 생성일만 추가
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public CommentResponseDto(Long id, Long boardId, Long userId, String userName, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }
}
