package com.nttdata.emea.devschool.vehicleordering.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/square")
public class SquareWS {
	
	@GET
	@Path("{val}")
	@Produces(MediaType.TEXT_HTML)
	public String square(@PathParam("val") long val){
		double res = val*val;
		return "<html><body>The square of " + val + " is " + res + "</body></html>";
	}

}
