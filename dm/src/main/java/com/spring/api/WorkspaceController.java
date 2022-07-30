package com.spring.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.WorkspaceDTO;
import com.spring.service.WorkspaceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class WorkspaceController {
	private final WorkspaceServiceImpl workspaceService;
	
	@PostMapping(value = "/workspace",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
		workspaceService.insertWorkspace(workspaceDTO);
		
	}
	
	@DeleteMapping(value = "/workspace/{workspaceNo}")
	public void deleteWorkspace(@PathVariable Long workspaceNo) {
		workspaceService.deleteWorkspace(workspaceNo);
	}
	
	@PutMapping(value = "/workspace/{workspaceNo}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateWorkspace(@RequestBody WorkspaceDTO workspaceDTO,@PathVariable Long workspaceNo) {
		workspaceDTO.setWorkspaceNo(workspaceNo);
		workspaceService.updateWorkspace(workspaceDTO);
	}
}
