package com.savan.genericService;


/**
 * @author SAVAN
 *
 */
public interface GenericService<T> {
	
	//Insert Employee
	public void saveEmployee(T entity);

}
