package com.spring.service;

import java.util.List;

import com.spring.dto.NoticeDTO.NoticeRequest;
import com.spring.dto.NoticeDTO.NoticeResponse;
import com.spring.entity.Notice;

public interface NoticeService {
	
	List<NoticeResponse> findAllNotices();
	
	//receiver인 user_no로 사용자가 받은 모든 알림 표시
	List<NoticeResponse> findAllNoticeByReceiverUserNo(Long receiverNo);
	
	public Notice insertNotice(NoticeRequest noticeDTO);
	
	public NoticeResponse getNoticeByNoticeno(Long noticeNo);
	
	public void deleteNotice(Long noticeNo);
	
}
