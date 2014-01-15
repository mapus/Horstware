package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class OrderDAO extends AbstractDAO<Order>
{
	public Order findById (Long id)
	{
		String jpql =
			"SELECT o " + 
			"FROM Order " +
			"AS o " + 
			"WHERE o.id=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);
		
		@SuppressWarnings("unchecked")
		List<Order> result = query.getResultList();
		if(!result.isEmpty())
		{
			return result.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public List<Order> findByCustomerOrModel (String firstName, String lastName, Model model)
	{
		boolean firstNameEmpty = (firstName == null) || (firstName.length() == 0);
		boolean lastNameEmpty = (lastName == null) || (lastName.length() == 0);
		boolean modelIsNull = (model == null);
		

		String jpql = "SELECT o FROM Order AS o";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Order> result = query.getResultList();
		
		if(!modelIsNull)
		{
			result = filterByModel(result, model);
		}
		
		if(!firstNameEmpty)
		{
			result = filterByFirstName(result, firstName);
		}
		
		if(!lastNameEmpty)
		{
			result = filterByLastName(result, lastName);
		}
		
		return result;
	}
	
	private List<Order> filterByModel (List<Order> orders, Model model)
	{
		List<Order> filteredOrders =  new ArrayList<Order>();
		for(Order o : orders)
		{
			if(o.getModel().equals(model))
			{
				filteredOrders.add(o);
			}
		}
		return filteredOrders;
	}
	
	private List<Order> filterByFirstName (List<Order> orders, String firstName)
	{
		List<Order> filteredOrders =  new ArrayList<Order>();
		for(Order o : orders)
		{
			if(o.getCustomer().getFirstName().contains(firstName))
			{
				filteredOrders.add(o);
			}
			
		}
		return filteredOrders;
	}
	
	private List<Order> filterByLastName (List<Order> orders, String lastName)
	{
		List<Order> filteredOrders =  new ArrayList<Order>();
		for(Order o : orders)
		{
			if(o.getCustomer().getLastName().contains(lastName))
			{
				filteredOrders.add(o);
			}
			
		}
		return filteredOrders;
	}
}