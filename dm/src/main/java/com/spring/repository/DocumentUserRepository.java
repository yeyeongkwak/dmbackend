package com.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.DocumentUser;
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
	
	@Query(value = "SELECT * FROM document_user WHERE user_no = :userNo"
			+ " AND recycle_bin = '0'"
			+ " AND authority = 'MASTER'"
			+ " AND document_no IN (SELECT document_no FROM document WHERE original_name LIKE %:originalName%)", nativeQuery = true) 			
	public Page<DocumentUser> findAllByMyBox(Long userNo, String originalName, Pageable pageable);
	
	@Query(value = "SELECT * FROM document_user WHERE user_no = :userNo"
			+ " AND recycle_bin = '0'"
			+ " AND NOT authority = 'MASTER'"
			+ " AND document_no IN (SELECT document_no FROM document WHERE original_name LIKE %:originalName%)", nativeQuery = true) 			
	public Page<DocumentUser> findAllByShareBox(Long userNo, String originalName, Pageable pageable);
	
	@Query(value = "SELECT * FROM document_user WHERE user_no = :userNo"
			+ " AND recycle_bin = '0'"
			+ " AND important = '1'"
			+ " AND document_no IN (SELECT document_no FROM document WHERE original_name LIKE %:originalName%)", nativeQuery = true) 			
	public Page<DocumentUser> findAllByImportantBox(Long userNo, String originalName, Pageable pageable);
	
	@Query(value = "SELECT * FROM document_user WHERE user_no = :userNo"
			+ " AND recycle_bin = '1'"
			+ " AND document_no IN (SELECT document_no FROM document WHERE original_name LIKE %:originalName%)", nativeQuery = true) 			
	public Page<DocumentUser>  findAllByRecycleBox(Long userNo, String originalName, Pageable pageable);
	

}
