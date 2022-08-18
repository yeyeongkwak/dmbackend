package com.spring.api;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;
import com.spring.security.JwtAuthToken;
import com.spring.security.JwtAuthTokenProvider;
import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userService;
	private final JwtAuthTokenProvider jwtAuthProvider;
	private final PasswordEncoder passwordencoder;
	
	@PostMapping("/signup")
	public void insertUser(@Valid @RequestBody UserDTO user) {
		userService.insertUser(user);
	}
	
	@PostMapping("/login")
	public UserDTO loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
		UserDTO oldUserDTO= userService.getUserById(userDTO.getId());
		if (passwordencoder.matches(userDTO.getPassword(),oldUserDTO.getPassword())) {
			JwtAuthToken jwtAuthToken = jwtAuthProvider.createAuthToken(userDTO.getId(), "MN00001",
					Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant()));

			Cookie createCookie = new Cookie("accessToken", jwtAuthToken.getToken());
			createCookie.setMaxAge(24 * 60 * 60);
			createCookie.setPath("/");
			response.addCookie(createCookie);
			oldUserDTO.setPassword(null);
			return oldUserDTO; 
		} else {
			System.out.println("failed");
			return null;
		}

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if( auth !=null && auth.isAuthenticated()) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			System.out.println("로그아웃");
		}
		return "Redirect:/";
		
	}
	
	@GetMapping(value = "/user/{userNo}")
	public UserDTO getUserByUserNo(@PathVariable Long userNo) {
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
	
	@PutMapping(value = "/profile/{userNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProfile(@RequestBody UserDTO userDTO,@PathVariable Long userNo) {
		userDTO.setUserNo(userNo);
		userService.updateUser(userDTO);
	}
	
	@GetMapping(value = "/user/name/{name}")
	public List<UserDTO> findByName(@PathVariable String name){
		return userService.findByName(name);
	}
	
	@PostMapping(value ="/user/member", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getMemberList(@RequestBody List<Long> userNoList){
		return userService.findByIdList(userNoList);
	}
}
