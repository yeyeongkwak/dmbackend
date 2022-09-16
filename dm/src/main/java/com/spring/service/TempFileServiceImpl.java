package com.spring.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.TempFileDTO;
import com.spring.entity.TempFile;
import com.spring.repository.TempFileRepository;
import com.spring.util.S3Util;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

@Service
@RequiredArgsConstructor
public class TempFileServiceImpl implements TempFileService {
	
	private final TempFileRepository tempFileRepository;
	private final S3Util s3Util;
	
	@Override
	@Transactional
	public TempFileDTO insertTempFile(MultipartFile multipart,TempFileDTO tempFileDTO) {
		String fileName = "temp/"+UUID.randomUUID().toString()+"_"+multipart.getOriginalFilename();
		TempFile tempFile = null;
		try {
			if(tempFileDTO != null) {
				s3Util.deleteFile(tempFileDTO.getFileName());
			}
			s3Util.uploadFile(fileName, multipart.getInputStream());
				TempFileDTO newTempFileDTO = TempFileDTO.builder()
						.fileName(fileName)
						.filePath(s3Util.getFileUrl(fileName))
						.originalName(multipart.getOriginalFilename())
						.build();
				tempFile =tempFileRepository.save(newTempFileDTO.toEntity(newTempFileDTO));
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return tempFile == null ? null : tempFile.toDTO(tempFile);
	}
	
	@Override
	public void deleteTempFile(Long fileNo) {
		TempFileDTO tempFileDTO = getTempFile(fileNo);
		if(tempFileDTO != null) {
			s3Util.deleteFile("temp/"+tempFileDTO.getFileName());
			tempFileRepository.deleteById(fileNo);
		}
	}
	
	@Override
	public void updateTempFile(TempFileDTO tempFileDTO) {
		TempFile tempFile = tempFileRepository.getTempFileByFileNo(tempFileDTO.getFileNo());
		if(tempFile != null) {
			tempFileRepository.save(tempFileDTO.toEntity(tempFileDTO));
		}
	}
	
	@Override
	public TempFileDTO getTempFile(Long fileNo) {
		TempFile tempFile = tempFileRepository.getTempFileByFileNo(fileNo);
		
		return tempFile == null? null : tempFile.toDTO(tempFile);
	}
	
//	public TempFileDTO readTempFile(Long fileNo) {
//		return  getTempfile(fileNo);
//		S3Util.readFile("temp/"+tempFileDTO.getFileName());
		
//	}
}
