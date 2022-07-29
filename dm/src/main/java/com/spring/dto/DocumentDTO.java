package com.spring.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
	
	private Long documentNo; 

	private Long userNo;
	
	private Timestamp registerDate;

	private Timestamp modifyDate;
	
	private String content;
	
	private String fileName;
	
	private String filePath;
	
	private String originalName;
}
