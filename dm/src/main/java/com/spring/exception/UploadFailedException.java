package com.spring.exception;

import java.time.ZonedDateTime;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadFailedException extends Exception{
	
	public UploadFailedException(String message) {
		super(message);
	}
	
}

