package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	@Query("select c from Employee c where c.activeStatus='Y'")
	public List<Employee> findActiveEmployee();
	
	
	@Query("select c from Employee c where c.activeStatus='Y' and c.employeeId =:employeeId")
	public Optional<Employee> findEmployeeId(Integer employeeId);
	
	@Query("select c from Employee c "
			+ " where c.activeStatus='Y'  "
			+ " and upper(c.employeeName) like upper(:employeeName) "
			+ " and upper(c.employeeDescription) like upper(:employeeDescription) "
			)
	public List<Employee> findEmployee(String employeeName,String employeeDescription);
	
}
