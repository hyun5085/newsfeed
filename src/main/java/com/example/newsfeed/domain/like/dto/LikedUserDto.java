package com.example.newsfeed.domain.like.dto;

import com.example.newsfeed.domain.like.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikedUserDto {
    private Long userId;
    private String username;

    public static LikedUserDto from(Like like){
        return new LikedUserDto(
                like.getUser().getId(),
                like.getUser().getUsername()
        );
    }
}
