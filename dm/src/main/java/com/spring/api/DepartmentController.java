package com.spring.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.DepartmentDTO;

import com.spring.service.DepartmentServiceImpl;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentServiceImpl departmentService;
	
	@GetMapping("/alldepartment")
	public List<DepartmentDTO> getAllDepartment(){
		return departmentService.getAllDepartment();
	}
	
}
