package com.spring.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.entity.User;
import com.spring.entity.Workspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDTO {
	
	private Long workspaceNo;
	
	private UserDTO master;
	
	private String title;
	
	private String content;
	
	private LocalDateTime registerDate;
	
	private LocalDateTime modifyDate;
	
	private List<UserDTO> userList;
  
	public Workspace toEntity(WorkspaceDTO workspaceDTO) {
		Workspace workspace = Workspace.builder()
								.workspaceNo(workspaceDTO.getWorkspaceNo())
								.master(workspaceDTO.getMaster().toEntity(workspaceDTO.getMaster()))
								.title(workspaceDTO.getTitle())
								.content(workspaceDTO.getTitle())
								.registerDate(workspaceDTO.getRegisterDate())
								.modifyDate(workspaceDTO.getModifyDate())
								.build();
		return workspace;
	}
	
	public WorkspaceDTO(WorkspaceDTO newWorkspaceDTO,WorkspaceDTO oldWorkspaceDTO) {
		workspaceNo = oldWorkspaceDTO.getWorkspaceNo();
		master = oldWorkspaceDTO.getMaster();
		registerDate = oldWorkspaceDTO.getRegisterDate();
		if(newWorkspaceDTO.getTitle() != null) {
			title = newWorkspaceDTO.getTitle();
		}else {
			title = oldWorkspaceDTO.getTitle();
		}
		if(newWorkspaceDTO.getContent() != null) {
			content = newWorkspaceDTO.getContent();
		}else {
			content = oldWorkspaceDTO.getContent();
		}
		
	}
	
}
