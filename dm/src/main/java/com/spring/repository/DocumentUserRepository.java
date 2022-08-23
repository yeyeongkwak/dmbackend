package com.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;
import com.spring.model.Authority;

public interface DocumentUserRepository extends JpaRepository<DocumentUser, Long>{
	
	public Page<DocumentUser> findDocumentUserByUserNoUserNo(Long userNo, Pageable pageable);
	
	public Page<DocumentUser> findDocumentUserByUserNoUserNoAndRecycleBinAndAuthorityNot(Long userNo, Pageable pageable, Integer recycle, Authority authority);


	public Page<DocumentUser> findDocumentUserByUserNoUserNoAndImportantAndRecycleBin(Long userNo, Pageable pageable, Integer important, Integer recycle);
	
	public Page<DocumentUser> findDocumentUserByUserNoUserNoAndRecycleBinAndAuthority(Long userNo, Pageable pageable, Integer recycle, Authority authority);
	
	public Page<DocumentUser> findDocumentUserByUserNoUserNoAndRecycleBin(Long userNo, Pageable pageable, Integer recycle);
	
	public List<DocumentUser> findDocumentUserByUserNoUserNo(Long userNo);
	
	public DocumentUser findDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(Long userNo,Long documentNo);

	public void deleteDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(Long userNo, Long documentNo);

	public List<DocumentUser> findAllByDocumentNoDocumentNo(Long documentNo);
}
