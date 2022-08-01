package com.spring.api;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
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
import com.spring.dto.UserDTO;
import com.spring.entity.Document;
import com.spring.repository.DocumentRepository;
import com.spring.service.DocumentServiceImpl;
import com.spring.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DocumentController {
	
	private final DocumentServiceImpl documentService;
	private final DocumentRepository documentRepository;
	@GetMapping("/download/{documentNo}")
	  public void download(HttpServletResponse response, @PathVariable Long documentNo) throws IOException {

		DocumentDTO document = documentService.selectDocument(documentNo);
		String finalPath = document.getFilePath() + "\\" + document.getFileName();
	    String path = "C://Users//ju390//Downloads/a2ff3d73-4fed-4b37-9513-5bc98a07178a_sick.png";
	    
	    
	    
	    byte[] fileByte = FileUtils.readFileToByteArray(new File(finalPath));

	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("sick.png", "UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");

	    response.getOutputStream().write(fileByte);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	  }
	
	// 문서 조회
	@GetMapping(value = "/document/{documentNo}")
	public DocumentDTO selectDocument(@PathVariable Long documentNo){
		return documentService.selectDocument(documentNo);
	}
	
	// 문서 다운로드
	@GetMapping(value = "/document/download/{documentNo}")
	public void downloadDocument(@PathVariable Long documentNo){
		documentService.downloadDocument(documentNo);
	}
	
	// 문서 작성
	@PostMapping(value = "/document",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void insertDocument(@RequestPart("documentDTO") DocumentDTO documentDTO, @RequestPart("file") MultipartFile multipart) {
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
	@DeleteMapping(value = "/document/{documentNo}")
	public void deleteDocument(@PathVariable Long documentNo) {
		documentService.deleteDocument(documentNo);
	}
}
