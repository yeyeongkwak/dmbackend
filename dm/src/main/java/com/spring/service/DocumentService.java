package com.spring.service;

import java.util.List;

import com.spring.dto.DocumentDTO;
import com.spring.dto.UserDTO;

public interface DocumentService {
	public List<DocumentDTO> getAllDocuments();
	public DocumentDTO getDocumentByDocumentNo(Long documentNo);
	public void insertDocument(DocumentDTO documentDTO);
	public void updateDocument(DocumentDTO documentDTO);
	public void deleteDocumentByDocumentNo(Long documentNo);
	
}
