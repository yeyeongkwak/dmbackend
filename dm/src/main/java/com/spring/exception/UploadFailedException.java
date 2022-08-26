package com.spring.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UploadFailedException extends Exception{
	
	public UploadFailedException(String message) {
		super(message);
	}
	
}
