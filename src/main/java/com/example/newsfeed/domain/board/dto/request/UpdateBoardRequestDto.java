package com.example.newsfeed.domain.board.dto.request;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {

    private final String contents;

    public UpdateBoardRequestDto(String contents) {
        this.contents = contents;
    }

}
