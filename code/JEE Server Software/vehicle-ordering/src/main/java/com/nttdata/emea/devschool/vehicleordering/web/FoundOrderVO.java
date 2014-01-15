package com.nttdata.emea.devschool.vehicleordering.web;

public class FoundOrderVO
{
	private String firstName;
	private String lastName;
	private int amount;
	
	public FoundOrderVO(String firstName, String lastName, int amount)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
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
}
