package com.spring.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.DocumentDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.User;
import com.spring.service.DocumentServiceImpl;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor

public class DocumentController {

   private final DocumentServiceImpl documentService;
   
   @GetMapping("/documents/{userNo}")
	public PageResultDTO<DocumentDTO, Document> getDocuments(@PathVariable User userNo, PageRequestDTO pageDTO){
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		
		PageResultDTO<DocumentDTO, Document> pageResultDTO = documentService.getList(userNo, pageRequestDTO);
		
		List<DocumentDTO> resultBoards = new ArrayList<DocumentDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
		
	}
   
   // 문서 조회
   @GetMapping(value = "/document/{documentNo}")
   public DocumentDTO selectDocument(@PathVariable Long documentNo){
      return documentService.selectDocument(documentNo);
   }
   
   // 문서 다운로드
   @GetMapping(value = "/document/download/{documentNo}")
   public DocumentDTO downloadDocument(@PathVariable Long documentNo){
      return documentService.selectDocument(documentNo);
   }
   
   // 문서 작성
   @PostMapping(value = "/document",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public void insertDocument(@RequestPart("documentDTO") DocumentDTO documentDTO, @RequestPart("file") MultipartFile multipart) {
	   System.out.println(documentDTO);
      documentService.insertDocument(documentDTO, multipart);
   }
   
   // 문서 수정(파일, 문서 내용)
   @PostMapping(value="/document/{documentNo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public void updateDocument(@PathVariable Long documentNo, @RequestPart("documentDTO") DocumentDTO documentDTO, @RequestPart("file") MultipartFile multipart) {
      documentService.updateDocument(documentNo, documentDTO, multipart);
   }
   
   // 문서 수정(문서 내용)
   @PutMapping(value="/document/{documentNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public void updateDocument(@PathVariable Long documentNo, @RequestBody DocumentDTO documentDTO) {
      documentService.updateDocument(documentNo, documentDTO);
   }
   
   // 문서 삭제
   @DeleteMapping(value = "/documents", consumes = MediaType.APPLICATION_JSON_VALUE)
   public void deleteDocument(@RequestBody List<Long> documentNo) {
      documentService.deleteDocument(documentNo);
//	   System.out.println(documentNo);
   }
}