package com.spring.dto;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long userNo;
	
	private Long deptNo;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private Date registerDate;
	
	private String profile;
	
}
