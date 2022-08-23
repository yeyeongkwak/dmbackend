package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDTO getUserByUserNo(Long userNo) {
		User user = userRepository.getUserByUserNo(userNo);
		return user.toDTO(user);
	}
	
	@Override
	public UserDTO getUserById(String id) {
		User user = userRepository.getUserById(id);
		return user == null?null:user.toDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userRepository.findAll().forEach(v->userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	@Override
	public void insertUser(UserDTO userDTO) {
		System.out.println(userDTO);
		if(getUserById(userDTO.getId()) == null) {
			String newPassword = pwEncoder.encode(userDTO.getPassword());
			userDTO.toEntity(userDTO);
			userDTO.setPassword(newPassword);
			userRepository.save(userDTO.toEntity(userDTO));
		}
	}

	@Override
	public void deleteUserByUserNo(Long userNo) {
		userRepository.deleteById(userNo);
	}
	
	@Override
	public void updateUser(UserDTO userDTO) {
		UserDTO oldUserDTO = getUserByUserNo(userDTO.getUserNo());
		if(oldUserDTO != null) {
			System.out.println("11");
			UserDTO newUserDTO = new UserDTO(userDTO,oldUserDTO);
			userRepository.save(newUserDTO.toEntity(newUserDTO));
		}
	}
	
	@Override
	public List<UserDTO> findByName(String name){
		List<User> userList = userRepository.findAllByName(name);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(v->userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}
	
	public List<UserDTO> findByNameAndEmail(String name, String email){
		List<User> userList = userRepository.findAllByNameAndEmail(name, email);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(v->userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	public List<UserDTO> findByIdAndEmail(String id, String email) {
		List<User> userList = userRepository.findAllByIdAndEmail(id, email);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(v->userDTOList.add(v.toDTO(v)));
	@Override
	public List<UserDTO> findByIdList(List<Long> userNoList){
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userNoList.forEach(v -> {UserDTO userDTO = getUserByUserNo(v);
								if(userDTO !=  null) {
									userDTOList.add(userDTO);
									}});
		return userDTOList;
	}

}
