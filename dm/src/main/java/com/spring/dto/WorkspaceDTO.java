package com.spring.dto;

import java.sql.Date;

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

	private Date regisetDate;
	
	private Date modifyDate;
	
	
}
