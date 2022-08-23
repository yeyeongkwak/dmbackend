package com.spring.service;

import java.util.List;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;

public interface DocumentUserService {
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getList(Long userNo, PageRequestDTO pageRequestDTO, Integer recycle);
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getShareList(Long userNo, PageRequestDTO pageRequestDTO, Integer recycle);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantList(Long userNo, PageRequestDTO pageRequestDTO, Integer important, Integer recycle);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleList(Long userNo, PageRequestDTO pageRequestDTO, Integer recycle);
	
	public List<DocumentUserDTO> getDocumentUserByUserNo(Long userNo);
	
	public DocumentUserDTO getDocumentUserByUserNoAndDocumentNo(Long userNo, Long documentNo);

	public void insertDocumentUser(List<DocumentUserDTO> documentUserDTOs);

	public void deleteDocumentUser(List<Long> documentNo, Long userNo);

	public void updateDocumentUser(List<DocumentUserDTO> documentUserDTO);


	public List<DocumentUserDTO> getMemberList(Long documentNo);
}
