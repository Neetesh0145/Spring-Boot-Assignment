package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;


@Service
public class EmployeeService {

	
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	
	public List<Employee> findActiveEmployee(){
		return employeeRepository.findActiveEmployee();
	}
	
	public Optional<Employee> findEmployeeId(Integer employeeId){
		return employeeRepository.findEmployeeId(employeeId);
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.saveAndFlush(employee);
	}
	
	public List<Employee> findEmployee(String employeeName,String employeeDescription)
	{
		return employeeRepository.findEmployee(employeeName, employeeDescription);
	}
}
