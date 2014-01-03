package com.nttdata.emea.devschool.vehicleordering.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;


@Path("/create")
@Stateless
public class CreateOrderWS {
	@EJB
	private OrderBS orderBS;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String createOrder(@QueryParam("customerFirstName") String customerFirstName, @QueryParam("customerLastName") String customerLastName,@QueryParam("amount") int amount, @QueryParam("deliveryDate") String deliveryDate){
		 Date date=null ; 
		 
		 DateFormat format = new SimpleDateFormat("ddMMyyyy");
		  try {
			date = (Date)format.parse(deliveryDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		orderBS.createOrder(customerFirstName, customerLastName, amount,date );
		return "<html><body>First Name:"+customerFirstName+"<br>Last Name:"+customerLastName+"<br>Amount:"+amount+"<br>Delivery Date:"+date+"</body></html>";
	}

}
