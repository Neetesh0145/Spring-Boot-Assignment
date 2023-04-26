package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	@Query("select c from Department c where c.activeStatus='Y'")
	public List<Department> findActiveDepartment();
	
	
	@Query("select c from Department c where c.activeStatus='Y' and c.departmentId =:departmentId")
	public Optional<Department> findDepartmentId(Integer departmentId);
	
	@Query("select c from Department c "
			+ " where c.activeStatus='Y'  "
			+ " and upper(c.departmentName) like upper(:departmentName) "
			+ " and upper(c.departmentDescription) like upper(:departmentDescription) "
			)
	public List<Department> findDepartment(String departmentName,String departmentDescription);
	
	
}
