package com.spring.api;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.ec2.model.EndDateType;
import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;
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
		
	
		// 유저 문서 리스트(내  문서함)
		@GetMapping("/documents/user/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO){
			return documentUserService.getList(userNo, pageDTO);	
		}
		
		// 유저 문서 리스트(공유 문서함)
		@GetMapping("/documents/user/share/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getShareDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO){	
			return documentUserService.getShareList(userNo, pageDTO);
		}
		
		// 유저 중요 문서 리스트
		@GetMapping("/documents/user/important/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO){
			return documentUserService.getImportantList(userNo, pageDTO);
				
		}
		
		// 유저 휴지통 리스트
		@GetMapping("/documents/user/recycle/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO){					
			return documentUserService.getRecycleList(userNo, pageDTO);
		}
	
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
		@PutMapping(value="/documents/{userNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateDocument(@PathVariable Long userNo, @RequestBody List<DocumentUserDTO> documentUserDTO) {
			for (int i = 0; i < documentUserDTO.size(); i++) {
				DocumentDTO documentDTO =documentService.selectDocument(documentUserDTO.get(i).getDocumentNo().getDocumentNo());
				UserDTO userDTO = userService.getUserByUserNo(userNo);
				documentUserDTO.get(i).setUserNo(userDTO);
				documentUserDTO.get(i).setDocumentNo(documentDTO);
			}
			documentUserService.updateDocumentUser(documentUserDTO);
			
		}
		
		// 문서 삭제
		@DeleteMapping(value = "/document/{userNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
		@Transactional
		public void deleteDocument(@RequestBody List<DocumentUserDTO> documentDTO, @PathVariable Long userNo) {
			documentUserService.deleteDocumentUser(documentDTO, userNo);
		}
		
		@GetMapping(value="/document/member/{documentNo}")
		public List<DocumentUserDTO> getMemberList(@PathVariable Long documentNo){
			return documentUserService.getMemberList(documentNo);
		}
		
		// 문서 검색
		@GetMapping(value = "/document/{type}/{userNo}/{search}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getDocumentList(@PathVariable String type, @PathVariable Long userNo, @PathVariable String search, PageRequestDTO pageDTO){
			return documentUserService.getDocumentSearchList(userNo, search, pageDTO, type );
		}
		
		// 공유 문서 검색
		@GetMapping(value = "/document/share/{type}/{userNo}/{search}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getShareDocumentList(@PathVariable String type, @PathVariable Long userNo, @PathVariable String search, PageRequestDTO pageDTO){
			return documentUserService.getShareDocumentSearchList(userNo, search, pageDTO, type);
		}
		
		// 중요 문서 검색
		@GetMapping(value = "/document/important/{type}/{userNo}/{search}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantDocumentList(@PathVariable String type, @PathVariable Long userNo, @PathVariable String search, PageRequestDTO pageDTO){
			return documentUserService.getImportantDocumentSearchList(userNo, search, pageDTO, type);
		}
		
		// 휴지통 검색
		@GetMapping(value = "/document/recycle/{type}/{userNo}/{search}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleDocumentList(@PathVariable String type, @PathVariable Long userNo, @PathVariable String search, PageRequestDTO pageDTO){
			return documentUserService.getRecycleDocumentSearchList(userNo, search, pageDTO, type);
		}
		
}
