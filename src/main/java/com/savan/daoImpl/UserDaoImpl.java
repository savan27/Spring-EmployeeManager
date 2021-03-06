package com.savan.daoImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.savan.dao.UserDao;
import com.savan.model.User;

/**
 * @author SAVAN
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	//logger initialization 
	private Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	// get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean save(User entity) {
		Integer ans = (Integer) getSession().save(entity);

		if (ans > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getById(Integer id) {
		return getSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String userName, String password) {

		String HQL = " from User u where u.email =:email and u.password = :password";
		Query<User> q = getSession().createQuery(HQL);
		q.setParameter("email", userName);
		q.setParameter("password", password);
		return q.uniqueResult();
	}

	@Override
	public boolean updateUser(User u) {
		try {
			getSession().saveOrUpdate(u);
			return true;
		} catch (Exception e) {
			System.out.println("User Update Fail...");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		return getSession().createQuery("from User").list();
	}

	@Override
	public boolean removeUser(User user) {
		
		try {
			getSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in address deletion : "+e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) {
		
		String HQL = " from User u where u.email =:email";
		Query<User> q = getSession().createQuery(HQL);
		q.setParameter("email", email);
		
		return  q.uniqueResult();
	}
}
