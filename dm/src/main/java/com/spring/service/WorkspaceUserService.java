package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;
import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;

public interface WorkspaceUserService {
	
	public void insertWorkspaceUserService(WorkspaceUserDTO workspacesUserDTO);
	
	public List<WorkspaceUserDTO> getAllWorkspaceUserByUser(Long userNo);

	public List<WorkspaceUserDTO> insertAllWorkspaceUserService(List<UserDTO> userDTOList,WorkspaceDTO workspaceDTO);

	public List<WorkspaceUserDTO> deleteWorkspaceUser(Long userNo, Long workspaceNo);

	public List<WorkspaceUserDTO> deleteAllWorkspaceUser(List<Long> workspaceNoList, Long userNo);
	
	
	
}
