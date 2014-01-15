package com.nttdata.emea.devschool.vehicleordering.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.nttdata.emea.devschool.vehicleordering.business.Model;
import com.nttdata.emea.devschool.vehicleordering.business.ModelDAO;
import com.nttdata.emea.devschool.vehicleordering.business.Type;
import com.nttdata.emea.devschool.vehicleordering.business.TypeDAO;
import com.nttdata.emea.devschool.vehicleordering.web.xml_roots.Models;

@Stateless
@Path("/models")
public class ModelsWS
{
	@EJB private ModelDAO dao;
	@EJB private TypeDAO typeDao;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Models getModels (@QueryParam("type") String typeId)
	{
		Type type = null;
		if(typeId != null)
		{
			type = typeDao.findById(Long.valueOf(typeId));
		}
		Models models = new Models();
		models.model = dao.findByType(type);
		return models;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Models getModel (@PathParam("id") String id)
	{
		Model model = dao.findById(Long.valueOf(id));
		List<Model> modelList =  new ArrayList<Model>();
		if(model != null) modelList.add(model);
		Models models = new Models();
		models.model = modelList;
		return models;
	}
}