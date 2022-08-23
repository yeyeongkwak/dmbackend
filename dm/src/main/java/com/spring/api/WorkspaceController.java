package com.spring.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.WorkspaceDTO;
import com.spring.dto.WorkspaceUserDTO;
import com.spring.service.WorkspaceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class WorkspaceController {
	private final WorkspaceServiceImpl workspaceService;
	
	@PostMapping(value = "/workspace",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<WorkspaceUserDTO> insertWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
		System.out.println(workspaceDTO);
		return workspaceService.insertWorkspace(workspaceDTO);
		
	}
	
	@DeleteMapping(value = "/workspace/{workspaceNo}")
	public void deleteWorkspace(@PathVariable Long workspaceNo) {
		workspaceService.deleteWorkspace(workspaceNo);
	}
	
	// 워크스페이스 제목 수정
	@PutMapping(value = "/workspace/{workspaceNo}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<WorkspaceUserDTO> updateWorkspace(@RequestBody WorkspaceDTO workspaceDTO,@PathVariable Long workspaceNo) {
		workspaceDTO.setWorkspaceNo(workspaceNo);
		return workspaceService.updateWorkspace(workspaceDTO);
	}
	
	// 임시저장시 사용
	@PutMapping(value = "/workspace/temp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void updateTempWorkspace(@RequestPart("file") MultipartFile multipart,@RequestPart("workspace") WorkspaceDTO workspaceDTO) {
		workspaceService.updateWorkspace(multipart,workspaceDTO);
	}
	
	@GetMapping(value = "/workspace/{workspaceNo}")
	public WorkspaceDTO getWorkspace(@PathVariable Long workspaceNo) {
		return workspaceService.getWorkspaceByWorkspaceNo(workspaceNo);
	}
}
