package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	Notice findByNoticeNo(Long noticeNo);
	
	@Query(value="SELECT * FROM notice WHERE receiver = :receiverNo ORDER BY notice_no DESC", nativeQuery = true)
	List<Notice> findAllNoticeByReceiverUserNo(Long receiverNo);
	
}
