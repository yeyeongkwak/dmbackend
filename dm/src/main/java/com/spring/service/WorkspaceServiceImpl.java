package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.dto.WorkspaceDTO;
import com.spring.entity.Workspace;
import com.spring.mapper.WorkspaceMapper;
import com.spring.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
	
	private final WorkspaceRepository workspaceRepository;

	@Override
	public void insertWorkspace(Workspace workspace) {
		workspaceRepository.save(workspace);
	}

	@Override
	public void deleteWorkspace(Long workspaceNo) {
		workspaceRepository.deleteById(workspaceNo);
	}

	@Override
	public Workspace getWorkspaceByWorkspaceNo(Long workspaceNo) {
		return workspaceRepository.getWorkspaceByWorkspaceNo(workspaceNo);
	}
	
	
}
