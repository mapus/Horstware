package com.nttdata.emea.devschool.vehicleordering.entities;

import org.simpleframework.xml.Element;

public class Customer
{
	@Element
	private String firstName;
	@Element
	private String lastName;
	@Element
	private long id;
	
	public Customer (long id, String firstName, String lastName)
	{
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public void setId(long id)
	{
		this.id=id;
	}
	public long getId () {
		return id;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Customer other = (Customer) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	@Override
	public String toString ()
	{
		return super.toString() + ": " + firstName + " " + lastName;
	}
}