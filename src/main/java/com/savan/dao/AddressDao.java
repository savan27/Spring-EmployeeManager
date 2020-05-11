package com.savan.dao;

import com.savan.genericDao.GenericDao;
import com.savan.model.Address;

/**
 * @author SAVAN
 *
 */
public interface AddressDao extends GenericDao<Address> {

	//remove address 
	void removeAddress(Address address);

}
