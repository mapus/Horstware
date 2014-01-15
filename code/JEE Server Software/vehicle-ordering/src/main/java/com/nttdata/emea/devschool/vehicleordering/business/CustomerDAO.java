package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class CustomerDAO extends AbstractDAO<Customer>
{
	public Customer findById (Long id)
	{
		String jpql =
			"SELECT c " + 
			"FROM Customer " +
			"AS c " + 
			"WHERE c.id=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);
		
		@SuppressWarnings("unchecked")
		List<Customer> result = query.getResultList();
		if(!result.isEmpty())
		{
			return result.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public List<Customer> findByName (String firstName, String lastName)
	{
		boolean firstNameEmpty = (firstName == null) || (firstName.isEmpty());
		boolean lastNameEmpty = (lastName == null) || (lastName.isEmpty());
		
		Query query;
		if(firstNameEmpty && lastNameEmpty)
		{
			String jpql = "SELECT c FROM Customer AS c";
			query = entityManager.createQuery(jpql);
			
		}
		else if(firstNameEmpty)
		{
			String jpql =
				"SELECT c " + 
				"FROM Customer " +
				"AS c " + 
				"WHERE c.lastName=?1";
			query = entityManager.createQuery(jpql);
			query.setParameter(1, lastName);
		}
		else if(lastNameEmpty)
		{
			String jpql =
				"SELECT c " + 
				"FROM Customer " +
				"AS c " + 
				"WHERE c.firstName=?1";
			query = entityManager.createQuery(jpql);
			query.setParameter(1, firstName);
			
		}
		else
		{
			String jpql =
				"SELECT c " + 
				"FROM Customer " +
				"AS c " + 
				"WHERE c.firstName=?1 AND c.lastName=?2";
			query = entityManager.createQuery(jpql);
			query.setParameter(1, firstName);
			query.setParameter(2, lastName);
			
		}
		
		@SuppressWarnings("unchecked")
		List<Customer> result = query.getResultList();
		return result;
	}
}