package com.spring.dto;

import java.time.LocalDateTime;

import com.spring.entity.TempFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempFileDTO {
	
	private Long fileNo;
	
	private String fileName;
	
	private String filePath;
	
	private String originalName;
	
	
	public TempFile toEntity(TempFileDTO tempFileDTO) {
		TempFile tempFile = TempFile.builder()
							.fileNo(tempFileDTO.getFileNo())
							.fileName(tempFileDTO.getFileName())
							.filePath(tempFileDTO.getFilePath())
							.originalName(tempFileDTO.getOriginalName())
							.build();
		return tempFile;
	}
	
}
