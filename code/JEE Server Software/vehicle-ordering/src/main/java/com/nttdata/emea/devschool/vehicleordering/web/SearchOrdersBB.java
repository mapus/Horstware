package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nttdata.emea.devschool.vehicleordering.business.Order;
import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;

@SessionScoped
@ManagedBean(name="searchOrdersBB")
public class SearchOrdersBB
{
	private final static Logger LOG = Logger.getLogger(SearchOrdersBB.class.getName());
	
	private String firstName;
	private String lastName;
	private List<FoundOrderVO> searchResults;
	
	@EJB private OrderBS orderBS;
	
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
	public List<FoundOrderVO> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(List<FoundOrderVO> searchResults) {
		this.searchResults = searchResults;
	}
	
	public String filterResults()
	{
		LOG.info("Searched for " + firstName + " " + lastName + "!");
		
		List<Order> orders = orderBS.findOrders(firstName, lastName);
		searchResults = new ArrayList<FoundOrderVO>();
		for(Order o : orders)
		{
			searchResults.add(new FoundOrderVO(
				o.getCustomer().getFirstName(),
				o.getCustomer().getLastName(),
				o.getAmount()
			));
		}
		
		return null;
	}
}
