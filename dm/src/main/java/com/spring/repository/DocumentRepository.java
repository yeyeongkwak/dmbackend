package com.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;

public interface DocumentRepository extends JpaRepository<Document, Long> {
   
   public Document findDocumentByDocumentNo(Long documentNo);
   
   public Page<Document> findDocumentByUser(User userNo, Pageable pageable);
   
   @Transactional 
   public void deleteDocumentByDocumentNo(Long documentNo);
   
   @Query(value = "SELECT ROUND(SUM(file_size), 2) FROM document WHERE user_no = :userNo", nativeQuery = true)	
   public Double findDocumentSize(Long userNo);
}