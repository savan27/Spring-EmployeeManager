package com.savan.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.savan.model.User;
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
	
	@PostMapping("/afterLogin")
	public String afterLogin(@RequestAttribute("user") Integer id,@RequestAttribute("userRole")String role,HttpSession session) {
		
		session.setAttribute("userId", id);
		
		if (role.contentEquals("ADMIN")) {
			return "redirect:/adminLogin";
		}
		else {
			return "redirect:/userLogin";
		}
	}
	
	@GetMapping("/forgotPassword")
	public String ForgotPassword() {
		return "forgotPassword";
	}
	
}
