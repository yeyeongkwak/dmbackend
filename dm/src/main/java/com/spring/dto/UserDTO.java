package com.spring.dto;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Long userNo;
	
	private Long deptNo;
	
	private String id;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private Date registerDate;
	
	private String profile;
	
}
