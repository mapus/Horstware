package com.nttdata.emea.devschool.vehicleordering.xml.impl;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;

@Root(name="models")
public class XMLVehicleModelResponse {		
		@ElementList(inline=true)
		private List<VehicleModel> model;
		
		public List<VehicleModel> getModels()
		{
			return this.model;
		}
	
}
