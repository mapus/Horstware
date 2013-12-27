package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class OrderDAO extends AbstractDAO<Order> {

	@SuppressWarnings("unchecked")
	public List<Order> findByCustomer(String firstName, String lastName) {
		String queryExpression = "SELECT o FROM Order AS o";

		boolean firstNameEmpty = isEmpty(firstName);
		boolean lastNameEmtpy = isEmpty(lastName);

		if (!firstNameEmpty || !lastNameEmtpy) {
			queryExpression += " WHERE";
			
			boolean firstCondition = true;

			if (!firstNameEmpty) {
				queryExpression += " o.customer.firstName = :firstName";
				firstCondition = false;
			}

			if (!lastNameEmtpy) {
				if(!firstCondition){
					queryExpression += " AND";
				}
				
				queryExpression += " o.customer.lastName = :lastName";
			}
		}

		Query q = createQuery(queryExpression);

		if (!firstNameEmpty) {
			q.setParameter("firstName", firstName);
		}

		if (!lastNameEmtpy) {
			q.setParameter("lastName", lastName);
		}

		return q.getResultList();
	}

	private boolean isEmpty(String s) {
		return s.trim().isEmpty();
	}

}
