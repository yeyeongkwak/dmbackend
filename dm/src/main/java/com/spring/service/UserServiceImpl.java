package com.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.util.S3Util;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final S3Util s3util;
	BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDTO getUserByUserNo(Long userNo) {
		User user = userRepository.getUserByUserNo(userNo);
		return user.toDTO(user);
	}

	@Override
	public UserDTO getUserById(String id) {
		User user = userRepository.getUserById(id);
		return user == null ? null : user.toDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userRepository.findAll().forEach(v -> userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	@Override
	public void insertUser(UserDTO userDTO, MultipartFile profile) {
		System.out.println(userDTO);
		if (getUserById(userDTO.getId()) == null) {
			String newPassword = pwEncoder.encode(userDTO.getPassword());
			userDTO.toEntity(userDTO);
			userDTO.setPassword(newPassword);
			if(!profile.isEmpty() && "image".equals(profile.getContentType().split("/")[0]) ) {
				try {
					s3util.uploadFile("profile/"+userDTO.getId()+".png",profile.getInputStream());
					userDTO.setProfile(s3util.getFileUrl("profile/"+userDTO.getId()+".png"));
				} catch (AwsServiceException | SdkClientException | IOException e) {
					e.printStackTrace();
				}
			}
			userRepository.save(userDTO.toEntity(userDTO));
		}
		else {
			System.out.println("몰루");
		}
	}

	@Override
	public void deleteUserByUserNo(Long userNo) {
		userRepository.deleteById(userNo);
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		UserDTO oldUserDTO = getUserByUserNo(userDTO.getUserNo());
		if (oldUserDTO != null) {
			UserDTO newUserDTO = new UserDTO(userDTO, oldUserDTO);
			userRepository.save(newUserDTO.toEntity(newUserDTO));
		}
	}

	@Override
	public List<UserDTO> findByName(String name) {
		List<User> userList = userRepository.findAllByName(name);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(v -> userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	public List<UserDTO> findByNameAndEmail(String name, String email) {
		List<User> userList = userRepository.findAllByNameAndEmail(name, email);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(v -> userDTOList.add(v.toDTO(v)));
		return userDTOList;
	}

	@Override
	public List<UserDTO> findByIdList(List<Long> userNoList) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userNoList.forEach(v -> {
			UserDTO userDTO = getUserByUserNo(v);
			if (userDTO != null) {
				userDTOList.add(userDTO);
			}
		});
		return userDTOList;
	}

	@Override
	public boolean userEmailCheck(String id, String email) {
		User user = userRepository.findById(id);
		if (user != null && user.getId().equals(id) && user.getEmail().equals(email)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void updateProfile(MultipartFile profile, Long userNo) {
		UserDTO userDTO = getUserByUserNo(userNo);
		if(!profile.isEmpty() && "image".equals(profile.getContentType().split("/")[0]) ) {
			String fileName = "profile/"+UUID.randomUUID().toString()+"_"+userDTO.getId()+".png";
			if(userDTO != null) {
				try {
					if(userDTO.getProfile() != null) {
						s3util.deleteFile("/profile"+userDTO.getProfile().split("profile")[1]);
					}
					s3util.uploadFile(fileName,profile.getInputStream());
					userDTO.setProfile(s3util.getFileUrl(fileName));
				} catch (AwsServiceException | SdkClientException | IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			if(userDTO.getProfile() != null) {
				s3util.deleteFile("/profile"+userDTO.getProfile().split("profile")[1]);
			}
			userDTO.setProfile(null);
		}
		userRepository.save(userDTO.toEntity(userDTO));						
	}
	

	public boolean userPasswordCheck(String id, String oldpw, String pw) {
		User user = userRepository.findById(id);
		if (user != null && user.getId().equals(id) && pwEncoder.matches(oldpw, user.getPassword())) {
			String password = pwEncoder.encode(pw);
			userRepository.updatePassword(password, id);
			return true;
		} else {
			return false;
		}
	}

	public boolean userIdCheck(String id) {
		User user = userRepository.findById(id);
		if(id.isEmpty()) {
			throw new NullPointerException("아이디 검색창 비어있음");
		}
		else {
		if(user == null) {
			return true;
		}else 
			return false;
		}
	}

}
