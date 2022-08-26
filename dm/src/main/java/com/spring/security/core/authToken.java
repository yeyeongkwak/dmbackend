package com.spring.security.core;

public interface authToken<T> {
	boolean validate();
    T getData();
}
