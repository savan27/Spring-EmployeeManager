package com.savan.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.savan.dao.RoleDao;
import com.savan.model.Role;

/**
 * @author SAVAN
 *
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// get the current session
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// method to count number of rows
	@Override
	public int countRows() {
		try {
			String HQL = "select count(id) from Role";
			Query q = getSession().createQuery(HQL);
			Long count =  (Long) q.uniqueResult();
			
			return (count).intValue();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//method to insert role 
	@Override
	public boolean insert(Role role) {
		Integer ans = (Integer) getSession().save(role);
		
		if (ans > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Role findByRole(String roleName) {
		
		String HQL = " from Role r where r.role =:rolee";
		Query<Role> q = getSession().createQuery(HQL);
		q.setParameter("rolee", roleName);
			
		return q.uniqueResult();
	}

}
