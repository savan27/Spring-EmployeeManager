package com.savan.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.savan.dto.AddressDto;
import com.savan.dto.UserDto;
import com.savan.model.Address;
import com.savan.model.User;
import com.savan.service.RoleService;
import com.savan.service.UserService;



/**
 * @author SAVAN
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/userLogin")
	public String profile(HttpSession session, Model m) {
		
		int userId = (int) session.getAttribute("userId");
		
		//remove userId from session
		//session.removeAttribute("userId");
		
		//adding userRole to session
		session.setAttribute("userRole", "USERR");
		
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

	@PostMapping("/registerUser")
	public String registerUser(User u,AddressDto address ,@RequestParam("File") MultipartFile File) {

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
			System.out.println("User Insertion SuccessFull");
		}

		return "login";
	}

	@GetMapping("/doUpdateUser")
	public String doupdateUser(@RequestParam("id") int id, Model m) {

		// get the user data
		 User u = userService.getById(id);

		// Converting Image to Base64 String
		String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());

		m.addAttribute("user", u);
		m.addAttribute("profilePicture", base64Image);
		return "register";
	}
	
	@PostMapping("/afterUserUpdate")
	public String afterUserUpdate(User u,UserDto userdto,AddressDto addressdto) {
		
		System.out.println(userdto.getUserRole());
		
		//update user
		if (userService.updateUser(u,userdto,addressdto)) {
			System.out.println("User is Updated Successfully...");
		}
		
		if (userdto.getUserRole().contentEquals("Admin")) {
			return "redirect:/adminLogin";
		}

		return "doUpdateUser?id=27";
	}
}
