package com.nttdata.emea.devschool.vehicleordering.business;

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
public class OrderBA {

	@EJB
	private OrderTrackerBA orderTrackerBA;

	@Resource(lookup = "jms/__defaultConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "jms/vehicle-ordering/orders")
	private Destination ordersDestination;
	
	@EJB
	private OrderDAO orderDAO;

	public void createOrder(Order order) {
		if (orderTrackerBA.getNumberOfOrderedCars() + order.getAmount() > 10) {
			throw new TooManyOrderedCarsException();
		}

		if (orderTrackerBA.equalOrderExists(order)) {
			throw new DuplicateOrderException();
		}

		orderTrackerBA.addOrder(order);
		
		orderDAO.persist(order);

		sendOrderMessage(order);
	}

	private void sendOrderMessage(Order order) {
		try {
			Connection connection = connectionFactory.createConnection();

			try {
				Session session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				
				Message message = createOrderMessage(session, order);
				
				MessageProducer producer = session.createProducer(ordersDestination);
				producer.send(message);
			} finally {
				connection.close();
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private Message createOrderMessage(Session session, Order order)
			throws JMSException {
		Message message = session.createMessage();
		
		message.setStringProperty("customerFirstName", order.getCustomer().getFirstName());
		message.setStringProperty("customerLastName", order.getCustomer().getLastName());
		message.setIntProperty("amount", order.getAmount());
		
		return message;
	}
	
	public List<Order> findOrders(String customerFirstName, String customerLastName){
		return orderDAO.findByCustomer(customerFirstName, customerLastName);
	}

}
