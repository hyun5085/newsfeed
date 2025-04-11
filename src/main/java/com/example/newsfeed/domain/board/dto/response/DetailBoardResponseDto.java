package com.example.newsfeed.domain.board.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailBoardResponseDto {

    private final Long id;
    private final String contents;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public DetailBoardResponseDto(Long id, String contents, Long userId, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
