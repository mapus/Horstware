package com.nttdata.emea.devschool.vehicleordering.entities;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="order")
public class Order
{
	@Element
	private Customer customer;
	@Element
	private VehicleModel model;
	@Element(name="amount")
	private int quantity;
	@Element(name="deliveryDate")
	private String date;
	private Date deliveryDate;
	@Element
	private long id;
	
	public Order (long id, Customer customer, VehicleModel model, int quantity, Date deliveryDate)
	{
		this.id=id;
		this.customer = customer;
		this.model = model;
		this.quantity = quantity;
		this.deliveryDate = deliveryDate;
	}
	public Order()
	{
		super();
		
	}
	public long getId () {
		return id;
	}	
	public void setId(long id)
	{
		this.id=id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public VehicleModel getModel() {
		return model;
	}
	public void setModel(VehicleModel model) {
		this.model = model;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public long getInvoiceTotal ()
	{
		return quantity * model.getPriceInEuroCent();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + quantity;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (quantity != other.quantity)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}
	
	@Override
	public String toString ()
	{
		return "Order " + String.valueOf(getId()) + ":\n" +
			String.valueOf(quantity) + "x " + model.getName() + "\n" +
			customer.getFirstName() + " " + customer.getLastName();
	}
}