package com.spring.dto;


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
	
	private Byte isRead;
	
	private String relatedUrl;
	
	private DocumentDTO document;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	
	public static class NoticeRequest{
		
		private User sender;
		
		private User receiver;
		
		private String content;
		
		private Integer isRead;
		
		private String relatedUrl;
		
		private Document document;
		
		public Notice toEntity() {
			Notice notice = Notice.builder()
							.sender(sender)
							.receiver(receiver)
							.content(content)
							.isRead(isRead)
							.relatedUrl("/document/"+document.getDocumentNo()) //insert시 해당 document url로 바로 이동하기 위해 값을 미리 지정
							.document(document)
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
		
		private final Integer isRead;
		
		private final String relatedUrl;
		
		private final Document document;
		
		public NoticeResponse(Notice notice) {
			this.noticeNo=notice.getNoticeNo();
			this.sender=notice.getSender().getName(); //바로 이름 출력하려고 getName 사용
			this.receiver=notice.getReceiver().getName(); //바로 이름 출력하려고 getName 사용
			this.content=notice.getContent();
			this.isRead=notice.getIsRead();
			this.relatedUrl=notice.getRelatedUrl();
			this.document=notice.getDocument();
		}
	}
	
}
