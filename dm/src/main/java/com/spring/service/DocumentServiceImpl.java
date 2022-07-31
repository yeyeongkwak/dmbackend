package com.spring.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.DocumentDTO;
import com.spring.entity.Document;
import com.spring.repository.DocumentRepository;
import com.spring.util.S3Util;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{
	
	private final DocumentRepository documentRepository;
	
//	@Override
//	public List<DocumentDTO> getAllDocuments() {
//		List<Document> documents = documentRepository.findAll();
//		List<DocumentDTO> documentDTOs = new ArrayList<DocumentDTO>();
//		for (Document document : documents) {
//			documentDTOs.add(document.toDTO(document));
//		}
//		return documentDTOs;
//	}
	
//	public PageResultDTO<DocumentDTO, Document> getList(PageRequestDTO pageRequestDTO) {
//		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
//		
//		Page<Document> result = documentRepository.findAll(pageable);
//		
//		// entity -> dto
//		Function<Document, DocumentDTO> function = (Document -> Document.toDTO(Document));
//		
//		return new PageResultDTO<DocumentDTO, Document>(result, function);
//	}
	
	
	// 문서 조회
	@Override
	public DocumentDTO selectDocument(Long documentNo) {
		Document document = documentRepository.findDocumentByDocumentNo(documentNo);
		return document == null ? null : document.toDTO(document);
	}
	
	// 문서 다운로드
	public void downloadDocument(Long documentNo) {
		DocumentDTO documentDTO = selectDocument(documentNo);
		if(documentDTO != null) {
			try {
			String bucketName = "test-busan";
			String keyName = documentDTO.getFileName();
			
			S3Client client = S3Client.builder().build();
			
			GetObjectRequest request = GetObjectRequest.builder()
					.bucket(bucketName)
					.key(keyName)
					.build();
			
			ResponseInputStream<GetObjectResponse> inputStream = client.getObject(request);
			
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(keyName));
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = inputStream.read(buffer))!= -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			inputStream.close();
			outputStream.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	// 문서 작성
	// S3 파일 업로드
	public void S3Upload(MultipartFile multipart, String filename) {	
		try {
			S3Util.uploadFile(filename, multipart.getInputStream());
			
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}

	};
	
	// DB INSERT
	@Override
	public void insertDocument(DocumentDTO documentDTO ,MultipartFile multipart) {		
			String originalFileName =  multipart.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + "_" + originalFileName;	

			S3Upload(multipart, filename);
			
			String filePath = System.getProperty("user.dir") + "\\files";
						
			// 저장폴더가 존재하지 않을 경우 -> 반드시 생성을 해줘야 함
				if(!new File(filePath).exists()) {
					new File(filePath).mkdir();
							
				}
				String finalFilePath = filePath + "\\" + filename;
				try {
					multipart.transferTo(new File(finalFilePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			
			documentDTO.setFilePath(finalFilePath);
			documentDTO.setOriginalName(originalFileName);
			documentDTO.setFileName(filename);
			
			documentRepository.save(documentDTO.toEntity(documentDTO));
		}	
	
	// 문서 수정(파일, 문서 내용)
	@Override
	public void updateDocument(Long documentNo, DocumentDTO documentDTO, MultipartFile multipart) {
		documentDTO.setDocumentNo(documentNo);
		DocumentDTO orginalDTO = selectDocument(documentDTO.getDocumentNo());
		if(orginalDTO != null) {
			S3Delete(documentNo);
			
			String originalFileName =  multipart.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + "_" + originalFileName;
			S3Upload(multipart, filename);
			documentDTO.setOriginalName(originalFileName);
			documentDTO.setFileName(filename);
			
			DocumentDTO newDTO = new DocumentDTO(orginalDTO, documentDTO);
			documentRepository.save(newDTO.toEntity(newDTO));
		}
	}
	
	// 문서 수정(문서 내용)
	@Override
	public void updateDocument(Long documentNo, DocumentDTO documentDTO) {
		documentDTO.setDocumentNo(documentNo);
		DocumentDTO orginalDTO = selectDocument(documentDTO.getDocumentNo());
		if(orginalDTO != null) {
			DocumentDTO newDTO = new DocumentDTO(orginalDTO, documentDTO);
			documentRepository.save(newDTO.toEntity(newDTO));
		}
	}
	
	// 문서 삭제
	// S3 파일 삭제
	public void S3Delete(Long documentNo) {
		String bucketName = "test-busan";
		String key = selectDocument(documentNo).getFileName();
		
		S3Client client = S3Client.builder().build();
				
		DeleteObjectRequest request = DeleteObjectRequest.builder()
														 .bucket(bucketName)
														 .key(key)
														 .build();
				
		client.deleteObject(request);
	}
	// DB DELETE
	@Override
	public void deleteDocument(Long documentNo) {
		S3Delete(documentNo);
		documentRepository.deleteDocumentByDocumentNo(documentNo);
	}
}
