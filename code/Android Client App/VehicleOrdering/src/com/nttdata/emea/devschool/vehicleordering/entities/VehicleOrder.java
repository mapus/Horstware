package com.nttdata.emea.devschool.vehicleordering.entities;

import java.util.Date;

public class VehicleOrder extends AbstractEntity
{
	private Customer customer;
	private VehicleModel model;
	private int amount;
	private Date deliveryDate;
	
	public VehicleOrder (long id, Customer customer, VehicleModel model, int amount, Date deliveryDate)
	{
		super(id);
		this.customer = customer;
		this.model = model;
		this.amount = amount;
		this.deliveryDate = deliveryDate;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + amount;
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
		VehicleOrder other = (VehicleOrder) obj;
		if (amount != other.amount)
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
		return super.toString() + ": " + amount + " [" + model.toString() + "] for [" + customer.toString() + "]" ;
	}
}