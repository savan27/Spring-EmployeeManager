package com.savan.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.savan.dao.EmployeeDao;
import com.savan.model.Employee;

/**
 * @author SAVAN
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	//get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Save user data to data base
	@Override
	public void saveEmployee(Employee entity) {
		System.out.println("inside the EmployeeDaoImpl");
		getSession().persist(entity);
	}

	

}
