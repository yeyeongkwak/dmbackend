package com.spring.api;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;
import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
	private final UserServiceImpl userService;
	
	@GetMapping(value = "/user/{userNo}")
	public UserDTO getUserByUserNo(@PathVariable Long userNo) {
		System.out.println(userNo);
		UserDTO userDTO = userService.getUserByUserNo(userNo);
		return userDTO;
	}
	
	@GetMapping(value = "/user/id/{id}")
	public UserDTO getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(value = "/alluser")
	public List<UserDTO> getAllUser(){

		return userService.getAllUser();
	}
	
	@PostMapping(value = "/join",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertUser(@RequestBody UserDTO userDTO) {
		userService.insertUser(userDTO);	
	}
	
	@PutMapping(value = "/profile/{userNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProfile(@RequestBody UserDTO userDTO,@PathVariable Long userNo) {
		userDTO.setUserNo(userNo);
		userService.updateUser(userDTO);
	}
	
}
