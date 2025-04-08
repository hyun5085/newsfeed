package com.example.newsfeed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("C001", "해당 유저는 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("C002", "비밀번호가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
    ALREADY_LOGGED_IN("C003", "이미 로그인된 상태입니다.", HttpStatus.BAD_REQUEST);


    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}

