package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.spring.model.Authority;

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
@IdClass(DocumentUserId.class)
public class DocumentUser {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User userNo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "document_no")
	private Document documentNo;
	
	@Size(max = 1)
	private Integer important;
	
	@Size(max = 1)
	private Integer recycleBin;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('read','write','master')")
	private Authority authority;
}
