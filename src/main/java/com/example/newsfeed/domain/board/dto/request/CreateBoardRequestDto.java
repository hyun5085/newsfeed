package com.example.newsfeed.domain.board.dto.request;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final Long id;
    private final String contents;
    private final Long userId;

    public CreateBoardRequestDto(Long id, String contents, Long userId) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
    }

}
