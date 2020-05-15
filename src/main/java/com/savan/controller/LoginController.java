package com.savan.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.savan.service.RoleService;
import com.savan.service.UserService;

/**
 * @author SAVAN
 *
 */
@Controller
public class LoginController {
	
	//logger initialization 
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private RoleService rolesrvice;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		if (rolesrvice.countRows() != 2) {
			if(rolesrvice.insert()) {
				logger.info("Role Updated Successfully...");
			}
		}
	}
	
	@RequestMapping("/")
	public String welcomeFile() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		
		// getting user role
		String userRole = (String) session.getAttribute("userRole");

		// validate session
		if (userRole != null && userRole.contentEquals("USER")) {
			return "redirect:/userprofile";
		}
		else if(userRole != null && userRole.contentEquals("ADMIN")){
			return "redirect:/adminprofile";
		}
		
		return "login";
	}
	
	@PostMapping("/afterLogin")
	public String afterLogin(@RequestAttribute("userId") Integer id,@RequestAttribute("userRole")String role,HttpSession session) {
		
		session.setAttribute("userId", id);
		
		if (role.contentEquals("ADMIN")) {
			session.setAttribute("userRole", "ADMIN");
			return "redirect:/adminprofile";
		}
		else {
			session.setAttribute("userRole", "USER");
			return "redirect:/userprofile";
		}
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session,Model m) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/forgotPassword")
	public String ForgotPassword() {
		return "forgotPassword";
	}
	
	@PostMapping("findPassword")
	@ResponseBody
	public String findPassword(@RequestParam("userEmail")String email) {
		
		//password fetched from the database
		String password = userService.findPassword(email);
		
		if(password != null) {
			return "Your Password Is : "+password+"";
		}
		else {
			return "Please Enter Valide Enail Id..!!";
		}
	}
}
