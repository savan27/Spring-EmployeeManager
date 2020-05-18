/**
 * 
 */
package com.savan.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.savan.dao.UserDao;
import com.savan.dto.AddressDto;
import com.savan.dto.UserDto;
import com.savan.enume.RoleType;
import com.savan.model.Address;
import com.savan.model.User;
import com.savan.service.AddressService;
import com.savan.service.RoleService;
import com.savan.service.UserService;

/**
 * @author SAVAN
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public boolean save(User entity) {
		entity.setRole(roleService.findByRole(RoleType.USER.name()));
		return userDao.save(entity);
	}

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}
	
	@Override
	public User getUser(String userName, String password) {
		return userDao.getUser(userName,password);
	}

	@Override
	public List<Address> getAllAddresses(int userId) {
		
		//user all info.
		User u = userDao.getById(userId);
		
		//list of all available addresses
		List<Address> existingAddressIds = new ArrayList<Address>();
		
		u.getAddress().stream().forEach(addId ->{
			existingAddressIds.add(addId);
		});
		
		return existingAddressIds;
	}

	@Override
	public boolean updateUser(User u, UserDto userdto, AddressDto addressdto) {
		
		// Manipulation of profile picture
		try {
			MultipartFile file = userdto.getFile();
			if (file != null && file.getSize() > 0) {
				u.setProfilePicture(file.getBytes());
			} else {
				byte[] Image = Base64.getDecoder().decode(userdto.getProfile());
				u.setProfilePicture(Image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// setting user id
		u.setId(userdto.getUserId());

		// setting user Role
		u.setRole(roleService.findByRole(userdto.getUserRole()));
		
		//get all existing addresses of user
		List<Address> addressList = getAllAddresses(userdto.getUserId());
		
		// list of existing address id's
		List<Integer> existingAddressIds = new ArrayList<Integer>();

		// add address id's to the existingAddressIds list
		addressList.stream().forEach(addr -> {
			existingAddressIds.add(addr.getId());
		});
		
		// Distinguish all addresses
		addressdto.getAddress().stream().forEach(addr -> {

			// set user to address
			addr.setUser(u);

			// find addresses to remove
			if (existingAddressIds.contains(addr.getId())) {
				existingAddressIds.remove(Integer.valueOf(addr.getId()));
			}
		});

		// setting updated address to user
		u.setAddress(addressdto.getAddress());

		// deleting the address from the entity
		addressList.stream().forEach(addr -> {
			if (existingAddressIds.contains(addr.getId())) {
				addressService.removeAddress(addr);
			}
		});
		
		return userDao.updateUser(u);
	}

	@Override
	public List<User> getAllUser() {
		
		//complete user information
		List<User> userlist = userDao.getAllUser();
		
		//Particular info. to display
		List<User> displayinfo = new ArrayList<User>();
		
		
		userlist.stream().forEach(user->{
			
			User u = new User();
			
			u.setId(user.getId());
			u.setFirstName(user.getFirstName());
			u.setEmail(user.getEmail());
			u.setGender(user.getGender());
			u.setContact(user.getContact());
			if (user.getProfilePicture() != null) {
				//image byte[] to Base64String 
				String base64Image = Base64.getEncoder().encodeToString(user.getProfilePicture());
				u.setBase64image(base64Image);
			}
			else {
				System.out.println("User with id "+ user.getId()+ " has no image available...");
			}
			displayinfo.add(u);
			 
		});
		
		return displayinfo;
	}

	@Override
	public boolean removeUser(User user) {
		return userDao.removeUser(user);
	}

	@Override
	public String findPassword(String email) {
		
		//get user from email
		User user = userDao.getUserByEmail(email);
		
		if (user != null ) {
			String password = user.getPassword();
			return password;
		}
		
		return null;
	}

	@Override
	public boolean checkUser(int userId, String email) {
		
		//get user from email
		User user = userDao.getUserByEmail(email);
		
		//check user updating 
		if(user != null) {
			
			if(user.getId() == userId) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
	}

}
