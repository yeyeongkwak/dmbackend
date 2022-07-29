package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.dto.WorkspaceDTO;
import com.spring.entity.Workspace;
import com.spring.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
	
	private final WorkspaceRepository workspaceRepository;

	@Override
	public void insertWorkspace(WorkspaceDTO workspaceDTO) {
		if(getWorkspaceByWorkspaceNo(workspaceDTO.getWorkspaceNo()) == null) {
			workspaceRepository.save(workspaceDTO.toEntity(workspaceDTO));
		}
	}

	@Override
	public void deleteWorkspace(Long workspaceNo) {
		workspaceRepository.deleteById(workspaceNo);
	}

	@Override
	public WorkspaceDTO getWorkspaceByWorkspaceNo(Long workspaceNo) {
		Workspace workspace = workspaceRepository.getWorkspaceByWorkspaceNo(workspaceNo);
		return workspace.toDTO(workspace);
	}
	
	@Override
	public void updateWorkspace(WorkspaceDTO workspaceDTO) {
		WorkspaceDTO oldWorkspace = getWorkspaceByWorkspaceNo(workspaceDTO.getWorkspaceNo());
		if(oldWorkspace != null) {
			WorkspaceDTO newWorkspaceDTO = new WorkspaceDTO(workspaceDTO,oldWorkspace);
			workspaceRepository.save(newWorkspaceDTO.toEntity(newWorkspaceDTO));
		}
	}
	
	
}
