package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;

@Stateful
public class OrderTrackerBA {
	
	private Set<Order> orders = new HashSet<>();
	
	public void addOrder(Order order){
		if(equalOrderExists(order)){
			throw new IllegalStateException("Order already exists in session");
		}
		
		orders.add(order);
	}
	
	public int getNumberOfOrderedCars(){
		int numberOfOrderdCars = 0;
		
		for(final Order o : orders){
			numberOfOrderdCars += o.getAmount();
		}
		
		return numberOfOrderdCars;
	}
	
	public boolean equalOrderExists(Order order){
		return orders.contains(order);
	}
	
}
