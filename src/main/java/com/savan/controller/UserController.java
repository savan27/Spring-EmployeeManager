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
import com.savan.dto.UserDto;
import com.savan.model.Address;
import com.savan.model.Role;
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
	
	@Autowired
	private RoleService roleService;
	
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

	@PostMapping("/afterLogin")
	public String profile(@RequestAttribute("user") Integer id, Model m, Address add) {
		
		//getting user data from the database
		User u = userService.getById(id);

		// Converting Image to Base64 String
		if (u.getProfilePicture() != null) {
			String base64Image = Base64.getEncoder().encodeToString(u.getProfilePicture());
			
			m.addAttribute("profilePicture", base64Image);
		}

		// add user object to model
		m.addAttribute("user", u);
		
		return "userProfile";
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
	public String afterUserUpdate(User u,UserDto udto,AddressDto address) {
		
		// Manipulation of profile picture
		try {
			MultipartFile file = udto.getFile();
			if (file != null && file.getSize() > 0) {
				byte[] fileImage = file.getBytes();
				u.setProfilePicture(fileImage);
			} else {
				byte[] Image = Base64.getDecoder().decode(udto.getProfile());
				u.setProfilePicture(Image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// set user id
		u.setId(udto.getUserId());
		
		//set user Role
		u.setRole(roleService.findByRole(udto.getUserRole()));
		
		//clear the address from user
		u.getAddress().clear();
		
		//get all existing address id's of user
		List<Integer> existingAddressIds = userService.getAllAddresses(udto.getUserId());
		
		//Distinguish all addresses 
		address.getAddress().stream().forEach(addr -> {
			
			// set user to address 
			addr.setUser(u);
			
			//find addresses to remove 
			if (existingAddressIds.contains(addr.getId())) {
				existingAddressIds.remove(Integer.valueOf(addr.getId()));
			}
		});
		
		//setting updated address to user
		u.setAddress(address.getAddress());

		existingAddressIds.stream().forEach(System.out::println);
		
		//update user
		if (userService.updateUser(u)) {
			System.out.println("User is Updated Successfully...");
		}

		return "doUpdateUser?id=27";
	}

}
