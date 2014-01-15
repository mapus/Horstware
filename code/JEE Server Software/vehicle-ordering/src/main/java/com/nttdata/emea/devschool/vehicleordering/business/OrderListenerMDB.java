package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.nttdata.emea.devschool.vehicleordering.web.CreateOrderBB;

@MessageDriven(mappedName="jms/vehicleordering/orders")
public class OrderListenerMDB implements MessageListener
{
	private final static Logger LOG = Logger.getLogger(CreateOrderBB.class.getName());
	
	@Override
	public void onMessage(Message msg)
	{
		try
		{
			String firstName = msg.getStringProperty("firstName");
			String lastName = msg.getStringProperty("lastName");
			int amount = msg.getIntProperty("amount");
			
			LOG.info("message received: firstName='" + firstName + "'; lastName='" + lastName + "', amount='" + amount + "'");
		}
		catch (JMSException ignored) {}
	}
}