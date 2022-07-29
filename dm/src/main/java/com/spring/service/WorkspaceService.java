package com.spring.service;

import com.spring.entity.Workspace;

public interface WorkspaceService {
	
	public void insertWorkspace(Workspace workspace);
	
	public void deleteWorkspace(Long workspaceNo);
	
	public Workspace getWorkspaceByWorkspaceNo(Long workspaceNo);
	
}
