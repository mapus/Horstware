package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class OrderBS {
	
	@EJB
	private CustomerBA customerBA;
	
	@EJB
	private OrderBA orderBA;
	
	public Order createOrder(String firstName, String lastName, int amount, Date deliveryDate){
		Customer customer = customerBA.getOrCreateCustomer(firstName, lastName);
		
		Order order = new Order();
		
		order.setCustomer(customer);
		order.setAmount(amount);
		order.setDeliveryDate(deliveryDate);
		
		orderBA.createOrder(order);
		
		return order;
	}
	
	public List<Order> findOrders(String customerFirstName, String customerLastName){
		return orderBA.findOrders(customerFirstName, customerLastName);
	}

}
