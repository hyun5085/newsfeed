package com.example.newsfeed.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    public ErrorResponse(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status.name();
        this.timestamp = LocalDateTime.now();
    }

}
