package com.spring.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.entity.Document;
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
	
	private Integer isRead;
	
	private LocalDateTime sendDate;
	
	private String urlParams;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	
	public static class NoticeRequest{
		
		private User sender;
		
		private User receiver;
		
		private String content;
		
		private Integer isRead;
		
		private String urlParams;
		
		
		public Notice toEntity() {
			Notice notice = Notice.builder()
							.sender(sender)
							.receiver(receiver)
							.content(content)
							.isRead(isRead)
							.urlParams(urlParams)
							.build();
			return notice;
			
		}
	}
	
	@Getter
	public static class NoticeResponse{
		
		private final Long noticeNo;
		
		private final User sender;
		
		private final User receiver;

		private final String content;
		
		private final Integer isRead;
		
		private final String urlParams;
		
		@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
		private final LocalDateTime sendDate;
		
		public NoticeResponse(Notice notice) {
			this.noticeNo=notice.getNoticeNo();
			this.sender=notice.getSender(); 
			this.receiver=notice.getReceiver(); 
			this.content=notice.getContent();
			this.isRead=notice.getIsRead();
			this.sendDate = notice.getSendDate();
			this.urlParams = notice.getUrlParams();
					
		}
	}
	
}

