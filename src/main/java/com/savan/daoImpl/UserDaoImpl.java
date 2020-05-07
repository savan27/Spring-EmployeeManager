package com.savan.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.savan.dao.UserDao;
import com.savan.model.Role;
import com.savan.model.User;

/**
 * @author SAVAN
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean save(User entity) {
		System.out.println("inside dao method");
		/* System.out.println(entity.getAddress().toString()); */
		int ans = (int) getSession().save(entity);
		System.out.println("after query execution");
		if (ans > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getById(int id) {
		
		String HQL = " from User u where u.id =:id";
		Query<User> q = getSession().createQuery(HQL);
		q.setParameter("id", id);
		
		return q.uniqueResult();
	}

	@Override
	public User getUser(String userName, String password) {
		
		String HQL = " from User u where u.email =:email and u.password = :password";
		Query<User> q = getSession().createQuery(HQL);
		q.setParameter("email", userName);
		q.setParameter("password", password);
		return q.uniqueResult();
	}

}
