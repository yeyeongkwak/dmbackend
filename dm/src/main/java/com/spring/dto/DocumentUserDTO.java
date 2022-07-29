package com.spring.dto;

import com.spring.model.Authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentUserDTO {

	private Long userNo;
	
	private Long documentNo;
	
	private Integer important;
	
	private Integer recycleBin;
	
	private Authority authority;
}
