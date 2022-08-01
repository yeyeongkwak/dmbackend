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

import com.spring.dto.UserDTO;

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
@EntityListeners(AuditingEntityListener.class)
@ToString

public class User {
	
	@Id
	@Column(name = "user_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;
	
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Department dept;
	
	@Size(max = 20)
	private String id;
	
	@Size(max = 30)
	private String password;
	
	@Size(max = 10)
	private String name;
	
	@Size(max = 40)
	private String email;
	
	@CreatedDate
	private LocalDateTime registerDate;
	
	@Size(max = 300)
	private String profile;
	
	public UserDTO toDTO(User user) {
		UserDTO userDTO = UserDTO.builder()
							.userNo(user.getUserNo())
							.dept(user.getDept().toDTO(user.getDept()))
							.id(user.getId())
							.password(user.getPassword())
							.name(user.getName())
							.email(user.getEmail())
							.registerDate(user.getRegisterDate())
							.profile(user.getProfile())
							.build();
		
		return userDTO;		

	}
}
