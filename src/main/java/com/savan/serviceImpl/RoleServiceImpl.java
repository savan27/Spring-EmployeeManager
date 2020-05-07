package com.savan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.dao.RoleDao;
import com.savan.enume.RoleType;
import com.savan.model.Role;
import com.savan.service.RoleService;

/**
 * @author SAVAN
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public int countRows() {
		return roleDao.countRows();
	}

	@Override
	public boolean insert() {
		
		//set admin role
		Role admin = new Role();
		admin.setRole(RoleType.ADMIN.name());
		
		// set user role
		Role user = new Role();
		user.setRole(RoleType.USER.name());
		
		if(roleDao.insert(admin) && roleDao.insert(user)) {
			return true;
		}
		return false;
	}

	@Override
	public Role findByRole(String roleName) {
		return roleDao.findByRole(roleName);
	}

}
