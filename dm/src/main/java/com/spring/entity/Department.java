package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.spring.dto.DepartmentDTO;

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
public class Department {
	@Id
	@Column(name = "dept_no")
	private Long deptNo;
	
	@Column(name="dept_name")
//	@Size(max = 20)
	private String deptName;
	
	public DepartmentDTO toDTO(Department department) {
		DepartmentDTO departmentDTO = DepartmentDTO.builder()
										.deptNo(department.getDeptNo())
										.deptName(department.getDeptName())
										.build();
		return departmentDTO;
	}
}
