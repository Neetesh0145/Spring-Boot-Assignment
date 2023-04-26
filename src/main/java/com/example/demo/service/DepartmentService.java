package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	
	@Autowired
	public DepartmentRepository departmentRepository;
	
	
	public List<Department> findActiveDepartment(){
		return departmentRepository.findActiveDepartment();
	}
	
	public Optional<Department> findDepartmentId(Integer departmentId){
		return departmentRepository.findDepartmentId(departmentId);
	}

	public Department saveDepartment(Department department) {
		return departmentRepository.saveAndFlush(department);
	}
	
	public List<Department> findDepartment(String departmentName,String departmentDescription)
	{
		return departmentRepository.findDepartment(departmentName, departmentDescription);
	}
}
