package com.savan.service;

import com.savan.model.Role;

/**
 * @author SAVAN
 *
 */
public interface RoleService {
	
	//check number of rows
	int countRows();

	//insert role again
	boolean insert();

	//find Role by name
	Role findByRole(String roleName); 
}
