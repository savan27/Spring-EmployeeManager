package com.savan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savan.dao.AddressDao;
import com.savan.model.Address;
import com.savan.service.AddressService;

/**
 * @author SAVAN
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public boolean save(Address entity) {
		return false;
	}
	
	@Override
	public Address getById(Integer id) {
		return addressDao.getById(id);
	}

	@Override
	public void removeAddress(Address address) {
		addressDao.removeAddress(address);
	}
}
