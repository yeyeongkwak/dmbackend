package com.spring.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
	
	private Long documentNo; 

	private Long userNo;
	
	private Date registerDate;

	private Date modifyDate;
	
	private String content;
	
	private String fileName;
	
	private String filePath;
	
	private String originalName;
}
