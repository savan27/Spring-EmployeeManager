package com.savan.genericDao;

/**
 * @author SAVAN
 *
 */
public interface GenericDao<T> {
	
	//Insert Employee
	public void saveEmployee(T entity);

}
