package com.example.newsfeed.domain.follow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowListResponseDto {
    private Long userId;
    private String email;

}
