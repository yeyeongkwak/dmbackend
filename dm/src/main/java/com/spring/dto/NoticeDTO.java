package com.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

	private Long noticeNo;
	
	private Long sender;
	
	private Long receiver;
	
	private String content;
}
