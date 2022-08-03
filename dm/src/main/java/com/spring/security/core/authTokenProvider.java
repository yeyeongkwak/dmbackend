package com.spring.security.core;

import org.springframework.security.core.Authentication;

import java.util.Date;

public interface authTokenProvider<T> {
    T createAuthToken(String id, String role, Date expiredDate);
    T convertAuthToken(String token);
    Authentication getAuthentication(T authToken);
}


