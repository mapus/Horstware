package com.nttdata.emea.devschool.vehicleordering.xml.impl;

import java.util.List;

import org.simpleframework.xml.ElementList;

import com.nttdata.emea.devschool.vehicleordering.entities.Customer;

public class XMLCustomerResponse {
	
	@ElementList(name="customer",inline=true)
	List<Customer> customerList;
	
	
	public List<Customer> getCustomerList()
	{
		return this.customerList;
	}
}
