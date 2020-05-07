package com.savan.service;

import com.savan.genericService.GenericService;
import com.savan.model.User;

/**
 * @author SAVAN
 *
 */
public interface UserService extends GenericService<User> {

	//get user from login credential 
	public User getUser(String userName,String password);
	
}
