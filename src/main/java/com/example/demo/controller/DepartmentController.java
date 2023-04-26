package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentForm;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	

	@RequestMapping(value = { "/department" }, method = { RequestMethod.GET})
	public ModelAndView viewDepartment(Model model,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal) {
	
	
		ModelAndView mv = new ModelAndView("Department");
		try {
			//--search--
			List<Department> departmentList=departmentService.findActiveDepartment();
			model.addAttribute("departmentList",departmentList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	@RequestMapping(value = { "/searchdepartment" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchDepartment(Model model,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal) {

		ModelAndView mv = new ModelAndView("Department");
		try {
			List<Department> departmentList=departmentService.findDepartment(departmentForm.getDepartmentName(),
					departmentForm.getDepartmentDescription());
			model.addAttribute("departmentList",departmentList);
			
			if(departmentList!=null && departmentList.isEmpty()) {
				departmentForm.setErrorMessage("No search data found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	@RequestMapping(value = { "/adddepartment" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addDepartment(Model model,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal) {
	
		ModelAndView mv = new ModelAndView("AddDepartment");
		departmentForm.setMethodType("add");
		return mv;
	}
	
	
	
	@RequestMapping(value = { "/departmentEdit/{departmentId}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView departmentEdit(Model model,
			@PathVariable("departmentId") Integer departmentId,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal) 
	{
		
		ModelAndView mv = new ModelAndView("AddDepartment");
		Department department = null;
		Optional<Department> departmentOptional = departmentService.findDepartmentId(departmentId);
		
		if (departmentOptional.isPresent()) {
			department = departmentOptional.get();
			departmentForm.setDepartmentName(department.getDepartmentName());
			departmentForm.setDepartmentDescription(department.getDepartmentDescription());
		}
		departmentForm.setMethodType("update");
		departmentForm.setDepartmentId(departmentId.toString());
		return mv;
		
	}
	
	
	@RequestMapping(value = { "/departmentDelete/{departmentId}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView departmentDelete(Model model,
			@PathVariable(value = "departmentId") Integer departmentId,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal)
	{
	
		ModelAndView mv = new ModelAndView("Department");
		departmentService.departmentDelete(departmentId);
		departmentForm.setSuccessMessage("Deleted  sucessfully !.");
		model.addAttribute("departmentList",departmentService.findActiveDepartment());
		return mv;
	}
	
	
	
	@RequestMapping(value = { "/createdepartment" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView createDepartment(Model model,
			@ModelAttribute("departmentForm") DepartmentForm departmentForm, Principal principal) {

	
	
		ModelAndView mv = departmentService.createAndUpdateDepartment(departmentForm, model);
		try {
//			if(departmentForm.getMethodType()!=null && departmentForm.getMethodType().equalsIgnoreCase("add")) {
//			
//				Department department= new Department();
//				department.setDepartmentName(departmentForm.getDepartmentName());
//				department.setDepartmentDescription(departmentForm.getDepartmentDescription());
//				
//				department.setActiveStatus("Y");
//				departmentService.saveDepartment(department);
//				
//				departmentForm.setSuccessMessage("Department Created Successfully");
//				//setting id after save
//				departmentForm.setDepartmentId(department.getDepartmentId().toString());
//				
//			
//				List<Department> departmentList=departmentService.findActiveDepartment();
//				model.addAttribute("departmentList",departmentList);
//				
//			}
//			
//			
//			//UPDATE
//			if(departmentForm.getMethodType()!=null && departmentForm.getMethodType().equalsIgnoreCase("update") )
//			{
//				Department department = null;
//				Optional<Department> departmentOptional = departmentService.findDepartmentId(new Integer(departmentForm.getDepartmentId()));
//				
//				if (departmentOptional.isPresent()) {
//					department=departmentOptional.get();
//					department.setDepartmentName(departmentForm.getDepartmentName());
//					department.setDepartmentDescription(departmentForm.getDepartmentDescription());
//					departmentService.saveDepartment(department);
//					
//					List<Department> departmentList=departmentService.findActiveDepartment();
//					model.addAttribute("departmentList",departmentList);
//					departmentForm.setSuccessMessage("Update sucessfully !.");
//					departmentForm.setMethodType("update");
//				}
//			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
}
