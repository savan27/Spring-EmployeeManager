package com.savan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.savan.dto.AddressDto;
import com.savan.model.Address;
import com.savan.model.User;
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

	@PostMapping("/registerUser")
	public String registerUser(User u,@ModelAttribute("address") AddressDto address ,@RequestParam("File") MultipartFile File) {

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
			System.out.println(addr.toString());

			// set user to address
			addr.setUser(u);

			// add address to user
			u.getAddress().add(addr);
		});
		
		//save user 
		if (userService.save(u)) {
			System.out.println("User Insertion SuccessFull");
		}

		return "login";
	}

	@PostMapping("/afterLogin")
	public String profile(@RequestAttribute("user") User u, Model m, Address add) {
		u.getAddress().forEach(System.out::println);

		// Converting Image to Base64 String
		String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());

		m.addAttribute("user", u);
		m.addAttribute("profilePicture", base64Image);
		return "profile";
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("id") int id, Model m) {

		// get the user data
		 User u = userService.getById(id);

		// Converting Image to Base64 String
		String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());

		m.addAttribute("user", u);
		m.addAttribute("profilePicture", base64Image);
		return "register";
	}

}
