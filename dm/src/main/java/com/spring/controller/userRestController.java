package com.spring.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.security.JwtAuthToken;
import com.spring.security.JwtAuthTokenProvider;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class userRestController {
	
private final UserService userService;

private final JwtAuthTokenProvider jwtAuthProvider;
private final PasswordEncoder passwordencoder;
private final UserRepository userRepo;

	@PostMapping("/signUp")
	public void insertUser(@Valid @RequestBody UserDTO user) {
		userService.insertUser(user);
	}
	
	@PostMapping("/login")
	public Map<String, Object> loginUser(@RequestBody User user, HttpServletResponse response) {
		
		if (passwordencoder.matches(user.getPassword(), userRepo.findById(user.getUserNo()).get().getPassword())) {
			JwtAuthToken jwtAuthToken = jwtAuthProvider.createAuthToken(user.getId(), "MN00001",
					Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant()));

			Cookie createCookie = new Cookie("accessToken", jwtAuthToken.getToken());
			createCookie.setMaxAge(3 * 60 * 60);
			createCookie.setPath("/");
			response.addCookie(createCookie);
		} else {
			System.out.println("failed");
		}

		return null;

	}

	
	@GetMapping("/sucessLogin")
	public Map<String, Object> sucessLogin(){
		System.out.println("로그인성공");
		return null;
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
		
}
