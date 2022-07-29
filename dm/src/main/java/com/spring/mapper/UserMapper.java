package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	public String getUserNoByUserNo(Long userNo);
	
	public UserDTO getUserByUserNo(Long userNo);
	
	public List<UserDTO> getAllUser();
	
	public void insertUser(UserDTO user);
	
	public void deleteUserByUserNo(Long userNo);	
}
