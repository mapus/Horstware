package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;

@ManagedBean(name = "createOrderBB")
@RequestScoped
public class CreateOrderBB {
	
	private static final Logger LOG = Logger.getLogger(CreateOrderBB.class.getName());
	
	private String firstName;
	
	private String lastName;
	
	private int amount;
	
	private Date deliveryDate;
	
	@ManagedProperty(value="#{searchOrdersBB}")
	private SearchOrdersBB searchOrdersBB;
	
	@EJB
	private OrderBS orderBS;
	
	public String createOrder(){
		orderBS.createOrder(firstName, lastName, amount, deliveryDate);
		
		LOG.info("Created order with firstName: " + firstName + ", lastName: "
				+ lastName + ", amount: " + amount + " and deliveryDate: " + deliveryDate);
		
		searchOrdersBB.setFirstName(firstName);
		searchOrdersBB.setLastName(lastName);
		
		searchOrdersBB.filterResults();
		
		return "searchOrders";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public SearchOrdersBB getSearchOrdersBB() {
		return searchOrdersBB;
	}
	
	public void setSearchOrdersBB(SearchOrdersBB searchOrdersBB) {
		this.searchOrdersBB = searchOrdersBB;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
