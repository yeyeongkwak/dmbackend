package com.spring.service;

import java.util.List;

import com.spring.dto.DocumentUserDTO;

public interface DocumentUserService {
	public List<DocumentUserDTO> getDocumentUserByUserNo(Long userNo);
	
	public DocumentUserDTO getDocumentUserByUserNoAndDocumentNo(Long userNo, Long documentNo);

	public void insertDocumentUser(List<DocumentUserDTO> documentUserDTOs);

	public void deleteDocumentUser(DocumentUserDTO documentUserDTO);

	public void updateDocumentUser(DocumentUserDTO documentUserDTO);
}
