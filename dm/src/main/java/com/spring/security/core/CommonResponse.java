package com.spring.security.core;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class CommonResponse {
    private String message;
    private Integer status;
    private Boolean success;
    private String code;
}
