package com.spring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "notice")
@Getter
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_no")
	private Long noticeNo;
	
	@ManyToOne
	@JoinColumn(name="sender")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name="receiver")
	private User receiver;
	
	@Size(min=1, max = 200)
	private String content;
	
	@Column(name = "is_read", columnDefinition = "TINYINT(1) default 0")
	private Integer isRead;
		
	@Column(name="send_date")
	@CreatedDate
	private LocalDateTime sendDate;
	
	public void updateNotice(Integer isRead) {
		this.isRead = isRead;
	
	}
}

