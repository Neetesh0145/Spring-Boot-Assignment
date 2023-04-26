package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.HomepageForm;



@Controller
public class HomepageController {

	@RequestMapping(value = { "/homepage" }, method = { RequestMethod.GET})
	public ModelAndView viewEmployee(Model model,
			@ModelAttribute("homepageForm") HomepageForm homepageForm, Principal principal) {
	
	
		ModelAndView mv = new ModelAndView("Homepage");
		try {
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
}
