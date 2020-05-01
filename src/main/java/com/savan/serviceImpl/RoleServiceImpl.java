package com.savan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.dao.RoleDao;
import com.savan.model.Role;
import com.savan.service.RoleService;

/**
 * @author SAVAN
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roalDao;

	@Override
	public int countRows() {
		return roalDao.countRows();
	}

	@Override
	public boolean insert() {
		
		//set admin role
		Role admin = new Role();
		admin.setRole("admin");
		
		// set user role
		Role user = new Role();
		user.setRole("user");
		
		if(roalDao.insert(admin) && roalDao.insert(user)) {
			return true;
		}
		return false;
	}

}
