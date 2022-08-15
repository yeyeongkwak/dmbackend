package com.spring.dto;

import java.time.LocalDateTime;
import java.util.List;

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
	
	private LocalDateTime registerDate;
	
	private LocalDateTime modifyDate;
	
	private TempFileDTO tempFile;
	
	private List<UserDTO> userList;
  
	public Workspace toEntity(WorkspaceDTO workspaceDTO) {
		Workspace workspace = Workspace.builder()
								.workspaceNo(workspaceDTO.getWorkspaceNo())
								.master(workspaceDTO.getMaster().toEntity(workspaceDTO.getMaster()))
								.title(workspaceDTO.getTitle())
								.registerDate(workspaceDTO.getRegisterDate())
								.tempFile(workspaceDTO.getTempFile() == null?null :workspaceDTO.getTempFile().toEntity(workspaceDTO.getTempFile()))
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
		if(newWorkspaceDTO.getTempFile() != null) {
			tempFile = newWorkspaceDTO.getTempFile();
		}else {
			tempFile = oldWorkspaceDTO.getTempFile();
		}
	}
	
}
