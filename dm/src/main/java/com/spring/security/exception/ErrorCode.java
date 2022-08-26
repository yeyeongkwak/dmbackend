package com.spring.security.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    AUTHENTICATION_FAILED(401, "AUTH_001", "AUTHENTICATION_FAILED.", false),
    Login_FAILED(400, "AUTH_002", "Login_FAILED.", false),
    ACCESS_DENIED(403, "AUTH_003", "ACCESS_DENIED." , false),
    TOKEN_GENERATION_FAILED(500, "AUTH_004", "TOKEN_GENERATION_FAILED.", false);


    private final String code;
    private final String message;
    private final Boolean success;
    private int status;

    ErrorCode(final int status, final String code, final String message ,final Boolean success) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.success = success;
    }

}
