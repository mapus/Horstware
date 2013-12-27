package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nttdata.emea.devschool.vehicleordering.business.Customer;
import com.nttdata.emea.devschool.vehicleordering.business.Order;
import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;

@ManagedBean(name = "searchOrdersBB")
@SessionScoped
public class SearchOrdersBB {

	private static final Logger LOG = Logger.getLogger(SearchOrdersBB.class
			.getName());

	private String firstName;

	private String lastName;

	private List<FoundOrderVO> searchResults;

	@EJB
	private OrderBS orderBS;

	public String filterResults() {
		LOG.info("Filter results for firstName " + firstName + " and lastName "
				+ lastName);

		searchResults = new ArrayList<FoundOrderVO>();

		for (Order order : orderBS.findOrders(firstName, lastName)) {
			Customer customer = order.getCustomer();

			FoundOrderVO foundOrder = new FoundOrderVO(customer.getFirstName(),
					customer.getLastName(), order.getAmount());
			searchResults.add(foundOrder);
		}

		return null;
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

	public List<FoundOrderVO> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<FoundOrderVO> searchResults) {
		this.searchResults = searchResults;
	}
}
