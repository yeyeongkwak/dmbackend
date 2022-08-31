package com.spring.service;

import java.util.List;

import org.hibernate.annotations.Sort;

import com.spring.dto.NoticeDTO.NoticeRequest;
import com.spring.dto.NoticeDTO.NoticeResponse;
import com.spring.entity.Notice;
import com.spring.entity.User;

public interface NoticeService {
	
	List<NoticeResponse> findAllNotices();
	
	//receiver인 user_no로 사용자가 받은 모든 알림 표시
	List<NoticeResponse> findAllNoticeByReceiverUserNo(Long receiverNo);
	
	public Notice insertNotice(NoticeRequest noticeDTO);
	
	public NoticeResponse getNoticeByNoticeno(Long noticeNo);
	
	public void deleteNotice(Long noticeNo);
	
	public void updateNotice(Long noticeNo, NoticeRequest noticeDTO);
	
	public void sendDocsNotice(User sender, User receiver, String content, Integer isRead);

	public void sendWorkSpaceNotice(User sender, User receiver, String content, Integer isRead);

	public void deleteAllNotice(Long receiverNo);
	
	public void updateAllNotice(Long receiverNo, List<NoticeRequest> noticeDTOList);

	public void sendAddMember(User sender, User receiver, String content, Integer isRead);

}
