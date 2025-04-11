package com.example.newsfeed.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// 댓글 생성시 요청 dto
@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private final String contents;

    public CreateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
