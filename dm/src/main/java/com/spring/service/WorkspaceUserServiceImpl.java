package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.dto.UserDTO;
import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;
import com.spring.entity.WorkspaceUser;
import com.spring.repository.WorkspaceUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceUserServiceImpl implements WorkspaceUserService{
	
	private final WorkspaceUserRepository workspaceUserRepository;

	@Override
	@Transactional
	public void insertWorkspaceUserService(WorkspaceUserDTO workspaceUserDTO) {
		workspaceUserRepository.save(workspaceUserDTO.toEntity(workspaceUserDTO));
	}
	
	@Override
	@Transactional
	public void insertAllWorkspaceUserService(List<UserDTO> userDTOList,WorkspaceDTO workspaceDTO) {
		List<WorkspaceUser> workspaceUsers = new ArrayList<WorkspaceUser>();
		userDTOList.forEach(v->workspaceUsers.add(WorkspaceUser.builder().workspaceNo(workspaceDTO.toEntity(workspaceDTO)).userNo(v.toEntity(v)).build()));
		workspaceUserRepository.saveAll(workspaceUsers);
	}

	@Override
	@Transactional
	public List<WorkspaceUserDTO> getAllWorkspaceUserByUser(Long userNo) {
		List<WorkspaceUser> workspaceUserList = workspaceUserRepository.findAllByUserNoUserNo(userNo);
		System.out.println(userNo);
		List<WorkspaceUserDTO> workspaceUserDTOList = new ArrayList<WorkspaceUserDTO>();
		workspaceUserList.forEach(v->workspaceUserDTOList.add(v.toDTO(v)));
		return workspaceUserDTOList; 
	}
	
	@Override
	@Transactional
	public void deleteWorkspaceUser(Long userNo, Long workspaceNo) {
		workspaceUserRepository.deleteByUserNoUserNoAndWorkspaceNoWorkspaceNo(userNo, workspaceNo);
	}

}
