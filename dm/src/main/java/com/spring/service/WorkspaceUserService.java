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

	public void deleteAllWorkspaceUser(List<WorkspaceDTO> workspaceList, Long userNo);

	public List<WorkspaceUserDTO> getMemberList(Long workspaceNo);
	
	
	
}
