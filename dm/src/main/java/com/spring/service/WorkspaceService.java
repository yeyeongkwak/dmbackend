package com.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;

public interface WorkspaceService {
	
	public void insertWorkspace(WorkspaceDTO workspaceDTO);
	
	public void deleteWorkspace(Long workspaceNo);
	
	public WorkspaceDTO getWorkspaceByWorkspaceNo(Long workspaceNo);

	public List<WorkspaceUserDTO> updateWorkspace(WorkspaceDTO workspaceNo);

	void updateWorkspace(MultipartFile multipart, WorkspaceDTO workspaceDTO);

}
