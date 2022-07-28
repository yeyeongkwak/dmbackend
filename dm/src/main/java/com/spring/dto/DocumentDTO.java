package com.spring.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
