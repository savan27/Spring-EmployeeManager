package com.savan.genericDao;

/**
 * @author SAVAN
 *
 */
public interface GenericDao<T> {
	
	//Insert Employee
	public boolean save(T entity);

	//Get entity Object by id
	public T getById(Integer id);
}
