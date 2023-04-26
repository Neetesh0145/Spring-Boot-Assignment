package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentForm;
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
		List<Department> departmentList=departmentRepository.findDepartment(
				
				"%" + (departmentName == null || departmentName.equals("") ? ""
						: departmentName.trim()) + "%",
				
				"%" + (departmentDescription == null || departmentDescription.equals("") ? ""
						: departmentDescription.trim()) + "%"
				);
	
		return departmentList;
	}
	
	
	public Optional<Department> departmentDelete(Integer departmentId){
		
		Department department = null;
		
		Optional<Department> departmentOptional = departmentRepository.findDepartmentId(departmentId);
		if (departmentOptional.isPresent()) {
			department = departmentOptional.get();
			department.setActiveStatus("N");
			this.saveDepartment(department);
		}
		
		return departmentOptional;
	}
	
	
	public ModelAndView createAndUpdateDepartment(DepartmentForm departmentForm,Model model) {
		ModelAndView mv = new ModelAndView("Department");
		
		try {
			if(departmentForm.getMethodType()!=null && departmentForm.getMethodType().equalsIgnoreCase("add")) {
				
					Department department= new Department();
					department.setDepartmentName(departmentForm.getDepartmentName());
					department.setDepartmentDescription(departmentForm.getDepartmentDescription());
					
					department.setActiveStatus("Y");
					this.saveDepartment(department);
					
					departmentForm.setSuccessMessage("Department Created Successfully");
					//setting id after save
					departmentForm.setDepartmentId(department.getDepartmentId().toString());
					
				
					List<Department> departmentList=this.findActiveDepartment();
					model.addAttribute("departmentList",departmentList);
					
				}
				
				
				//UPDATE
				if(departmentForm.getMethodType()!=null && departmentForm.getMethodType().equalsIgnoreCase("update") )
				{
					Department department = null;
					Optional<Department> departmentOptional = this.findDepartmentId(new Integer(departmentForm.getDepartmentId()));
					
					if (departmentOptional.isPresent()) {
						department=departmentOptional.get();
						department.setDepartmentName(departmentForm.getDepartmentName());
						department.setDepartmentDescription(departmentForm.getDepartmentDescription());
						this.saveDepartment(department);
						
						List<Department> departmentList=this.findActiveDepartment();
						model.addAttribute("departmentList",departmentList);
						departmentForm.setSuccessMessage("Update sucessfully !.");
						departmentForm.setMethodType("update");
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return mv;
	}
	
}
