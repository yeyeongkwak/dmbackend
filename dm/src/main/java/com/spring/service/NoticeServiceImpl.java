package com.spring.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.annotations.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.NoticeDTO;
import com.spring.dto.NoticeDTO.NoticeRequest;
import com.spring.dto.NoticeDTO.NoticeResponse;
import com.spring.entity.Notice;
import com.spring.entity.User;
import com.spring.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

	private final NoticeRepository noticeRepository;
	private final SimpMessagingTemplate messagingTemplate;
	
	@Override
	@Transactional(readOnly=true) //import org.springframework.transaction.annotation.Transactional;를 임포트 해야함
	public List<NoticeResponse> findAllNotices() {
		List<NoticeDTO.NoticeResponse> noticeList = 
				noticeRepository.findAll().stream()
				.map(NoticeDTO.NoticeResponse::new)
				.collect(Collectors.toList());
		return noticeList;
	}
	@Override
	@Transactional
	public Notice insertNotice(NoticeRequest noticeDTO) {
		Notice newNotice = noticeDTO.toEntity();
		return noticeRepository.save(newNotice);
	}
	
	@Override
	@Transactional
	public void deleteNotice(Long noticeNo) {
		Notice noticeEntity = noticeRepository.findById(noticeNo).orElseThrow(()->new NoSuchElementException("해당 알림은 존재하지 않습니다"));
		if(noticeEntity.getNoticeNo().equals(noticeNo)) {
			noticeRepository.delete(noticeEntity);
		}
		
	}
	@Override
	@Transactional(readOnly = true)
	public NoticeResponse getNoticeByNoticeno(Long noticeNo) {
		Notice noticeEntity = noticeRepository.findByNoticeNo(noticeNo);
		return new NoticeDTO.NoticeResponse(noticeEntity);
	}
	

	@Override
	public List<NoticeResponse> findAllNoticeByReceiverUserNo(Long receiverNo) {
		List<NoticeDTO.NoticeResponse> noticeList = noticeRepository.findAllNoticeByReceiverUserNo(receiverNo)
				.stream()
				.map(NoticeDTO.NoticeResponse::new)
				.collect(Collectors.toList());
		return noticeList;
	}
	
	@Override
	public void updateNotice(Long noticeNo, NoticeRequest noticeDTO) {
		Notice notice = noticeRepository.findByNoticeNo(noticeNo);
		if(notice.getNoticeNo().equals(noticeNo)){
			notice.updateNotice(noticeDTO.getIsRead()==null?notice.getIsRead():noticeDTO.getIsRead());
		}
		noticeRepository.save(notice);
		
	}
	
//	@Override
//	public void sendGlobalNotice(User sender, User receiver, String content, Integer isRead) {
//		NoticeRequest newNotice = new NoticeRequest(sender,receiver, content, isRead );
//		messagingTemplate.convertAndSend("/topic/notice", newNotice);
//		noticeRepository.save(newNotice.toEntity());
//	}
//	
//	@Override
//	public void sendWorkSpaceNotice(User sender, User receiver, String content, Integer isRead) {
//		NoticeRequest newNotice = new NoticeRequest(sender, receiver, content, isRead);
//		messagingTemplate.convertAndSend("/queue/workspace/"+receiver.getId(),newNotice);
//		noticeRepository.save(newNotice.toEntity());		
//	}
	@Override
	public void sendDocsNotice(User sender, User receiver, String content, Integer isRead) {
		NoticeRequest newNotice = new NoticeRequest(sender, receiver, content, isRead);
		messagingTemplate.convertAndSend("/queue/sharedocs/"+receiver.getId(),newNotice);
		noticeRepository.save(newNotice.toEntity());		
		System.out.println("id:"+receiver.getId());
	}
	
	@Override
	public void sendWorkSpaceNotice(User sender, User receiver, String content, Integer isRead) {
		NoticeRequest newNotice = new NoticeRequest(sender, receiver, content, isRead);
		messagingTemplate.convertAndSend("/queue/workspace/"+receiver.getId(),newNotice);
		noticeRepository.save(newNotice.toEntity());		
		System.out.println("id:"+receiver.getId());
	}
//	@Override
//	public void sendWorkSpaceNotice(User sender, User receiver, String content, Integer isRead) {
//		// TODO Auto-generated method stub
//		
//	}
	
//	@Override
//	public void sendWorkSpaceNoticeById(String receiverId, User sender, User receiver, String content, Integer isRead) {
//		NoticeRequest newNotice = new NoticeRequest(sender, receiver, content, isRead);
//		messagingTemplate.convertAndSendToUser(receiver.getId(), "/queue/workspace/notice", newNotice);
//		System.out.println(receiver.getId());
//		System.out.println(receiver.getUserNo().toString());
//		System.out.println(receiver.getName());
//		noticeRepository.save(newNotice.toEntity());
//	}
//	@Override
//	public void sendWorkSpaceNoticeById(User sender, User receiver, String content, Integer isRead) {
//		NoticeRequest newNotice = new NoticeRequest(sender, receiver, content, isRead);
//		messagingTemplate.convertAndSend("/queue/workspace-notice",  newNotice);
//		System.out.println(receiver.getId());
//		System.out.println(receiver.getUserNo().toString());
//		System.out.println(receiver.getName());
//		noticeRepository.save(newNotice.toEntity());
//	}
	
	
	



}
