/**
 * 
 */
package com.savan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.dao.UserDao;
import com.savan.enume.RoleType;
import com.savan.model.Address;
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
	public List<Integer> getAllAddresses(int userId) {
		
		User u = userDao.getById(userId);
		
		List<Integer> existingAddressIds = new ArrayList<Integer>();
		
		u.getAddress().stream().forEach(addId ->{
			existingAddressIds.add(addId.getId());
		});
		
		return existingAddressIds;
	}

	@Override
	public boolean updateUser(User u) {
		return userDao.updateUser(u);
	}

}
