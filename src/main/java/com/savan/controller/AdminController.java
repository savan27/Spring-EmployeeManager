package com.savan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.savan.model.User;
import com.savan.service.UserService;

/**
 * @author SAVAN
 *
 */
@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/adminLogin")
	public String adminLogin(HttpSession session,Model m) {
		
		//setting userRole to session
		session.setAttribute("userRole", "ADMIN");
		
		// user data to display
		List<User> userList = userService.getAllUser();

		m.addAttribute("userDetail", userList);

		return "adminProfile";
	}
	
	@PostMapping("removeUser")
	@ResponseBody
	public String removeUser(@RequestParam("userId") int userId) {
		
		//get user to remove 
		User u = userService.getById(userId);
		
		//remove user
		boolean isUserremoved = userService.removeUser(u);
		
		if (isUserremoved) {
			return "user Deletion successfll ... ";
		}
		
		return "Fail to Delelte User.........";
	}
}
