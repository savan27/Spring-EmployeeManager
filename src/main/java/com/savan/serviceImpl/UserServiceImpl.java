/**
 * 
 */
package com.savan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.controller.UserController;
import com.savan.dao.UserDao;
import com.savan.daoImpl.UserDaoImpl;
import com.savan.enume.RoleType;
import com.savan.model.User;
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
	private RoleService roleService;
	
	@Override
	public boolean save(User entity) {
		System.out.println("inside service method");
		/* System.out.println(entity.getAddress().toString()); */
		entity.setRole(roleService.findByRole(RoleType.USER.name()));
		System.out.println("after roal added");
		/* System.out.println(entity.getAddress().toString()); */
		return userDao.save(entity);
	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	public User getUser(String userName, String password) {
		return userDao.getUser(userName,password);
	}

}
