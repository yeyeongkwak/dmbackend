package com.spring.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.NoticeDTO.NoticeRequest;
import com.spring.dto.NoticeDTO.NoticeResponse;
import com.spring.entity.Notice;
import com.spring.repository.NoticeRepository;
import com.spring.service.NoticeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor


public class NoticeController {
	
	private final NoticeServiceImpl noticeService;
	
	//모든 알림 리스트 조회
	@GetMapping(value = "/notice/all")
	
	public List<NoticeResponse> findAllNotice(){
		return noticeService.findAllNotices();
	}
	
	//receiver에 해당하는 user번호로 알림 전체 조회하기
	@GetMapping(value = "/notice/receiver/{receiverNo}")
	public List<NoticeResponse> findUserNotice(@PathVariable Long receiverNo){
		return noticeService.findAllNoticeByReceiverUserNo(receiverNo);
	}
	
	//notice번호로 notice정보 조회
	@GetMapping(value = "/notice/{noticeNo}")
	NoticeResponse getNoticeByNoticNo(@PathVariable Long noticeNo) {
		return noticeService.getNoticeByNoticeno(noticeNo);
	}
	
	//알림 추가
	@PostMapping(value="/notice")
	public Notice insertNotice(@RequestBody NoticeRequest noticeDTO) {
		return noticeService.insertNotice(noticeDTO);
	}
	
	//notice번호로 알림 삭제하기
	@DeleteMapping("/notice/{noticeNo}")
	public void deleteNotice(@PathVariable Long noticeNo) {
		noticeService.deleteNotice(noticeNo);
	}
	
	//notice번호로 알림 읽음 표시하기
	@PutMapping("/notice/{noticeNo}")
	public void updateNotice(@PathVariable Long noticeNo, @RequestBody NoticeRequest noticeDTO) {
		noticeService.updateNotice(noticeNo, noticeDTO);
	}
	
	@MessageMapping("/sharedocs")
	public void getDocsNotice(NoticeRequest notice) {
		noticeService.sendDocsNotice(notice.getSender(), notice.getReceiver(), notice.getContent(), notice.getIsRead());
	}
	
	@MessageMapping("/workspace")
	public void getWorkSpaceMessage(NoticeRequest notice) {
		noticeService.sendWorkSpaceNotice(notice.getSender(), notice.getReceiver(), notice.getContent(), notice.getIsRead());
			
	};
	
	@MessageMapping("/workspace/add")
	public void getExtraMember(NoticeRequest notice) {
		noticeService.sendAddMember(notice.getSender(), notice.getReceiver(), notice.getContent(), notice.getIsRead());
	}
	
	@DeleteMapping("/notice/receiver/{receiverNo}/all")
	public void deleteAllNotices(@PathVariable Long receiverNo) {
		noticeService.deleteAllNotice(receiverNo);
	}
	
	@PutMapping("/notice/receiver/{receiverNo}/all")
	public void updateAllNotices(@PathVariable Long receiverNo, @RequestBody List<NoticeRequest> noticeDTOList) {
		noticeService.updateAllNotice(receiverNo, noticeDTOList);
	}
}
