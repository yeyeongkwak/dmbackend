package com.spring.service;



import java.io.Console;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.User;
import com.spring.exception.UploadFailedException;
import com.spring.repository.DocumentRepository;
import com.spring.util.S3Util;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{
   
   private final DocumentRepository documentRepository;
   private final DocumentUserServiceImpl documentUserService;
   private final S3Util s3Util;
    
//   @Override
//	public PageResultDTO<DocumentDTO, Document> getList(User userNo, PageRequestDTO pageRequestDTO) {
//		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
//		
//		Page<Document> result =  documentRepository.findDocumentByUser(userNo, pageable);
//		
//		// entity -> dto
//		Function<Document, DocumentDTO> function = (Document -> Document.toDTO(Document));
//		
//		return new PageResultDTO<DocumentDTO, Document>(result, function);
//	}
   

   // 문서 조회 
//   @Override
   public DocumentDTO selectDocument(Long documentNo) {
      Document document = documentRepository.findDocumentByDocumentNo(documentNo);
      return document == null ? null : document.toDTO(document);
   }
   // 문서 작성
   // DB INSERT
   @Override
   @Transactional

   public int insertDocument(DocumentDTO documentDTO ,List<DocumentUserDTO> documentUserList,MultipartFile multipart) {
	   double uploadFileSize = Math.round((((double)multipart.getSize()/1024))*100)/100.0;
	   if(documentSize(documentDTO.getUser().getUserNo()) + uploadFileSize > 10485760.00) {
//		   10485760
		   return 0;
	   }
	   if(uploadFileSize > 102400.00) {
		   return 2;
	   }
	   try {
		   String originalFileName =  multipart.getOriginalFilename();
		   String filename = "document/"+UUID.randomUUID().toString() + "_" + originalFileName;
			
		   documentDTO.setOriginalName(originalFileName);
		   documentDTO.setFileName(filename);
		   documentDTO.setFileCategory(multipart.getContentType().substring(multipart.getContentType().indexOf("/")+1));
		   documentDTO.setFileSize(uploadFileSize);
		   documentDTO.setFilePath(s3Util.S3Upload(multipart, filename));

		} catch (UploadFailedException e) {
			e.printStackTrace();
		}
	  
        Document document = documentRepository.save(documentDTO.toEntity(documentDTO));
        documentUserList.forEach(v->v.setDocumentNo(document.toDTO(document)));
        documentUserService.insertDocumentUser(documentUserList);
	  
        return 1;
			
			
//			List<DocumentUserDTO> documentUserDTOs = new ArrayList<DocumentUserDTO>();
//			documentDTO.getUserList().forEach(v->documentUserDTOs.add(
//															DocumentUserDTO.builder()
//															.documentNo(document.toDTO(document))
//															.userNo(v)
//															.build()));


         
         
         
      }   
   
   // 문서 수정(파일, 문서 내용)
//   @Override
//   public void updateDocument(Long documentNo, DocumentDTO documentDTO, MultipartFile multipart) {
//      documentDTO.setDocumentNo(documentNo);
//      DocumentDTO orginalDTO = selectDocument(documentDTO.getDocumentNo());
//      if(orginalDTO != null) {
//         s3Util.deleteFile(orginalDTO.getFileName());
//         
//         try {
//			documentDTO = s3Util.S3Upload(multipart, documentDTO);
//			DocumentDTO newDTO = new DocumentDTO(orginalDTO, documentDTO);
//			documentRepository.save(newDTO.toEntity(newDTO));
//		} catch (UploadFailedException e) {
//			e.printStackTrace();
//		}
//      }
//   }
   
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
   @Override
   public void deleteDocument(List<Long> documentNo) {
	   for (int i = 0; i < documentNo.size(); i++) {
		 s3Util.deleteFile(selectDocument(documentNo.get(i)).getFileName());
		 documentRepository.deleteById(documentNo.get(i));
	}

   }

   // 문서 용량
	@Override
	public Double documentSize(Long userNo) {
	    return documentRepository.findDocumentSize(userNo);
		
	}
}