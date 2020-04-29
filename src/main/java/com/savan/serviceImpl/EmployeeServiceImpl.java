package com.savan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savan.dao.EmployeeDao;
import com.savan.model.Employee;
import com.savan.service.EmployeeService;

/**
 * @author SAVAN
 *
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public void saveEmployee(Employee entity) {
		dao.saveEmployee(entity);
	}



}
