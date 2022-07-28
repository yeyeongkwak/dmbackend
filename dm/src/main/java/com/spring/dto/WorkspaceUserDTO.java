package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceUserDTO {
	
	private Long workspaceNo;
	
	private Long userNo;
}
