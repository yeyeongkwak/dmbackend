package com.spring.service;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.WorkspaceDTO;

public interface WorkspaceService {
	
	public void insertWorkspace(WorkspaceDTO workspaceDTO);
	
	public void deleteWorkspace(Long workspaceNo);
	
	public WorkspaceDTO getWorkspaceByWorkspaceNo(Long workspaceNo);

	public void updateWorkspace(WorkspaceDTO workspaceNo);

	void updateWorkspace(MultipartFile multipart, Long workspaceNo);

}
