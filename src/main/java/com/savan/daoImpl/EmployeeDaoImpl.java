package com.savan.daoImpl;

import org.springframework.stereotype.Repository;

import com.savan.dao.EmployeeDao;
import com.savan.genericDao.GenericDao;
import com.savan.initializer.SessionFactoryInitializer;

/**
 * @author SAVAN
 *
 */
@Repository
public class EmployeeDaoImpl extends SessionFactoryInitializer implements EmployeeDao,GenericDao<EmployeeDaoImpl> {

	
	@Override
	public void save(EmployeeDaoImpl entity) {
		System.out.println("Inside the EmployeeDaoImpl");
		getSession().persist(entity);
	}

}
