package com.savan.dto;

import java.util.ArrayList;
import java.util.List;

import com.savan.model.Address;

/**
 * @author SAVAN
 *
 */
public class AddressDto {
	
	private List<Address> address;

	/**
	 * @return the address
	 */
	public List<Address> getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(List<Address> address) {
		System.out.println("inside address dto setter");
		this.address = address;
	}

	/**
	 * @param address
	 */
	public AddressDto(List<Address> address) {
		this.address = address;
	}

	/**
	 * 
	 */
	public AddressDto() {
		this.address = new ArrayList<Address>();
	}

}
