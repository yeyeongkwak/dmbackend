package com.spring.dm;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.sun.security.auth.UserPrincipal;



public class UserHandshakeHandler  extends DefaultHandshakeHandler{

	private final Logger log = LoggerFactory.getLogger(UserHandshakeHandler.class);
	
	
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
		String randomId = UUID.randomUUID().toString();
		log.info("User with Id '{}'opened the page", randomId);
		System.out.println(randomId);
		return new UserPrincipal(randomId);
	}
}
