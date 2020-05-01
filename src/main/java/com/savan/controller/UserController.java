package com.savan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.savan.model.Employee;
import com.savan.service.EmployeeService;

/**
 * @author SAVAN
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/registerUser")
	public String registerUser() {
		return "redirect:/profile";
	}
	
	@GetMapping("/profile")
	public String profile(Model model) {
		List<Employee> list = empService.getAllEmployee();
		model.addAttribute("emplist", list);
		return "profile";
	}

}
