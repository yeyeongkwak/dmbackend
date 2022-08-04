package com.spring.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.UserDTO;
import com.spring.service.DocumentServiceImpl;
import com.spring.service.DocumentUserServiceImpl;
import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DocumentUserController {
	
	private final DocumentUserServiceImpl documentUserService;
	private final DocumentServiceImpl documentService;
	private final UserServiceImpl userService;
		
		
		// 유저 문서 조회
		@GetMapping(value = "/document/user/{userNo}")
		public List<DocumentUserDTO> selectDocumentUser(@PathVariable Long userNo){
			return documentUserService.getDocumentUserByUserNo(userNo);
		}
		
		@GetMapping(value = "/document/user/{userNo}/{documentNo}")
		public DocumentUserDTO getDocumentUser(@PathVariable Long userNo,@PathVariable Long documentNo) {
			return documentUserService.getDocumentUserByUserNoAndDocumentNo(userNo, documentNo);
		}
		
		// 문서 작성
		@PostMapping(value = "/document/authority",consumes = MediaType.APPLICATION_JSON_VALUE)
		public void insertDocument(@RequestBody List<DocumentUserDTO> documentUserDTOs) {
			documentUserService.insertDocumentUser(documentUserDTOs);	
		}
		
		// 문서 수정
		@PutMapping(value="/document/{userNo}/{documentNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateDocument(@PathVariable Long documentNo,@PathVariable Long userNo, @RequestBody DocumentUserDTO documentUserDTO) {
			DocumentDTO documentDTO =documentService.selectDocument(documentNo);
			UserDTO userDTO = userService.getUserByUserNo(userNo);
			documentUserDTO.setUserNo(userDTO);
			documentUserDTO.setDocumentNo(documentDTO);
			documentUserService.updateDocumentUser(documentUserDTO);
		}
		
		// 문서 삭제
		@DeleteMapping(value = "/document/{documentNo}/{userNo}")
		@Transactional
		public void deleteDocument(@PathVariable Long documentNo, @PathVariable Long userNo) {
			DocumentUserDTO documentUserDTO = documentUserService.getDocumentUserByUserNoAndDocumentNo(userNo, documentNo);
			documentUserService.deleteDocumentUser(documentUserDTO);
		}
	
}
