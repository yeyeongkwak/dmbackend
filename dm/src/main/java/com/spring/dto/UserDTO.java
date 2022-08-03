package com.spring.dto;



import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.spring.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Long userNo;
	
	private DepartmentDTO dept;
	
	private String id;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private LocalDateTime registerDate;
	
	private String profile;
	
	public User toEntity(UserDTO userDTO) {
		User user = User.builder()
					.userNo(userDTO.getUserNo())
					.dept(userDTO.getDept().toEntity(userDTO.getDept()))
					.id(userDTO.getId())
					.password(userDTO.getPassword())
					.name(userDTO.getName())
					.email(userDTO.getEmail())
					.registerDate(userDTO.getRegisterDate())
					.profile(userDTO.getProfile())
					.build();
		return user;
	}
	
	public UserDTO(UserDTO newUser,UserDTO oldUser) {
		userNo = oldUser.getUserNo();
		dept = oldUser.getDept();
		id = oldUser.getId();
		name = oldUser.getName();
		email = oldUser.getEmail();
		registerDate = oldUser.getRegisterDate();
		if(newUser.getPassword() != null) {
			password = newUser.getPassword();
		}else {
			password = oldUser.getPassword();
		}
		if(newUser.getProfile() != null) {
			profile = newUser.getProfile();
		}else {
			profile = oldUser.getProfile();
		}
	}
	
}
