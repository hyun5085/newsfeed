package com.example.newsfeed.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// 댓글 수정시 요청 dto
@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private final String contents;

    public CommentUpdateRequestDto(String contents) {
        this.contents = contents;
    }
}
