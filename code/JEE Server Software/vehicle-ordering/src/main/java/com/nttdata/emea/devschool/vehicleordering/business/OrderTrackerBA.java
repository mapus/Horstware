package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateful;

@Stateful
public class OrderTrackerBA
{
	private Collection<Order> orders = new ArrayList<Order>();
	
	public void addOrder (Order order)
	{
		orders.add(order);
	}
	
	public int getNumberOfOrderedCars ()
	{
		int result = 0;
		for(Order o : orders)
		{
			result += o.getAmount();
		}
		return result;
	}
	
	public boolean equalOrderExists (Order order)
	{
		for(Order o : orders)
		{
			if(o.equals(order))
			{
				return true;
			}
		}
		return false;
	}
}
