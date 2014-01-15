package com.nttdata.emea.devschool.vehicleordering.web.services;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.nttdata.emea.devschool.vehicleordering.business.Customer;
import com.nttdata.emea.devschool.vehicleordering.business.CustomerDAO;
import com.nttdata.emea.devschool.vehicleordering.web.xml_roots.Customers;

@Stateless
@Path("/customers")
public class CustomersWS
{
	@EJB private CustomerDAO dao;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Customers getCustomers (@QueryParam("first") String firstName, @QueryParam("last") String lastName)
	{
		Customers customers = new Customers();
		customers.customer = dao.findByName(firstName, lastName);
		return customers;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Customers getCustomer (@PathParam("id") String id)
	{
		Customers customers = new Customers();
		customers.customer = new ArrayList<Customer>();
		Customer customer = dao.findById(Long.valueOf(id));
		if(customer != null) customers.customer.add(customer);
		return customers;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void postCustomers (Customers customers)
	{
		for(Customer c : customers.customer)
		{
			Customer newCustomer = new Customer(c.getFirstName(), c.getLastName());
			dao.persist(newCustomer);
		}
	}	
}