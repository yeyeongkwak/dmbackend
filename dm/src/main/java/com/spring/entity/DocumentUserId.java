package com.spring.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DocumentUserId implements Serializable{
	private Long userNo;
	private Long documentNo;
}
