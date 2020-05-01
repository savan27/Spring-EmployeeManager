package com.savan.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.savan.service.RoleService;

/**
 * @author SAVAN
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private RoleService rolesrvice;
	
	@PostConstruct
	public void init() {
		if (rolesrvice.countRows() != 2) {
			if(rolesrvice.insert()) {
				System.out.println("Role Updated Successfully...");
			}
		}
	}
	
	@RequestMapping("/")
	public String welcomeFile() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/forgotPassword")
	public String ForgotPassword() {
		return "forgotPassword";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
}
