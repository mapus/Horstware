package com.nttdata.emea.devschool.vehicleordering.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nttdata.emea.devschool.vehicleordering.business.Type;
import com.nttdata.emea.devschool.vehicleordering.business.TypeDAO;
import com.nttdata.emea.devschool.vehicleordering.web.xml_roots.Types;

@Stateless
@Path("/types")
public class TypesWS
{
	@EJB private TypeDAO dao;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Types getTypes ()
	{
		Types types = new Types();
		types.type = dao.retrieveAll();
		return types;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Types getType (@PathParam("id") String id)
	{
		Type type = dao.findById(Long.valueOf(id));
		List<Type> typeList =  new ArrayList<Type>();
		if(type != null) typeList.add(type);
		Types orders = new Types();
		orders.type = typeList;
		return orders;
	}
}