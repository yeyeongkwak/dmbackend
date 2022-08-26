package com.spring.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.security.core.CommonResponse;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<CommonResponse> handleLoginFailedException(LoginFailedException e) {

        log.info("handleLoginFailedException");

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.Login_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.Login_FAILED.getStatus())
                .success(ErrorCode.Login_FAILED.getSuccess())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) throws URISyntaxException {

        log.info("handleAccessDeniedException");

//        CommonResponse response = CommonResponse.builder()
//                .code(ErrorCode.ACCESS_DENIED.getCode())
//                .message(ErrorCode.ACCESS_DENIED.getMessage())
//                .status(ErrorCode.ACCESS_DENIED.getStatus())
//                .success(ErrorCode.ACCESS_DENIED.getSuccess())
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        URI redirectUri = new URI("/");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) throws URISyntaxException {

        log.info("handleInsufficientAuthenticationException");

//        CommonResponse response = CommonResponse.builder()
//                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
//                .message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
//                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
//                .success(ErrorCode.AUTHENTICATION_FAILED.getSuccess())
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        URI redirectUri = new URI("/");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }
}
