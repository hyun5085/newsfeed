package com.example.newsfeed.domain.follow.dto.response;

import lombok.Getter;

@Getter
public class UnfollowResponseDto {


    private String message; // 메세지

    public UnfollowResponseDto(String message) {
        this.message = message;

    }
}
