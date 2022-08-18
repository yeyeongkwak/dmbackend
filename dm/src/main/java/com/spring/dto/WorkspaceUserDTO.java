package com.spring.dto;

import java.util.List;

import com.spring.entity.WorkspaceUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceUserDTO {
	
	private WorkspaceDTO workspaceNo;
	
	private UserDTO userNo;
	
	private List<String> member;
	
	public WorkspaceUser toEntity(WorkspaceUserDTO workspaceUserDTO) {
		WorkspaceUser workspaceUser = WorkspaceUser.builder()
										.workspaceNo(workspaceUserDTO.getWorkspaceNo().toEntity(workspaceUserDTO.getWorkspaceNo()))
										.userNo(workspaceUserDTO.getUserNo().toEntity(workspaceUserDTO.getUserNo()))
										.build();
		return workspaceUser;
	}
}
