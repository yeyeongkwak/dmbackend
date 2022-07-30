package com.spring.service;

import java.util.ArrayList;
import java.util.List;

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
	public void insertWorkspaceUserService(WorkspaceUserDTO workspaceUserDTO) {
		workspaceUserRepository.save(workspaceUserDTO.toEntity(workspaceUserDTO));
	}
	
	@Override
	public void insertAllWorkspaceUserService(List<UserDTO> userDTOList,WorkspaceDTO workspaceDTO) {
		List<WorkspaceUser> workspaceUsers = new ArrayList<WorkspaceUser>();
		userDTOList.forEach(v->workspaceUsers.add(WorkspaceUser.builder().workspaceNo(workspaceDTO.toEntity(workspaceDTO)).userNo(v.toEntity(v)).build()));
		workspaceUserRepository.saveAll(workspaceUsers);
	}

	@Override
	public List<WorkspaceUserDTO> getAllWorkspaceUserByUser(Long userNo) {
		List<WorkspaceUser> workspaceUserList = workspaceUserRepository.findAllByUserNoUserNo(userNo);
		List<WorkspaceUserDTO> workspaceUserDTOList = new ArrayList<WorkspaceUserDTO>();
		workspaceUserList.forEach(v->workspaceUserDTOList.add(v.toDTO(v)));
		return workspaceUserDTOList; 
	}

}
