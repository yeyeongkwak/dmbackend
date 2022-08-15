package com.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
import com.spring.repository.DocumentUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentUserServiceImpl implements DocumentUserService {
	
	private final DocumentUserRepository documentUserRepository;
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getList(Long userNo, PageRequestDTO pageRequestDTO, Integer recycle) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
		recycle = 0;
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndRecycleBin(userNo, pageable, recycle);

		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getImportantList(Long userNo, PageRequestDTO pageRequestDTO, Integer important, Integer recycle) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
		important = 1;
		recycle = 0;
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndImportantAndRecycleBin(userNo, pageable, important, recycle);
		
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
	}
	
	@Override
	public PageResultDTO<DocumentUserDTO, DocumentUser> getRecycleList(Long userNo, PageRequestDTO pageRequestDTO, Integer recycle) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("documentNo").descending());
		recycle = 1;
		Page<DocumentUser> result =  documentUserRepository.findDocumentUserByUserNoUserNoAndRecycleBin(userNo, pageable, recycle);
		
		Function<DocumentUser, DocumentUserDTO> function = (Document -> Document.toDTO(Document));
		
		return new PageResultDTO<DocumentUserDTO, DocumentUser>(result, function);
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
		
		documentUserDTOs.forEach(v-> documentUsers.add(v.toEntity(v)));
		documentUserRepository.saveAll(documentUsers);
	}
	
	@Override
	public void deleteDocumentUser(List<Long> documentNo, Long userNo) {
		for (int i = 0; i < documentNo.size(); i++) {
//			documentUserRepository.delete(documentNo.get(i).toEntity(documentNo.get(i)));
			documentUserRepository.deleteDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(userNo, documentNo.get(i));
		}
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

}
