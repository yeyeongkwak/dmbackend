package com.spring.service;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.TempFileDTO;

public interface TempFileService {

	public TempFileDTO insertTempFile(MultipartFile multipartFile,TempFileDTO tempFileDTO);

	public void deleteTempFile(Long fileNo);

	public void updateTempFile(TempFileDTO tempFileDTO);

	public TempFileDTO getTempFile(Long fileNo);


}
