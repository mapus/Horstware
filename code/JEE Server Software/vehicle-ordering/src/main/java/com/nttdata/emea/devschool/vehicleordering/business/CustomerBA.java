package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CustomerBA
{
	@EJB private CustomerDAO customerDAO;
	
	public Customer getOrCreateCustomer(String firstName, String lastName)
	{
		Customer resultingCustomer;
		List<Customer> customers = customerDAO.findByName(firstName, lastName);
		if(customers.isEmpty())
		{
			resultingCustomer = new Customer(firstName, lastName);
			customerDAO.persist(resultingCustomer);
		}
		else
		{
			resultingCustomer = customers.get(0);
		}
		return resultingCustomer;
	}
}
