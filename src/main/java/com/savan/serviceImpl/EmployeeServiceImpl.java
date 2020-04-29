package com.savan.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savan.model.Employee;
import com.savan.service.EmployeeService;

/**
 * @author SAVAN
 *
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public void saveEmployee(Employee entity) {
		System.out.println("Inside EmployeServiceImpl");
	}



}
