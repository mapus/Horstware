package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Stateless
public class OrderBS
{
	@EJB private CustomerBA customerBA;
	@EJB private OrderBA orderBA;
	
	@Resource(lookup="jms/__defaultConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup="jms/vehicleordering/orders")
	private Destination myDestination;
	
	public Order createOrder (String firstName, String lastName, int amount, Date deliveryDate, Model model)
	{
		Customer customer = customerBA.getOrCreateCustomer(firstName, lastName);
		Order order = new Order(customer, model, amount, deliveryDate);
		orderBA.createOrder(order);
		sendMessage(firstName, lastName, amount);
		return order;
	}
	
	public List<Order> findOrders (String firstName, String lastName)
	{
		return orderBA.findOrders(firstName, lastName);
	}
	
	private void sendMessage (String firstName, String lastName, int amount)
	{
		try
		{
			Connection connection = connectionFactory.createConnection();
			try
			{
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				
				Message message = session.createMessage();
				message.setStringProperty("firstName", firstName);
				message.setStringProperty("lastName", lastName);
				message.setIntProperty("amount", amount);
				
				MessageProducer producer = session.createProducer(myDestination);
				producer.send(message);
			}
			finally
			{
				connection.close();
			}
		}
		catch(JMSException ignored) {}
	}
}