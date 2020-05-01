package com.savan.dao;

import com.savan.model.Role;

/**
 * @author SAVAN
 *
 */
public interface RoleDao {
	
	//check number of rows
	int countRows();

	//insert role again
	boolean insert(Role role);


}
