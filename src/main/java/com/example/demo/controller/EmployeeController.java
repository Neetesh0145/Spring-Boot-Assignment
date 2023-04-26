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

import com.example.demo.entity.Employee;
import com.example.demo.form.EmployeeForm;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
		
	
	//------
	@RequestMapping(value = { "/employee" }, method = { RequestMethod.GET})
	public ModelAndView viewEmployee(Model model,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal) {
	
	
		ModelAndView mv = new ModelAndView("Employee");
		try {
			//--search--
			List<Employee> employeeList=employeeService.findActiveEmployee();
			model.addAttribute("employeeList",employeeList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	//------
	@RequestMapping(value = { "/searchemployee" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchEmployee(Model model,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal) {
	

		ModelAndView mv = new ModelAndView("Employee");
		
		try {
			//search
			List<Employee> employeeList=employeeService.findEmployee(
					
					"%" + (employeeForm.getEmployeeName() == null || employeeForm.getEmployeeName().equals("") ? ""
							: employeeForm.getEmployeeName().trim()) + "%",
					
					"%" + (employeeForm.getEmployeeDescription() == null || employeeForm.getEmployeeDescription().equals("") ? ""
							: employeeForm.getEmployeeDescription().trim()) + "%"
					);
			model.addAttribute("employeeList",employeeList);
			
			if(employeeList!=null && employeeList.isEmpty()) {
				employeeForm.setErrorMessage("No search data found");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	//------
	@RequestMapping(value = { "/addemployee" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addEmployee(Model model,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal) {
	
		ModelAndView mv = new ModelAndView("AddEmployee");
		
		employeeForm.setMethodType("add");
		
		return mv;
	}
	
	
	
	//------
	@RequestMapping(value = { "/employeeEdit/{employeeId}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView employeeEdit(Model model,
			@PathVariable("employeeId") Integer employeeId,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal) 
	{
		
		ModelAndView mv = new ModelAndView("AddEmployee");
	
		
		Employee employee = null;
		Optional<Employee> employeeOptional = employeeService.findEmployeeId(employeeId);
		
		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
			employeeForm.setEmployeeName(employee.getEmployeeName());
			employeeForm.setEmployeeDescription(employee.getEmployeeDescription());
		}
		employeeForm.setMethodType("update");
		employeeForm.setEmployeeId(employeeId.toString());
		return mv;
	}
	
	//------
	@RequestMapping(value = { "/employeeDelete/{employeeId}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView employeeDelete(Model model,
			@PathVariable(value = "employeeId") Integer employeeId,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal)
	{
	
		ModelAndView mv = new ModelAndView("Employee");
		
		Employee employee = null;
		Optional<Employee> employeeOptional = employeeService.findEmployeeId(employeeId);
		
		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
			employee.setActiveStatus("N");
			employeeService.saveEmployee(employee);
	
			employeeForm.setSuccessMessage("Deleted  sucessfully !.");
		} 
		//search
		/*List<Employee> employeeList=employeeService.findEmployee(
			
				"%" + (employeeForm.getEmployeeName() == null || employeeForm.getEmployeeName().equals("") ? ""
						: employeeForm.getEmployeeName().trim()) + "%",
				
				"%" + (employeeForm.getEmployeeDescription() == null || employeeForm.getEmployeeDescription().equals("") ? ""
						: employeeForm.getEmployeeDescription().trim()) + "%"
				
				);*/
		List<Employee> employeeList=employeeService.findActiveEmployee();
		model.addAttribute("employeeList",employeeList);
		return mv;
	}
	
	
	//------
	@RequestMapping(value = { "/createemployee" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView createEmployee(Model model,
			@ModelAttribute("employeeForm") EmployeeForm employeeForm, Principal principal) {

	
		ModelAndView mv = new ModelAndView("Employee");
	
		try {
			if(employeeForm.getMethodType()!=null && employeeForm.getMethodType().equalsIgnoreCase("add")) {
			
				Employee employee= new Employee();
				employee.setEmployeeName(employeeForm.getEmployeeName());
				employee.setEmployeeDescription(employeeForm.getEmployeeDescription());
				
				employee.setActiveStatus("Y");
				employeeService.saveEmployee(employee);
				
				employeeForm.setSuccessMessage("Employee Created Successfully");
				//setting id after save
				employeeForm.setEmployeeId(employee.getEmployeeId().toString());
				
				//search
				/*List<Employee> employeeList=employeeService.findEmployee(
						
						"%" + (employeeForm.getEmployeeName() == null || employeeForm.getEmployeeName().equals("") ? ""
								: employeeForm.getEmployeeName().trim()) + "%",
						
						"%" + (employeeForm.getEmployeeDescription() == null || employeeForm.getEmployeeDescription().equals("") ? ""
								: employeeForm.getEmployeeDescription().trim()) + "%"						
						);
				*/
				List<Employee> employeeList=employeeService.findActiveEmployee();
				model.addAttribute("employeeList",employeeList);
				
			}
			
			
			//UPDATE
			if(employeeForm.getMethodType()!=null && employeeForm.getMethodType().equalsIgnoreCase("update") )
			{
				Employee employee = null;
				Optional<Employee> employeeOptional = employeeService.findEmployeeId(new Integer(employeeForm.getEmployeeId()));
				
				if (employeeOptional.isPresent()) {
					employee=employeeOptional.get();
					employee.setEmployeeName(employeeForm.getEmployeeName());
					employee.setEmployeeDescription(employeeForm.getEmployeeDescription());
					employeeService.saveEmployee(employee);
					
					
					//search
					/*List<Employee> employeeList=employeeService.findEmployee(
							
							"%" + (employeeForm.getEmployeeName() == null || employeeForm.getEmployeeName().equals("") ? ""
									: employeeForm.getEmployeeName().trim()) + "%",
							
							"%" + (employeeForm.getEmployeeDescription() == null || employeeForm.getEmployeeDescription().equals("") ? ""
									: employeeForm.getEmployeeDescription().trim()) + "%"
							);*/
					List<Employee> employeeList=employeeService.findActiveEmployee();
					model.addAttribute("employeeList",employeeList);
					employeeForm.setSuccessMessage("Update sucessfully !.");
					employeeForm.setMethodType("update");
				}
			} 
		return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
}
