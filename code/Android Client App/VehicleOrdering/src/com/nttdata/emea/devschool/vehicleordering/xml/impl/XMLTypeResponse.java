package com.nttdata.emea.devschool.vehicleordering.xml.impl;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;

@Root(name="types")
public class XMLTypeResponse {
	
	@ElementList(name="type",inline=true)
	private List<VehicleType> types;
	
	public List<VehicleType> getTypes()
	{
		return this.types;
	}
}
