package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/vehicle-ordering/orders")
public class OrderListenerMDB implements MessageListener {

	private static final Logger LOG = Logger.getLogger(OrderListenerMDB.class.getName());

	@Override
	public void onMessage(Message message) {
		try {
			LOG.info("Got message: " + formatMessage(message));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private String formatMessage(Message message) throws JMSException {
		return message.getStringProperty("customerFirstName")
				+ " " + message.getStringProperty("customerLastName")
				+ " bought " + message.getIntProperty("amount");
	}

}
