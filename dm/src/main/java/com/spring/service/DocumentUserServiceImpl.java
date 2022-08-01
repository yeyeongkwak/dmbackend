package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dto.DocumentUserDTO;
import com.spring.entity.DocumentUser;
import com.spring.repository.DocumentUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentUserServiceImpl implements DocumentUserService {
	
	private final DocumentUserRepository documentUserRepository;
	
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
	public void deleteDocumentUser(DocumentUserDTO documentUserDTO) {
		documentUserRepository.delete(documentUserDTO.toEntity(documentUserDTO));
	}
	
	@Override
	public void updateDocumentUser(DocumentUserDTO documentUserDTO) {
		DocumentUserDTO oldDocumentUserDTO = getDocumentUserByUserNoAndDocumentNo(documentUserDTO.getUserNo().getUserNo(),
																			documentUserDTO.getDocumentNo().getDocumentNo());
		if(oldDocumentUserDTO != null) {
			DocumentUserDTO newDocumentUserDTO = new DocumentUserDTO(oldDocumentUserDTO,documentUserDTO);
			documentUserRepository.save(newDocumentUserDTO.toEntity(newDocumentUserDTO));
		}
	}

}
