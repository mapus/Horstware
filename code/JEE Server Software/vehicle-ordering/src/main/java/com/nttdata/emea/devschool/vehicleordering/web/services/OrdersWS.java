package com.nttdata.emea.devschool.vehicleordering.web.services;

import java.util.ArrayList;
import java.util.List;

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

import com.nttdata.emea.devschool.vehicleordering.business.Model;
import com.nttdata.emea.devschool.vehicleordering.business.ModelDAO;
import com.nttdata.emea.devschool.vehicleordering.business.Order;
import com.nttdata.emea.devschool.vehicleordering.business.OrderDAO;
import com.nttdata.emea.devschool.vehicleordering.web.xml_roots.Orders;

@Stateless
@Path("/orders")
public class OrdersWS
{
	@EJB private OrderDAO dao;
	@EJB private ModelDAO modelDao;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Orders getOrders (@QueryParam("first") String customerFirstName, @QueryParam("last") String customerLastName, @QueryParam("model") String modelId)
	{
		Orders orders = new Orders();
		
		Model model = null;
		if(modelId != null)
		{
			model = modelDao.findById(Long.valueOf(modelId));
		}
		orders.order = dao.findByCustomerOrModel(customerFirstName, customerLastName, model);
		return orders;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Orders getOrder (@PathParam("id") String id)
	{
		Order order = dao.findById(Long.valueOf(id));
		List<Order> orderList =  new ArrayList<Order>();
		if(order != null) orderList.add(order);
		Orders orders = new Orders();
		orders.order = orderList;
		return orders;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void postOrders (Orders orders)
	{
		for(Order o : orders.order)
		{
			Order newOrder = new Order(o.getCustomer(), o.getModel(), o.getAmount(), o.getDeliveryDate());
			dao.persist(newOrder);
		}
	}
}