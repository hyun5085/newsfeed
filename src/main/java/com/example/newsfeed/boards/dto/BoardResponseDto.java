package com.example.newsfeed.boards.dto;

import com.example.newsfeed.boards.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private final Long id;
    private final String contents;

    public BoardResponseDto(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(), board.getContents());
    }
}
