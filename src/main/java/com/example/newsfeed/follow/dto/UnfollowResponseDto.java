package com.example.newsfeed.follow.dto;

import lombok.Getter;

@Getter
public class UnfollowResponseDto {

    private String message;

    public UnfollowResponseDto(String message) {
        this.message = message;
    }
}
