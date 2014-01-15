package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.nttdata.emea.devschool.vehicleordering.web.services.CustomersWS;
import com.nttdata.emea.devschool.vehicleordering.web.services.ModelsWS;
import com.nttdata.emea.devschool.vehicleordering.web.services.OrdersWS;
import com.nttdata.emea.devschool.vehicleordering.web.services.TypesWS;

@ApplicationPath("/services")
public class VehicleOrderingApplication extends Application
{
	@Override
	public Set<Class<?>> getClasses ()
	{
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		classes.add(TypesWS.class);
		classes.add(ModelsWS.class);
		classes.add(CustomersWS.class);
		classes.add(OrdersWS.class);
		
		return classes;
	}
}