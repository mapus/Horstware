package com.nttdata.emea.devschool.vehicleordering.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CustomerBA {

	@EJB
	private CustomerDAO customerDAO;

	public Customer getOrCreateCustomer(String firstName, String lastName) {
		Customer existingCustomer = customerDAO.findByName(firstName, lastName);
		if (existingCustomer != null) {
			return existingCustomer;
		}

		Customer newCustomer = createCustomer(firstName, lastName);

		return newCustomer;
	}

	private Customer createCustomer(String firstName, String lastName) {
		Customer c = new Customer();

		c.setFirstName(firstName);
		c.setLastName(lastName);

		customerDAO.persist(c);

		return c;
	}

}
