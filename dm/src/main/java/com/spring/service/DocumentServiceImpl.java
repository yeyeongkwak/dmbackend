package com.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.dto.DocumentDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.Document;
import com.spring.mapper.UserMapper;
import com.spring.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{
	
	DocumentRepository documentRepository;
	
	@Override
	public List<DocumentDTO> getAllDocuments() {
		List<Document> documents = documentRepository.findAll();
		List<DocumentDTO> documentDTOs = new ArrayList<DocumentDTO>();
		for (Document document : documents) {
			documentDTOs.add(document.toDTO(document));
		}
		return documentDTOs;
	}
	
	public PageResultDTO<DocumentDTO, Document> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
		
		Page<Document> result = documentRepository.findAll(pageable);
		
		// entity -> dto
		Function<Document, DocumentDTO> function = (Document -> Document.toDTO(Document));
		
		return new PageResultDTO<DocumentDTO, Document>(result, function);
	}
	
	@Override
	public DocumentDTO getDocumentByDocumentNo(Long documentNo) {
		Document document = documentRepository.findDocumentByDocumentNo(documentNo);
		DocumentDTO documentdto = document.toDTO(document);
		return documentdto;
	}
	
	@Override
	public void insertDocument(DocumentDTO documentDTO) {
		Document document = documentRepository.findDocumentByDocumentNo(documentDTO.getDocumentNo());
		if(document == null) {
			document = documentDTO.toEntity(documentDTO);
			documentRepository.save(document);
		}	
	}
	
	@Override
	public void updateDocument(DocumentDTO documentDTO) {
		//제목, 내용, 작성자, 등록일, 수정일
		DocumentDTO orginalDTO = getDocumentByDocumentNo(documentDTO.getDocumentNo());
		if(orginalDTO != null) {
			DocumentDTO newDTO = new DocumentDTO(orginalDTO, documentDTO);
			documentRepository.save(newDTO.toEntity(newDTO));
			
		}
	}
	
	@Override
	public void deleteDocumentByDocumentNo(Long documentNo) {
		documentRepository.deleteDocumentByDocumentNo(documentNo);
	}



}
