package com.example.newsfeed.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //comment
    COMMENT_NOT_FOUND("CM001", "해당 댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    COMMENT_CONTENT_EMPTY("CM002", "댓글 내용을 입력해주세요.", HttpStatus.BAD_REQUEST),
    COMMENT_UPDATE_UNAUTHORIZED("CM003", "댓글 작성자만 수정할 수 있습니다.", HttpStatus.FORBIDDEN),
    COMMENT_DELETE_UNAUTHORIZED("CM004", "댓글 또는 게시글 작성자만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN),
    COMMENT_NOT_BELONG_TO_BOARD("CM005", "댓글이 해당 게시글에 속하지 않습니다.", HttpStatus.BAD_REQUEST),

    //board
    BOARD_NOT_FOUND("B001", "해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    BOARD_CREATE_UNAUTHORIZED("B002", "해당 로그인된 사용자만 게시글을 생성할 수 있습니다.", HttpStatus.UNAUTHORIZED),
    BOARD_UPDATE_UNAUTHORIZED("B003", "해당 게시글의 작성자만 수정할 수 있습니다.", HttpStatus.UNAUTHORIZED),
    BOARD_DELETE_UNAUTHORIZED("B004", "해당 게시글의 작성자만 삭제할 수 있습니다.", HttpStatus.UNAUTHORIZED),

    //authorized
    UNAUTHORIZED("AU001", "로그인이 필요한 요청입니다.", HttpStatus.UNAUTHORIZED),

    //user
    USER_NOT_FOUND("L001", "해당 유저는 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("L002", "비밀번호가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
    INCORRECT_PASSWORD("L003", "기존 비밀번호가 일치하지 않습니다.", HttpStatus.FORBIDDEN),
    ALREADY_LOGGED_IN("L004", "이미 로그인된 상태입니다.", HttpStatus.BAD_REQUEST),

    //user
    EMAIL_ALREADY_RETIRED("U001", "해당 이메일은 재사용할 수 없습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATED("U001", "이미 사용자가 있습니다.", HttpStatus.BAD_REQUEST),

    //follow
    FOLLOW_LOGIN_USER("F001", "본인의 이메일입니다.", HttpStatus.BAD_REQUEST),
    FOLLOW_NOT_FOUND("F002", "이메일이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    FOLLOW_ALREADY_FOLLOW("F003", "이미 팔로우중 입니다.", HttpStatus.ALREADY_REPORTED),
//    FOLLOW_NONE_FOLLOW("F004", "팔로우 관계가 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    //Like
    CANNOT_LIKE_OWN_COMMENT("L01","본인의 댓글에는 좋아요를 누를 수 없습니다.", HttpStatus.FORBIDDEN),
    ALREADY_LIKED("L02","이미 좋아요를 누르셨습니다.",HttpStatus.BAD_REQUEST);



    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}

