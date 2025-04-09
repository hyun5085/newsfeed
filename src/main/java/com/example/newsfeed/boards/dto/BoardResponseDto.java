package com.example.newsfeed.boards.dto;

import com.example.newsfeed.boards.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private final Long id;
    private final String contents;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;

    public BoardResponseDto(Long id, String contents, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.contents = contents;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(), board.getContents(), board.getCreatedAt(), board.getUpdatedAt());
    }
}
