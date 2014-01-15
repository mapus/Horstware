package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order extends AbstractEntity
{
	@ManyToOne private Customer customer;
	private int amount;
	private Date deliveryDate;
	@ManyToOne private Model model;
	
	public Order () {};
	
	public Order(Customer customer, Model model, int amount, Date deliveryDate) {
		super();
		this.customer = customer;
		this.amount = amount;
		this.deliveryDate = deliveryDate;
		this.model = model;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Temporal(TemporalType.DATE)
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result
				+ ((model == null) ? 0 : model.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
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
}
