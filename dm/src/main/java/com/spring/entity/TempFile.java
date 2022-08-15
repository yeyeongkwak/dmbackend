package com.spring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.spring.dto.TempFileDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString
public class TempFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_no")
	private Long fileNo;
	
	@Column(name = "file_name")
	@Size(max = 50)
	private String fileName;
	
	@Column(name = "file_path")
	@Size(max = 255)
	private String filePath;
	
	
	public TempFileDTO toDTO(TempFile tempFile) {
		TempFileDTO tempFileDTO = TempFileDTO.builder()
									.fileNo(tempFile.getFileNo())
									.fileName(tempFile.getFileName())
									.filePath(tempFile.getFilePath())
									.build();
		return tempFileDTO;
	}
	
}
