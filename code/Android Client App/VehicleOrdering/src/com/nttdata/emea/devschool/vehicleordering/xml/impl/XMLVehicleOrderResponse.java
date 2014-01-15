package com.nttdata.emea.devschool.vehicleordering.xml.impl;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;

@Root(name="orders")
public class XMLVehicleOrderResponse {
	
	@ElementList(inline=true,entry="order")
	private List<Order> orders;
	

	public List<Order> getOrders()
	{
		return this.orders;
	}
}
