package com.savan.dao;

import java.util.List;

import com.savan.genericDao.GenericDao;
import com.savan.model.Employee;

/**
 * @author SAVAN
 *
 */
public interface EmployeeDao extends GenericDao<Employee>{

	// get all Employee List
	List<Employee> getAllEmployee();
	
	

}
