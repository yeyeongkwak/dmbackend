package com.spring.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;

public interface DocumentUserService {
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getList(Long userNo, PageRequestDTO pageRequestDTO);
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getShareList(Long userNo, PageRequestDTO pageRequestDTO);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantList(Long userNo, PageRequestDTO pageRequestDTO);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleList(Long userNo, PageRequestDTO pageRequestDTO);
	
	public List<DocumentUserDTO> getDocumentUserByUserNo(Long userNo);
	
	public DocumentUserDTO getDocumentUserByUserNoAndDocumentNo(Long userNo, Long documentNo);

	public void insertDocumentUser(List<DocumentUserDTO> documentUserDTOs);

	public void deleteDocumentUser(List<DocumentUserDTO> documentDTOList, Long userNo);

	public void updateDocumentUser(List<DocumentUserDTO> documentUserDTO);


	public List<DocumentUserDTO> getMemberList(Long documentNo);
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getDocumentSearchList(Long userNo, String search, PageRequestDTO pageRequestDTO,String type);
	
	public PageResultDTO<DocumentUserDTO, DocumentUser> getShareDocumentSearchList(Long userNo, String search, PageRequestDTO pageRequestDTO ,String type);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantDocumentSearchList(Long userNo, String search, PageRequestDTO pageRequestDTO,String type);

	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleDocumentSearchList(Long userNo, String search, PageRequestDTO pageRequestDTO,String type);
	

}
