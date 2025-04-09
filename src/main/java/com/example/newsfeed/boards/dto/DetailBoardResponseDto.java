package com.example.newsfeed.boards.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailBoardResponseDto {

    private final Long id;
    private final String contents;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public DetailBoardResponseDto(Long id, String contents, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.contents = contents;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
