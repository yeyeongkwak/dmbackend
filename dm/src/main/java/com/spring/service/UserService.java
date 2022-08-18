package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;

public interface UserService {
	
	public UserDTO getUserByUserNo(Long userNo);
	
	public UserDTO getUserById(String id);
	
	public List<UserDTO> getAllUser();
	
	public void insertUser(UserDTO userDTO);
	
	public void deleteUserByUserNo(Long userNo);

	public void updateUser(UserDTO userDTO);

	public List<UserDTO> findByName(String name);

	public List<UserDTO> findByIdList(List<Long> userNoList);

}
