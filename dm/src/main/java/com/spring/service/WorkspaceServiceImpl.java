package com.spring.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.WorkspaceDTO;
import com.spring.entity.Workspace;
import com.spring.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
	
	private final WorkspaceRepository workspaceRepository;
	private final WorkspaceUserServiceImpl workspaceUserService;

	@Override
	@Transactional
	public void insertWorkspace(WorkspaceDTO workspaceDTO) {
		Workspace workspace = workspaceRepository.save(workspaceDTO.toEntity(workspaceDTO));
		workspaceDTO.getUserList().add(workspaceDTO.getMaster());
		workspaceUserService.insertAllWorkspaceUserService(workspaceDTO.getUserList(),workspace.toDTO(workspace));
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
			System.out.println(newWorkspaceDTO);
			workspaceRepository.save(newWorkspaceDTO.toEntity(newWorkspaceDTO));
		}
	}
	
	
}
