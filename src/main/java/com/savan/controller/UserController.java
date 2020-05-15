package com.savan.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.savan.dto.AddressDto;
import com.savan.dto.UserDto;
import com.savan.model.User;
import com.savan.service.UserService;



/**
 * @author SAVAN
 *
 */
@Controller
public class UserController {
	
	//logger initialization 
	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/userprofile")
	public String profile(HttpSession session, Model m) {
		
		//getting user roleuserLogin
		String userRole = (String) session.getAttribute("userRole");
		
		//validate session
		if (userRole != null && userRole.contentEquals("USER")) {
			
			//getting user id from user
			int userId = (int) session.getAttribute("userId");
			
			//getting user data from the database
			User u = userService.getById(userId);
			
			// Converting Image to Base64 String
			if (u.getProfilePicture() != null) {
				
				//image byte[] to Base64String 
				String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());
				
				m.addAttribute("profilePicture", base64Image);
			}
			
			// add user object to model
			m.addAttribute("user", u);
			
			return "userProfile";
		}
		else {
			return "redirect:/login";
		}
	}

	@PostMapping("/registerUser")
	public String registerUser(@Valid User u,BindingResult result,AddressDto address,@RequestParam("File") MultipartFile File,Model m) {
		
		//validating user data 
		if (result.hasErrors()) {
			m.addAttribute("error", result);
			return "register";
		}
		
		// setting image as byte[]
		try {
			if (File != null && File.getSize() > 0) {
				byte[] image = File.getBytes();
				u.setProfilePicture(image);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// address manipulation as per mapping
		address.getAddress().stream().forEach(addr -> {
			// set user to address
			addr.setUser(u);
		});
		
		//setting updated address to user
		u.setAddress(address.getAddress());
		
		//save user 
		if (userService.save(u)) {
			logger.info("User Insertion SuccessFull...");
		}

		return "login";
	}

	@GetMapping("/doUpdateUser")
	public String doupdateUser(@RequestParam("id") int id, Model m) {
		
		// get the user data
		 User u = userService.getById(id);
		 
		// Converting Image to Base64 String
		if (u.getProfilePicture() != null) { 
			String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());
			m.addAttribute("profilePicture", base64Image);
		}
		else {
			logger.info("User with id "+ u.getId()+ " has no image available at update time...");
		}
		
		m.addAttribute("user", u);
		m.addAttribute("userUpdate", "updateUser");
		return "register";
	}
	
	@PostMapping("/afterUserUpdate")
	public String afterUserUpdate(@Valid User u,BindingResult result,UserDto userdto,AddressDto addressdto,Model m) {
		
		// validating user data
		if (result.hasErrors()) {
			m.addAttribute("error", result);
			return "register";
		}
		
		//update user
		if (userService.updateUser(u,userdto,addressdto)) {
			logger.info("User is Updated Successfully...");
		}
		else {
			logger.error("Fail to Update User");
		}
		
		if (userdto.getUserRole().contentEquals("ADMIN")) {
			return "redirect:/adminprofile";
		}

		return "redirect:/userprofile";
	}
	
	@PostMapping("/checkUserExistance")
	@ResponseBody
	public String checkUserExistance(@RequestParam("userEmail")String email,@RequestParam("userId")int userId) {
		
		boolean isUserExists = userService.checkUser(userId,email);
		
		if (isUserExists) {
			return "";
		}
		else {
			return "Email Already Exists...";
		}
	}
}
