package com.spring.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.TempFileDTO;
import com.spring.dto.WorkspaceDTO;
import com.spring.entity.Workspace;
import com.spring.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
	
	private final WorkspaceRepository workspaceRepository;
	private final WorkspaceUserServiceImpl workspaceUserService;
	private final TempFileServiceImpl tempFileService;

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
	
	// 제목변경시 사용
	@Override
	@Transactional
	public void updateWorkspace(WorkspaceDTO workspaceDTO) {
		WorkspaceDTO oldWorkspace = getWorkspaceByWorkspaceNo(workspaceDTO.getWorkspaceNo());
		if(oldWorkspace != null) {
			WorkspaceDTO newWorkspaceDTO = new WorkspaceDTO(workspaceDTO,oldWorkspace);
			workspaceRepository.save(newWorkspaceDTO.toEntity(newWorkspaceDTO));
		}
	}

	// 파일 임시저장되었을시
	@Override
	@Transactional
	public void updateWorkspace(MultipartFile multipart,Long workspaceNo) {
		WorkspaceDTO workspaceDTO = getWorkspaceByWorkspaceNo(workspaceNo);
		TempFileDTO tempFileDTO = tempFileService.insertTempFile(multipart,workspaceDTO.getTempFile());	
		if(tempFileDTO != null) {
			workspaceDTO.setTempFile(tempFileDTO);
			workspaceRepository.save(workspaceDTO.toEntity(workspaceDTO));
		}
	}
	
	
}
