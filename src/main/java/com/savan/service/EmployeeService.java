package com.savan.service;

import java.util.List;

import com.savan.genericService.GenericService;
import com.savan.model.Employee;

/**
 * @author SAVAN
 *
 */
public interface EmployeeService extends GenericService<Employee> {

	// get all Employee List
	List<Employee> getAllEmployee();
	

}
