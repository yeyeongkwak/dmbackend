package com.spring.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.dto.DocumentDTO;
import com.spring.dto.DocumentUserDTO;
import com.spring.dto.PageRequestDTO;
import com.spring.dto.PageResultDTO;
import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;
import com.spring.model.Authority;
import com.spring.repository.DocumentRepository;
import com.spring.repository.DocumentUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentUserServiceImpl implements DocumentUserService {
	
	private final DocumentUserRepository documentUserRepository;
	private final DocumentRepository documentRepository;
	
	
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getList(Long userNo, PageRequestDTO pageDTO, Integer recycle) {
		Pageable pageable = pageDTO.getPageable(Sort.by("documentNo").descending());
		recycle = 0;
		
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndRecycleBinAndAuthority(userNo, pageable, recycle, Authority.MASTER);

		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getList(userNo, pageRequestDTO, recycle);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
		
//		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getShareList(Long userNo, PageRequestDTO pageDTO, Integer recycle) {
		Pageable pageable = pageDTO.getPageable(Sort.by("documentNo").descending());
		recycle = 0;
		
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndRecycleBinAndAuthorityNot(userNo, pageable, recycle, Authority.MASTER);

		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getShareList(userNo, pageRequestDTO, recycle);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
//		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantList(Long userNo, PageRequestDTO pageDTO, Integer important, Integer recycle) {
		Pageable pageable = pageDTO.getPageable(Sort.by("documentNo").descending());
		important = 1;
		recycle = 0;
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndImportantAndRecycleBin(userNo, pageable, important, recycle);
		
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getImportantList(userNo, pageRequestDTO,important, recycle);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
		
//		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleList(Long userNo, PageRequestDTO pageDTO, Integer recycle) {
		Pageable pageable = pageDTO.getPageable(Sort.by("documentNo").descending());
		recycle = 1;
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndRecycleBin(userNo, pageable, recycle);
		
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getRecycleList(userNo, pageRequestDTO, recycle);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
//		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public List<DocumentUserDTO> getDocumentUserByUserNo(Long userNo) {
		List<DocumentUser> documentUserList = documentUserRepository.findDocumentUserByUserNoUserNo(userNo);
		List<DocumentUserDTO> documentUserDTOList = new ArrayList<DocumentUserDTO>();
		
		documentUserList.forEach(v->documentUserDTOList.add(v.toDTO(v)));
		return documentUserDTOList;
	}
	
	@Override
	public DocumentUserDTO getDocumentUserByUserNoAndDocumentNo(Long userNo,Long documentNo) {
		DocumentUser documentUser = documentUserRepository.findDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(userNo, documentNo);
		return documentUser == null ? null : documentUser.toDTO(documentUser);
	}
	
	@Override
	public void insertDocumentUser(List<DocumentUserDTO> documentUserDTOs) {
		List<DocumentUser> documentUsers = new ArrayList<DocumentUser>();
		
//		documentUserDTOs.forEach(v->getDocumentUserByUserNoAndDocumentNo(v., documentNo));
		documentUserDTOs.forEach(v->{DocumentUserDTO oldDocumentUserDTO = getDocumentUserByUserNoAndDocumentNo(v.getUserNo().getUserNo(), v.getDocumentNo().getDocumentNo());
									if(oldDocumentUserDTO != null) {
										DocumentUserDTO newDocumentDTO = new DocumentUserDTO(oldDocumentUserDTO,v);
										documentUsers.add(newDocumentDTO.toEntity(newDocumentDTO));
									}else{
										documentUsers.add(v.toEntity(v));
									}});
		documentUserRepository.saveAll(documentUsers);
	}
	
	@Override
	@Transactional
	public void deleteDocumentUser(List<DocumentUserDTO> documentDTOList, Long userNo) {
		documentDTOList.forEach(documentDTO -> {
			if(documentDTO.getAuthority().equals(Authority.MASTER)){
				documentRepository.deleteById(documentDTO.getDocumentNo().getDocumentNo());
			}else {
				documentUserRepository.deleteDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(userNo, documentDTO.getDocumentNo().getDocumentNo());
			}
					});
//		for (int i = 0; i < documentNo.size(); i++) {
//			documentUserRepository.delete(documentNo.get(i).toEntity(documentNo.get(i)));
//			documentUserRepository.deleteDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(userNo, documentNo.get(i));
//		}
	}
	
	@Override
	public void updateDocumentUser(List<DocumentUserDTO> documentUserDTO) {
		for (int i = 0; i < documentUserDTO.size(); i++) {
			DocumentUserDTO oldDocumentUserDTO 
			= getDocumentUserByUserNoAndDocumentNo(documentUserDTO.get(i).getUserNo().getUserNo(),
					documentUserDTO.get(i).getDocumentNo().getDocumentNo());
			if(oldDocumentUserDTO != null) {
			DocumentUserDTO newDocumentUserDTO = new DocumentUserDTO(oldDocumentUserDTO,documentUserDTO.get(i));
			documentUserRepository.save(newDocumentUserDTO.toEntity(newDocumentUserDTO));
			}
		}
		
	}
	
	@Override
	public List<DocumentUserDTO> getMemberList(Long documentNo) {
		List<DocumentUser> documentUserList = documentUserRepository.findAllByDocumentNoDocumentNo(documentNo);
		List<DocumentUserDTO> documentUserDTOList = new ArrayList<DocumentUserDTO>();
		documentUserList.forEach(v->documentUserDTOList.add(v.toDTO(v)));
		return documentUserDTOList;
	}

	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getDocumentSearchList(Long userNo, String search, PageRequestDTO pageDTO, String type) {
		Pageable pageable = pageDTO.getPageable(Sort.by("document_no").descending());
		Page<DocumentUser> result = null;
		if(type.equals("originalName")) {
			result = documentUserRepository.searchMyBox(userNo, search, pageable);
		}else if(type.equals("content")) {
			result = documentUserRepository.searchMyBoxContent(userNo, search, pageable);
		}else if(type.equals("userName")) {
			result = documentUserRepository.searchMyBoxUserName(userNo, search, pageable);
		}
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));

		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getDocumentSearchList(userNo, search, pageRequestDTO, type);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getShareDocumentSearchList(Long userNo, String search, PageRequestDTO pageDTO, String type) {
		Pageable pageable = pageDTO.getPageable(Sort.by("document_no").descending());

		Page<DocumentUser> result = null;
		if(type.equals("originalName")) {
			result = documentUserRepository.searchShareBox(userNo, search, pageable);
		}else if(type.equals("content")) {
			result = documentUserRepository.searchShareBoxContent(userNo, search, pageable);
		}else if(type.equals("userName")) {
			result = documentUserRepository.searchShareBoxUserName(userNo, search, pageable);
		}
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));

		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getShareDocumentSearchList(userNo, search, pageRequestDTO, type);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantDocumentSearchList(Long userNo, String search, PageRequestDTO pageDTO, String type) {
		Pageable pageable = pageDTO.getPageable(Sort.by("document_no").descending());

		Page<DocumentUser> result = null;
		if(type.equals("originalName")) {
			result = documentUserRepository.searchImportantBox(userNo, search, pageable);
		}else if(type.equals("content")) {
			result = documentUserRepository.searchImportantBoxContent(userNo, search, pageable);
		}else if(type.equals("userName")) {
			result = documentUserRepository.searchImportantBoxUserName(userNo, search, pageable);
		}
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));

		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getImportantDocumentSearchList(userNo, search, pageRequestDTO, type);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleDocumentSearchList(Long userNo, String search, PageRequestDTO pageDTO, String type) {
		Pageable pageable = pageDTO.getPageable(Sort.by("document_no").descending());
		
		Page<DocumentUser> result = null;
		if(type.equals("originalName")) {
			result = documentUserRepository.searchRecycleBox(userNo, search, pageable);
		}else if(type.equals("content")) {
			result = documentUserRepository.searchRecycleBoxContent(userNo, search, pageable);
		}else if(type.equals("userName")) {
			result = documentUserRepository.searchRecycleBoxUserName(userNo, search, pageable);
		}
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));

		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(pageDTO.getPage()).size(10).build();
		PageResultDTO<DocumentUserDTO, DocumentUser> pageResultDTO = new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
		
		if(pageResultDTO.getDtoList().size() == 0 && pageDTO.getPage() != 1 && pageDTO.getPage()-1 == pageResultDTO.getTotalPage()) {
			pageRequestDTO.setPage(pageDTO.getPage()-1);
			pageResultDTO = getRecycleDocumentSearchList(userNo, search, pageRequestDTO, type);
		}
		
		List<DocumentUserDTO> resultBoards = new ArrayList<DocumentUserDTO>(); 
		pageResultDTO.getDtoList().forEach(BoardDTO -> resultBoards.add(BoardDTO));
		
		return pageResultDTO;
	}

	

}
