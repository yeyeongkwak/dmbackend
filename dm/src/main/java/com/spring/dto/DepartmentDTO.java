package com.spring.dto;

import com.spring.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
	private Long deptNo;
	
	private String deptName;
	
	public Department toEntity(DepartmentDTO departmentDTO) {
		Department department = Department.builder()
								.deptNo(departmentDTO.getDeptNo())
								.deptName(departmentDTO.getDeptName())
								.build();
		return department;
	}
}
