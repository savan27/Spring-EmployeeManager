package com.savan.genericServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.genericDao.GenericDao;
import com.savan.genericService.GenericService;

/**
 * @author SAVAN
 * @param <T>
 *
 */
@Service
public class GenericServiceImpl<T> implements GenericService<T> {

	@Autowired
	private GenericDao<T> dao;
	
	@Override
	public void saveEmployee(T entity) {
		System.out.println("inside generic Service impl");
		dao.save(entity);
	}

}
