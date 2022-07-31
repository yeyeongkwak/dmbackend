package com.spring.service;

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
	public DocumentUserDTO getDocumentUserByUserNo(Long userNo) {
		DocumentUser documentUser = documentUserRepository.findDocumentUserByUserNo(userNo);
		return documentUser == null ? null : documentUser.toDTO(documentUser);
	}

}
