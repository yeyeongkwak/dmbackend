package com.spring.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.TempFileDTO;
import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;
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
	public List<WorkspaceUserDTO> insertWorkspace(WorkspaceDTO workspaceDTO) {
		Workspace workspace = workspaceRepository.save(workspaceDTO.toEntity(workspaceDTO));
		workspaceDTO.getUserList().add(workspaceDTO.getMaster());
		return workspaceUserService.insertAllWorkspaceUserService(workspaceDTO.getUserList(),workspace.toDTO(workspace));
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
	public List<WorkspaceUserDTO> updateWorkspace(WorkspaceDTO workspaceDTO) {
		WorkspaceDTO oldWorkspace = getWorkspaceByWorkspaceNo(workspaceDTO.getWorkspaceNo());
		if(oldWorkspace != null) {
			WorkspaceDTO newWorkspaceDTO = new WorkspaceDTO(workspaceDTO,oldWorkspace);
			workspaceRepository.save(newWorkspaceDTO.toEntity(newWorkspaceDTO));
			return workspaceUserService.getAllWorkspaceUserByUser(oldWorkspace.getMaster().getUserNo());
		}
		return null;
	}

	// 파일 임시저장되었을시
	@Override
	@Transactional
	public void updateWorkspace(MultipartFile multipart,WorkspaceDTO workspaceDTO) {
		
		TempFileDTO tempFileDTO = tempFileService.insertTempFile(multipart,workspaceDTO.getTempFile());	
		if(tempFileDTO != null) {
			workspaceDTO.setTempFile(tempFileDTO);
			workspaceRepository.save(workspaceDTO.toEntity(workspaceDTO));
		}
	}
	
	
}
