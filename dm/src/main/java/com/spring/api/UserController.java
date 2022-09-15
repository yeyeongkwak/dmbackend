package com.spring.api;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@PostMapping("/signup")
	public void insertUser(@Valid @RequestPart("user") UserDTO user, @RequestPart("profile") MultipartFile profile) {
		userService.insertUser(user,profile);
	}
	@PostMapping("/login")
	public UserDTO loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
		UserDTO oldUserDTO = userService.getUserById(userDTO.getId());
		if (oldUserDTO !=null && passwordEncoder.matches(userDTO.getPassword(), oldUserDTO.getPassword())) {
			JwtAuthToken jwtAuthToken = jwtAuthProvider.createAuthToken(userDTO.getId(), "MN00001", // 나중에 role바꿔야됨
			Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant()));
//			Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()));
			// 위에 이게 토큰 시간
			Cookie createCookie = new Cookie("accessToken", jwtAuthToken.getToken());
			createCookie.setMaxAge(24 * 60 * 60); // 쿠키 지속 시간
			createCookie.setPath("/"); // 모든 경로에서 사용하겠다는 뜻
			response.addCookie(createCookie);
			oldUserDTO.setPassword(null);
			return oldUserDTO;
		} else {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		}
	}
	
//쿠키값 호출해서 id추출,이후 마이페이지로돌릴애들
	@GetMapping("/mypage")
	public UserDTO myPage(@CookieValue(name = "accessToken") Cookie showCookie) {
		JwtAuthToken jwtAuth = jwtAuthProvider.convertAuthToken(showCookie.getValue()); // 토큰 가져와서 복호화
		System.out.println(jwtAuth.getData());
		String printCookie = jwtAuth.getData().getSubject();
		System.out.println(printCookie);
		return userService.getUserById(printCookie);
	}

	@GetMapping("/checkpassword")
	public @ResponseBody Map<String, Boolean> checkPassword(@RequestParam(value = "oldpw") String oldpw,
			@RequestParam(value = "pw") String pw, @CookieValue(name = "accessToken") Cookie checkCookie) {
		Map<String, Boolean> verify = new HashMap<>();
		JwtAuthToken jwtAuth = jwtAuthProvider.convertAuthToken(checkCookie.getValue());
		String id = jwtAuth.getData().getSubject();
		System.out.println(id);
		boolean pwVerifyCheck = userService.userPasswordCheck(id, oldpw, pw);
		verify.put("check", pwVerifyCheck);
		return verify;
	}

	
	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			System.out.println("로그아웃");
		}
	}
	
	
	@GetMapping("/checkuser")
	public @ResponseBody Map<String, Boolean> checkUser(@RequestParam(value = "id") String id) {
		Map<String, Boolean> verifyUser = new HashMap<>();
	
			boolean userCheck = userService.userIdCheck(id);
			verifyUser.put("check", userCheck);
			return verifyUser;
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
	public List<UserDTO> getAllUser() {

		return userService.getAllUser();
	}

	@PostMapping(value = "/profile/{userNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void updateProfile(@RequestPart("profile") MultipartFile profile, @PathVariable Long userNo) {
		userService.updateProfile(profile, userNo);
	}

	@GetMapping(value = "/user/name/{name}")
	public List<UserDTO> findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	@PostMapping(value = "/user/member", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getMemberList(@RequestBody List<Long> userNoList) {
		return userService.findByIdList(userNoList);
	}
}
