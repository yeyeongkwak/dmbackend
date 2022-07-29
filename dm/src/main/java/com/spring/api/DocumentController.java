package com.spring.api;

import java.sql.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.DocumentDTO;
import com.spring.dto.UserDTO;
import com.spring.service.DocumentServiceImpl;
import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DocumentController {
	private final DocumentServiceImpl documentService;
	
	@GetMapping(value = "/document/{documentNo}")
	public DocumentDTO sellectDocument(@PathVariable Long documentNo){
		return documentService.getDocumentByDocumentNo(documentNo);
	}
	
	@PostMapping(value = "/document",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertDocument(@RequestBody DocumentDTO documentDTO) {
		documentService.insertDocument(documentDTO);	
	}
	
	@PostMapping(value="/document/{documentNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateDocument(@PathVariable Long documentNo, @RequestBody DocumentDTO param) {
		param.setDocumentNo(documentNo);
		documentService.updateDocument(param);
	}
	
	
	@DeleteMapping(value = "/document/{documentNo}")
	public void deleteDocument(@PathVariable Long documentNo) {
		documentService.deleteDocumentByDocumentNo(documentNo);
	}
}
