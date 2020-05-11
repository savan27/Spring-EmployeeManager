package com.savan.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.savan.dao.AddressDao;
import com.savan.model.Address;

/**
 * @author SAVAN
 *
 */
@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	// get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean save(Address entity) {
		return false;
	}

	@Override
	public Address getById(Integer id) {
		return getSession().get(Address.class, id);
	}

	@Override
	public void removeAddress(Address address) {
		getSession().delete(address);
	}

}
