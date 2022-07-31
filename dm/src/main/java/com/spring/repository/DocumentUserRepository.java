package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Document;
import com.spring.entity.DocumentUser;

public interface DocumentUserRepository extends JpaRepository<DocumentUser, Long>{
	
	public DocumentUser findDocumentUserByUserNo(Long userNo);

}
