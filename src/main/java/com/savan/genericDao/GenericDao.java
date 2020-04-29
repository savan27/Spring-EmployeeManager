package com.savan.genericDao;

/**
 * @author SAVAN
 *
 */
public interface GenericDao<T> {
	
	/**
	* 
	* @param entity: entity to save
	* @return Identifier of saved entity
	*/
	public void save(T entity);

}
