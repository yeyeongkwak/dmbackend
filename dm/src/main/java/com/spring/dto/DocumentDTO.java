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
public class DocumentDTO {
	
	private Long documentNo; 

	private UserDTO user;
	
	private LocalDateTime registerDate;

	private LocalDateTime modifyDate;
	
	private String content;
	
	private String fileName;
	
	private String filePath;
	
	private String originalName;
	
	
	public DocumentDTO(DocumentDTO orginalDTO, DocumentDTO newDTO) {
		documentNo = orginalDTO.getDocumentNo();
		user = orginalDTO.getUser();
		registerDate = orginalDTO.getRegisterDate();
		if(newDTO.getContent() != null) {
			content = newDTO.getContent();
		}else {
			content = orginalDTO.getContent();
		}
		if(newDTO.getFileName() != null) {
			fileName = newDTO.getFileName();
		}else {
			fileName = orginalDTO.getFileName();
		}
		if(newDTO.getFilePath() != null) {
			filePath = newDTO.getFilePath();
		}else {
			filePath = orginalDTO.getFilePath();
		}
		if(newDTO.getOriginalName() != null) {
			originalName = newDTO.getOriginalName();
		}else {
			originalName = orginalDTO.getOriginalName();
		}
	}
}
