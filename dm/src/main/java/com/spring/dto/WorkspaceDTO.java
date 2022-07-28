package com.spring.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDTO {
	
	private Long workspaceNo;
	
	private Long master;
	
	private String title;
	
	private String content;

	private Timestamp regisetDate;
	
	private Timestamp modifyDate;
	
	
}
