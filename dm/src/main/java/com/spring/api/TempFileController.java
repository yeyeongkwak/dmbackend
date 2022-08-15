package com.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.TempFileDTO;
import com.spring.service.TempFileServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class TempFileController {
	
	private final TempFileServiceImpl tempFileservice;
	
	@GetMapping(value = "/temp/{fileNo}")
	public TempFileDTO getTempFile(@PathVariable Long fileNo) {
		return tempFileservice.getTempFile(fileNo);
	}
	
}
