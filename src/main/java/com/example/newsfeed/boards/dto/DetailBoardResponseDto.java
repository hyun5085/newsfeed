package com.example.newsfeed.boards.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailBoardResponseDto {

    private final Long id;
    private final String contents;
    private final String username;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;

    public DetailBoardResponseDto(Long id, String contents, String username, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.contents = contents;
        this.username = username;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
