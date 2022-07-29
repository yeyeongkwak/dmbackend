package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;

public interface UserService {
	
	public String getUserNoByUserNo(Long userNo);
	
	public UserDTO getUserByUserNo(Long userNo);
	
	public List<UserDTO> getAllUser();
	
	public void insertUser(UserDTO user);
	
	public void deleteUserByUserNo(Long userNo);
}
