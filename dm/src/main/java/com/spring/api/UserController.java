package com.spring.api;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;

import com.spring.entity.User;

import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
	private final UserServiceImpl userService;
	
	@GetMapping(value = "/user/{userNo}")

	public User getUserByUserNo(@PathVariable Long userNo) {
		User user = userService.getUserByUserNo(userNo);
		return user;
	}
	
	@GetMapping(value = "/alluser")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@PostMapping(value = "/join",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertUser(@RequestBody User user) {
//		userService.insertUser(user);	
	}
	
}
