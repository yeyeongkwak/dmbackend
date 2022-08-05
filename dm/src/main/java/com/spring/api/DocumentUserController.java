package com.spring.api;

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
@CrossOrigin(origins = "*")

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DocumentUserController {
	
	private final DocumentUserServiceImpl documentUserService;
	private final DocumentServiceImpl documentService;
	private final UserServiceImpl userService;
		
	
		// 유저 문서 리스트
		@GetMapping("/documents/user/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO, Integer recycle){
			PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
			
			PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = documentUserService.getList(userNo, pageRequestDTO, recycle);
			
			List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
			pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
			
			return pageResultDTO;
			
		}
		
		// 유저 중요 문서 리스트
		@GetMapping("/documents/user/important/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO, Integer important, Integer recycle){
			PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
					
			PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = documentUserService.getImportantList(userNo, pageRequestDTO, important, recycle);
					
			List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
			pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
					
			return pageResultDTO;
				
		}
		
		// 유저 휴지통 문서 리스트
		@GetMapping("/documents/user/recycle/{userNo}")
		public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleDocuments(@PathVariable Long userNo, PageRequestDTO pageDTO, Integer recycle){
			PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
							
			PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = documentUserService.getRecycleList(userNo, pageRequestDTO, recycle);
							
			List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
			pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
							
			return pageResultDTO;
						
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
		@DeleteMapping(value = "/document/{documentNo}/{userNo}")
		@Transactional
		public void deleteDocument(@PathVariable Long documentNo, @PathVariable Long userNo) {
			DocumentUserDTO documentUserDTO = documentUserService.getDocumentUserByUserNoAndDocumentNo(userNo, documentNo);
			documentUserService.deleteDocumentUser(documentUserDTO);
		}
	
}
