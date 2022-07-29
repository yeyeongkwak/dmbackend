package com.spring.dto;

import java.time.LocalDateTime;

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
	
	private Long master;
	
	private String title;
	
	private String content;
	
	private LocalDateTime registerDate;
	
	private LocalDateTime modifyDate;
	
	
}
