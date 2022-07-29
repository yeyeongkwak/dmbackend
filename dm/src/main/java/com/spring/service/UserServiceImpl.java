package com.spring.service;

import java.util.ArrayList;
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
	public UserDTO getUserByUserNo(Long userNo) {
		User user = userRepository.getUserByUserNo(userNo);
		return user.toDTO(user);
	}
	
	@Override
	public UserDTO getUserById(String id) {
		User user = userRepository.getUserById(id);
		return user.toDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userRepository.findAll().forEach(v->userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	@Override
	public void insertUser(UserDTO userDTO) {
		if(getUserById(userDTO.getId()) == null) {
			userRepository.save(userDTO.toEntity(userDTO));
		}
	}

	@Override
	public void deleteUserByUserNo(Long userNo) {
		userRepository.deleteById(userNo);
	}
	
	@Override
	public void updateUser(UserDTO userDTO) {
		UserDTO oldUserDTO = getUserById(userDTO.getId());
		if(oldUserDTO != null) {
			UserDTO newUserDTO = new UserDTO(userDTO,oldUserDTO);
			userRepository.save(newUserDTO.toEntity(newUserDTO));
		}
	}

}
