package com.spring.service;

import java.io.IOException;

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
	
	@Override
	@Transactional
	public TempFileDTO insertTempFile(MultipartFile multipart,TempFileDTO tempFileDTO) {
		String fileName = "temp/"+multipart.getOriginalFilename();
		try {
			if(tempFileDTO != null) {
				System.out.println("111");
				S3Util.deleteFile(fileName);
			}
			else{
				TempFileDTO newTempFileDTO = TempFileDTO.builder()
						.fileName(multipart.getOriginalFilename())
						.filePath(S3Util.getFileUrl(fileName))
						.build();
				TempFile tempFile =tempFileRepository.save(newTempFileDTO.toEntity(newTempFileDTO));
				return tempFile.toDTO(tempFile);
			}
			S3Util.uploadFile(fileName, multipart.getInputStream());
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteTempFile(Long fileNo) {
		TempFileDTO tempFileDTO = getTempFile(fileNo);
		if(tempFileDTO != null) {
			S3Util.deleteFile("temp/"+tempFileDTO.getFileName());
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
