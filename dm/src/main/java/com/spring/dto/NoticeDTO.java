package com.spring.dto;


import com.spring.entity.Notice;
import com.spring.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
	

	private Long noticeNo;
	
	private UserDTO sender;
	
	private UserDTO receiver;
	
	private String content;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	
	public static class NoticeRequest{
		
		private User sender;
		
		private User receiver;
		
		private String content;
		
		public Notice toEntity() {
			Notice notice = Notice.builder()
							.sender(sender)
							.receiver(receiver)
							.content(content)
							.build();
			return notice;
			
		}
	}
	
	@Getter
	public static class NoticeResponse{
		
		private final Long noticeNo;
		
		private final String sender;
		
		private final String receiver;
		
		private final String content;
		
		public NoticeResponse(Notice notice) {
			this.noticeNo=notice.getNoticeNo();
			this.sender=notice.getSender().getName(); //바로 이름 출력하려고 getName 사용
			this.receiver=notice.getReceiver().getName(); //바로 이름 출력하려고 getName 사용
			this.content=notice.getContent();
		}
	}
	
}
