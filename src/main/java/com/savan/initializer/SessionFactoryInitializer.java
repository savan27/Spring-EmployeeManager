package com.savan.initializer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SAVAN
 *
 */
public abstract class SessionFactoryInitializer {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/*
	 * //persist the entity public void persist(Object entity) {
	 * getSession().persist(entity); }
	 */

}
