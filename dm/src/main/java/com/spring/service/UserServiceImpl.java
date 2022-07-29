package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public User getUserByUserNo(Long userNo) {
		return userRepository.getUserByUserNo(userNo);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void insertUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUserByUserNo(Long userNo) {
		userRepository.deleteById(userNo);
	}

}
