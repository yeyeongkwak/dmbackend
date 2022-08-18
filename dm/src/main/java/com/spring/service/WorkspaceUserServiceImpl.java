package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.dto.UserDTO;
import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;
import com.spring.entity.WorkspaceUser;
import com.spring.repository.UserRepository;
import com.spring.repository.WorkspaceUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceUserServiceImpl implements WorkspaceUserService{
	
	private final WorkspaceUserRepository workspaceUserRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void insertWorkspaceUserService(WorkspaceUserDTO workspaceUserDTO) {
		workspaceUserRepository.save(workspaceUserDTO.toEntity(workspaceUserDTO));
	}
	
	@Override
	@Transactional
	public List<WorkspaceUserDTO> insertAllWorkspaceUserService(List<UserDTO> userDTOList,WorkspaceDTO workspaceDTO) {
		List<WorkspaceUser> workspaceUsers = new ArrayList<WorkspaceUser>();
		userDTOList.forEach(v->workspaceUsers.add(WorkspaceUser.builder().workspaceNo(workspaceDTO.toEntity(workspaceDTO)).userNo(v.toEntity(v)).build()));
		workspaceUserRepository.saveAll(workspaceUsers);
		return getAllWorkspaceUserByUser(workspaceDTO.getMaster().getUserNo());
	}

	@Override
	@Transactional
	public List<WorkspaceUserDTO> getAllWorkspaceUserByUser(Long userNo) {
		List<WorkspaceUser> workspaceUserList = workspaceUserRepository.findAllByUserNoUserNo(userNo); 
		List<WorkspaceUserDTO> workspaceUserDTOList = new ArrayList<WorkspaceUserDTO>();
//		UserDTO member = null;
//		List<UserDTO> memeberList = new ArrayList<UserDTO>();
//		workspaceUserList.forEach(v->workspaceUserDTOList.add(v.toDTO(v)));
		workspaceUserList.forEach(v->workspaceUserDTOList.add(v.toDTO(v)));
		workspaceUserDTOList.forEach(v-> v.setMember(workspaceUserRepository.findUserByWorkspaceUser(v.getWorkspaceNo().getWorkspaceNo())));
//		return workspaceUserDTOList;
		return workspaceUserDTOList;
	}
	
	@Override
	@Transactional
	public List<WorkspaceUserDTO> deleteWorkspaceUser(Long userNo, Long workspaceNo) {
		workspaceUserRepository.deleteByUserNoUserNoAndWorkspaceNoWorkspaceNo(userNo, workspaceNo);
		return getAllWorkspaceUserByUser(userNo);
	}
	
	@Override
	@Transactional
	public List<WorkspaceUserDTO> deleteAllWorkspaceUser(List<Long> workspaceNoList, Long userNo) {
		workspaceNoList.forEach(workspaceNo-> workspaceUserRepository.deleteByUserNoUserNoAndWorkspaceNoWorkspaceNo(userNo, workspaceNo));
		
		return getAllWorkspaceUserByUser(userNo);
	}

}
