package com.spring.mapper;

import com.spring.dto.WorkspaceDTO;

public interface WorkspaceMapper {
	
	public void insertWorkspace(WorkspaceDTO workspace);
	
	public void deleteWorkspace(Long workspaceNo);
	
	public WorkspaceDTO getWorkspaceByWorkspaceNo(Long workspaceNo);
	
}
