package com.savan.service;

import java.util.List;

import com.savan.dto.AddressDto;
import com.savan.dto.UserDto;
import com.savan.genericService.GenericService;
import com.savan.model.Address;
import com.savan.model.User;

/**
 * @author SAVAN
 *
 */
public interface UserService extends GenericService<User> {

	//get user from login credential 
	public User getUser(String userName,String password);

	//get all address list
	public List<Address> getAllAddresses(int userId);

	//update user
	public boolean updateUser(User u, UserDto userdto, AddressDto addressdto);

	// fetch all user Data
	public List<User> getAllUser();

	//remove user 
	public boolean removeUser(User user);
	
}
