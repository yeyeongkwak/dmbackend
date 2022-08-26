package com.spring.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "accessToken";

 // 실제 JWT 검증을 실행하는 Provider
    private JwtAuthTokenProvider jwtAuthTokenProvider;


    JwtFilter(JwtAuthTokenProvider jwtAuthTokenProvider) {
        this.jwtAuthTokenProvider = jwtAuthTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Optional<String> token = resolveToken(httpServletRequest);

        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());

            Authentication authentication = jwtAuthTokenProvider.getAuthentication(jwtAuthToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);


        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, AUTHORIZATION_HEADER);
        String authToken = null;
        if (cookie != null) {
            authToken = cookie.getValue();
        } else {
            authToken = request.getHeader(AUTHORIZATION_HEADER);
        }

        if (StringUtils.hasText(authToken)) {
            return Optional.of(authToken);
        } else {
            return Optional.empty();
        }
    }

}
