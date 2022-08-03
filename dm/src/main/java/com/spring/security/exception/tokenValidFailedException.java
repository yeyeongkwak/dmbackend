package com.spring.security.exception;


public class tokenValidFailedException extends RuntimeException{
    public tokenValidFailedException(){
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }

    private tokenValidFailedException(String msg){
        super(msg);
    }
}
