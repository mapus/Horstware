package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class OrderBA
{
	private final static int ORDER_LIMIT = 10;

	@EJB private OrderDAO orderDAO;
	@EJB private OrderTrackerBA orderTrackerBA;
	
	public void createOrder (Order order)
	{
		boolean orderLimitExceeded = ((orderTrackerBA.getNumberOfOrderedCars() + order.getAmount()) > ORDER_LIMIT);
		boolean orderDuplicated = orderTrackerBA.equalOrderExists(order);
		
		if(!orderLimitExceeded && !orderDuplicated)
		{
			orderDAO.persist(order);
			orderTrackerBA.addOrder(order);
		}
		else
		{
			throw new RuntimeException("failed to create order");
		}		
	}
	
	public List<Order> findOrders (String firstName, String lastName)
	{
		return orderDAO.findByCustomerOrModel(firstName, lastName, null);
	}
}