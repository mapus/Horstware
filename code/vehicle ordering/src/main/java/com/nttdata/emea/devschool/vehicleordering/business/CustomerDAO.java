package com.nttdata.emea.devschool.vehicleordering.business;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
public class CustomerDAO extends AbstractDAO<Customer> {

	public Customer findByName(String firstName, String lastName) {
		Query q = createQuery("SELECT c FROM Customer AS c WHERE c.firstName = :firstName AND c.lastName = :lastName");

		q.setParameter("firstName", firstName);
		q.setParameter("lastName", lastName);

		try {
			return (Customer) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
