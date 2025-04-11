package com.example.newsfeed.domain.board.dto.response;

import com.example.newsfeed.domain.board.entity.Board;
import lombok.Getter;

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
