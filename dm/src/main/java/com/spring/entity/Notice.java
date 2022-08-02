package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@ToString
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
	
	@Column(name="related_url")
	private String relatedUrl;
	
	@ManyToOne
	@JoinColumn(name="document_no")
	private Document document;
	
	public void updateNotice(Integer isRead) {
		this.isRead = isRead;
	
	}
}
