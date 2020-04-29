package com.savan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.savan.model.Employee;
import com.savan.service.EmployeeService;

/**
 * @author SAVAN
 * @param <T>
 *
 */
@Controller
@ComponentScan(basePackages = "com.savan.service")
public class HomeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/home")
	public String homeInit(Model model) {
		return "home";
	}
	
	@PostMapping("/register")
	public String register(Employee emp) {
		System.out.println("inside resgisr");
		
		empService.saveEmployee(emp);
		return "redirect:/home";
	}

}
