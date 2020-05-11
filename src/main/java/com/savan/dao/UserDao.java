package com.savan.dao;

import com.savan.genericDao.GenericDao;
import com.savan.model.Address;
import com.savan.model.User;

/**
 * @author SAVAN
 *
 */
public interface UserDao extends GenericDao<User> {

	//get user from login credential 
	public User getUser(String userName, String password);

	//Update User
	public boolean updateUser(User u);

}
