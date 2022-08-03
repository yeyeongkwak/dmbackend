package com.spring.security.core;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Role {
	ADMIN("MN00001", "관리자권한"),
    USER("MN00002", "사용자권한"),
    CLIENT("MN00003", "클라이언트"),
    UNKNOWN("UNKNOWN", "알수없는 권한");

    private String code;
    private String description;

    Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Role of(String code) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }
}
