package com.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.DepartmentDTO;
import com.spring.dto.UserDTO;

public interface UserService {
	
	public UserDTO getUserByUserNo(Long userNo);
	
	public UserDTO getUserById(String id);
	
	public List<UserDTO> getAllUser();
	
	public void insertUser(UserDTO userDTO, MultipartFile profile);
	
	public void deleteUserByUserNo(Long userNo);

	public void updateUser(UserDTO userDTO);

	public List<UserDTO> findByName(String name);
	
	public List<UserDTO> findByNameAndEmail(String name, String email);

	public List<UserDTO> findByIdList(List<Long> userNoList);
	
	public boolean userEmailCheck(String name, String email);
  
	public void updateProfile(MultipartFile profile, Long userNo);

}
