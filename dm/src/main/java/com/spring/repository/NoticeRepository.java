package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	Notice findByNoticeNo(Long noticeNo);
		
	List<Notice> findAllNoticeByReceiverUserNo(Long receiverNo);
	
}
