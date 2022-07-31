package com.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	
	public Document findDocumentByDocumentNo(Long documentNo);
	
	@Transactional
	public void deleteDocumentByDocumentNo(Long documentNo);
}
