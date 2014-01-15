package com.nttdata.emea.devschool.vehicleordering.web.xml_roots;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.nttdata.emea.devschool.vehicleordering.business.Customer;

@XmlRootElement
public class Customers
{
	public List<Customer> customer;
}