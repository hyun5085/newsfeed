package com.example.newsfeed.boards.dto;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {

    private final String contents;

    public UpdateBoardRequestDto(String contents) {
        this.contents = contents;
    }

}
