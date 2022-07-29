package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;
import com.spring.entity.User;

public interface UserService {
	
	public User getUserByUserNo(Long userNo);
	
	public List<User> getAllUser();
	
	public void insertUser(User user);
	
	public void deleteUserByUserNo(Long userNo);
}
