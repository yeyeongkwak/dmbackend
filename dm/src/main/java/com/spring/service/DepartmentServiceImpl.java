package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dto.DepartmentDTO;
import com.spring.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	
	public List<DepartmentDTO> getAllDepartment() {
		List<DepartmentDTO> departmentDTOList = new ArrayList<DepartmentDTO>();
		departmentRepository.findAll().forEach(v -> departmentDTOList.add(v.toDTO(v)));
		return departmentDTOList;
	}

}
