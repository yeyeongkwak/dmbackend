package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Document;
import com.spring.entity.DocumentUser;

public interface DocumentUserRepository extends JpaRepository<DocumentUser, Long>{
	
	public List<DocumentUser> findDocumentUserByUserNoUserNo(Long userNo);
	
	public DocumentUser findDocumentUserByUserNoUserNoAndDocumentNoDocumentNo(Long userNo,Long documentNo);

}
