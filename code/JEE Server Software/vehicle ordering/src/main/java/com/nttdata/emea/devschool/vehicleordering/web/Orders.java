package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.nttdata.emea.devschool.vehicleordering.business.Order;

@XmlRootElement
public class Orders {
	
	public List<Order> order;
	
}
