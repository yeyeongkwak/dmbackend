package com.spring.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Workspace;
import com.spring.service.WorkspaceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class WorkspaceController {
	private final WorkspaceServiceImpl workspaceService;
	
	@PostMapping(value = "/workspace",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertWorkspace(@RequestBody Workspace workspace) {
		
		workspaceService.insertWorkspace(workspace);
	}
	
	@DeleteMapping(value = "/workspace/{workspaceNo}")
	public void deleteWorkspace(Long workspaceNo) {
		workspaceService.deleteWorkspace(workspaceNo);
	}
	
	
}
