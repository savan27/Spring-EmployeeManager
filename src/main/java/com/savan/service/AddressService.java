package com.savan.service;

import com.savan.genericService.GenericService;
import com.savan.model.Address;

/**
 * @author SAVAN
 *
 */
public interface AddressService extends GenericService<Address> {

	//remove address  
	void removeAddress(Address address);

}
