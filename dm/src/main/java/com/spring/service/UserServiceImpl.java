package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dto.UserDTO;
import com.spring.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	@Override
	public String getUserNoByUserNo(Long userNo) {
		return userMapper.getUserNoByUserNo(userNo);
	}

	@Override
	public UserDTO getUserByUserNo(Long userNo) {
		return userMapper.getUserByUserNo(userNo);
	}

	@Override
	public List<UserDTO> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	public void insertUser(UserDTO user) {
		userMapper.insertUser(user);
	}

	@Override
	public void deleteUserByUserNo(Long userNo) {
		userMapper.deleteUserByUserNo(userNo);
	}

}
