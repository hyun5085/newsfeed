package com.example.newsfeed.domain.board.dto.response;

import com.example.newsfeed.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {

    private final Long id;
    private final String contents;
    private final Long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public FeedResponseDto(Long id, String contents, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static FeedResponseDto toDto(Board board) {
        return new FeedResponseDto(board.getId(), board.getContents(), board.getUser().getId(), board.getCreatedAt(), board.getUpdatedAt());
    }

}
