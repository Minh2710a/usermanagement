package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND(
            1001,
            "User not found",
            HttpStatus.NOT_FOUND
    ),

    USERNAME_ALREADY_EXISTS(
            1003,
            "Username already exists",
            HttpStatus.CONFLICT
    ),

    EMAIL_ALREADY_EXISTS(
            1004,
            "Email already exists",
            HttpStatus.CONFLICT
    ),

    UNCATEGORIZED_EXCEPTION(
            9999,
            "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(
            int code,
            String message,
            HttpStatus statusCode
    ) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}