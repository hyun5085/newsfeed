package com.example.newsfeed.boards.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final Long id;
    private final String contents;
    private final String username;

    public CreateBoardRequestDto(Long id, String contents, String username) {
        this.id = id;
        this.contents = contents;
        this.username = username;
    }

}
