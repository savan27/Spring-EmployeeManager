package com.savan.genericService;


/**
 * @author SAVAN
 *
 */
public interface GenericService<T> {
	
	//Insert Employee
	public boolean save(T entity);
	
	//Get entity Object by id
	public T getById(Integer id);

}
