package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class VehicleOrderingApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		classes.add(OrdersWS.class);
		classes.add(SquareWS.class);
		classes.add(CreateOrderWS.class);
		
		return classes;
	}

}
