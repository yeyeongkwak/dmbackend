package com.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.User;

public interface DocumentService {
   
//   public List<DocumentDTO> getAllDocuments();
   
   // 내 문서 리스트
	public PageResultDTO<DocumentDTO, Document> getList(User userNo, PageRequestDTO pageRequestDTO);
	
   // 문서 조회 
   public DocumentDTO selectDocument(Long documentNo);
   
   // 문서 작성
   public Boolean insertDocument(DocumentDTO documentDTO,List<DocumentUserDTO> documentUserList ,MultipartFile multipartFile);
   

   // 문서 수정(파일, 문서 내용)
   public void updateDocument(Long documentNo, DocumentDTO documentDTO, MultipartFile multipartFile);

   // 문서 수정(문서 내용)
   public void updateDocument(Long documentNo, DocumentDTO documentDTO);
   
   // 문서 삭제
   public void deleteDocument(List<Long> documentNo);
   
   // 내 문서 용량
   public double documentSize(Long userNo);
   
   
}