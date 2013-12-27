package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.nttdata.emea.devschool.vehicleordering.business.Order;
import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;

@Path("/orders")
@Stateless
public class OrdersWS {
	
	@EJB
	private OrderBS orderBS;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Orders findOrders(@QueryParam("customerFirstName") String customerFirstName, @QueryParam("customerLastName") String customerLastName){
		List<Order> foundOrders = orderBS.findOrders(customerFirstName, customerLastName);
		
		Orders orders = new Orders();
		orders.order = foundOrders;
		
		return orders;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void createOrder(Orders orders){
		for(Order o : orders.order){
			orderBS.createOrder(o.getCustomer().getFirstName(), o.getCustomer().getLastName(), o.getAmount(), o.getDeliveryDate());
		}
	}

}
