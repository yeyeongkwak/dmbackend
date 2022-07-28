package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentUserDTO {

	private Long userNo;
	
	private Long documentNo;
	
	private Byte important;
	
	private Byte recycleBin;
	
}
