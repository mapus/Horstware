package com.nttdata.emea.devschool.vehicleordering.xml.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.xml.XMLParserFactory;

public class OrderParser {
	
	XmlPullParser parser;
	XmlPullParserFactory factory;
	
	
	public OrderParser() throws XmlPullParserException
	{
		factory = XmlPullParserFactory.newInstance();
		parser=factory.newPullParser();
	}
	
	public List<Order> parseOrderList(String xml) throws XmlPullParserException, NumberFormatException, IOException
	{
		
		InputStream in_s = null;
		//TODO setinputstreamfromstring
		parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
		parser.setInput(in_s,null);
		
		
		
		int eventType = parser.getEventType();
		List<Order> orderList=null;
		Order currentOrder=null;
		Customer currentCustomer =null;
		
		while(eventType != XmlPullParser.END_DOCUMENT)
		{
			String tagName = null;
			switch(eventType)
			{
			case XmlPullParser.START_DOCUMENT:
				tagName=parser.getName();
				break;
			case XmlPullParser.START_TAG:
				
				if(tagName=="order"){
					currentOrder=new Order();
				}
				if(currentCustomer == null)
				{
					if(tagName=="id"){
						currentOrder.setId(Long.parseLong(parser.nextText()));
					}
					if(tagName=="ammount"){
						currentOrder.setQuantity(Integer.parseInt(parser.nextText()));
					}
					
					if(tagName=="customer"){
						currentCustomer = new Customer();
					}
				}
				if(currentCustomer!=null)
				{
					if(tagName=="id"){
						currentCustomer.setId(Long.parseLong(parser.nextText()));
					}
					if(tagName=="firstName"){
						currentCustomer.setFirstName(parser.nextText());
					}
					if(tagName=="lastName"){
						currentCustomer.setLastName(parser.nextText());
					}
				}
				
				break;
				
			case XmlPullParser.END_TAG:
				tagName=parser.getName();
				
				if(tagName=="customer" && currentCustomer !=null){
					currentOrder.setCustomer(currentCustomer);
					
				}
			}
			
		}
		return null;
	}

}
