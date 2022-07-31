package com.spring.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.service.DocumentUserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DocumentUserController {
	
	private final DocumentUserServiceImpl documentUserService;
		
		
		
		// 문서 조회
		@GetMapping(value = "/document/{userNo}")
		public DocumentUserDTO sellectDocumentUser(@PathVariable Long userNo){
			return documentUserService.getDocumentUserByUserNo(userNo);
		}
		
//		// 문서 작성
//		@PostMapping(value = "/document",consumes = MediaType.APPLICATION_JSON_VALUE)
//		public void insertDocument(@RequestBody DocumentDTO documentDTO) {
//			documentService.insertDocument(documentDTO);	
//		}
//		
//		// 문서 수정
//		@PostMapping(value="/document/{documentNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
//		public void updateDocument(@PathVariable Long documentNo, @RequestBody DocumentDTO param) {
//			param.setDocumentNo(documentNo);
//			documentService.updateDocument(param);
//		}
		
//		// 문서 삭제
//		@DeleteMapping(value = "/document/{documentNo}")
//		public void deleteDocument(@PathVariable Long documentNo) {
//			documentService.deleteDocumentByDocumentNo(documentNo);
//		}
	
}
