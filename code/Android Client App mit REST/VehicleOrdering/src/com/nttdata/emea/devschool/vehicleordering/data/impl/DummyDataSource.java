package com.nttdata.emea.devschool.vehicleordering.data.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;

public class DummyDataSource implements DataSource
{
	private static Map<Long, Customer> customers;
	private static Map<Long, VehicleModel> vehicleModels;
	private static Map<Long, VehicleType> vehicleTypes;
	private static Map<Long, Order> vehicleOrders;
	
	private static long customerIdCounter;
	private static long vehicleOrderIdCounter;
	
	static
	{
		customers = new HashMap<Long, Customer>();
		vehicleModels = new HashMap<Long, VehicleModel>();
		vehicleTypes = new HashMap<Long, VehicleType>();
		vehicleOrders = new HashMap<Long, Order>();
		
		Customer customer1 = new Customer(1, "Jon", "Doe");
		Customer customer2 = new Customer(2, "Jane", "Dove");
		Customer customer3 = new Customer(3, "Jane", "Doe");
		customers.put(customer1.getId(), customer1);
		customers.put(customer2.getId(), customer2);
		customers.put(customer3.getId(), customer3);
		
		customerIdCounter = 3;
		
		VehicleType type1 = new VehicleType(1, "Dreiraeder");
		VehicleType type2 = new VehicleType(2, "Speed-Boats");
		vehicleTypes.put(type1.getId(), type1);
		vehicleTypes.put(type2.getId(), type2);
		
		String loremIpsum = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.";
		
		VehicleModel model1 = new VehicleModel(1, "Todestreter Treitausend", type1, "Holy Cow, das Ding geht ab! " + loremIpsum, "http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg", 10000);
		VehicleModel model2 = new VehicleModel(2, "Tripletastic", type1, "Da legts di nieder! " + loremIpsum, "http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg", 9999);
		VehicleModel model3 = new VehicleModel(3, "Gammelkanu", type2, "Meh, nicht so knorke. " + loremIpsum, "http://demos.appthemes.com/classipress/files/2012/06/656479.jpg", 10000000);
		VehicleModel model4 = new VehicleModel(4, "Bremser-Boye", type2, "Da schwimmt man schneller. " + loremIpsum, "http://tzach.edublogs.org/files/2011/01/speedBoat-szx5d2.jpg", 8000000);
		vehicleModels.put(model1.getId(), model1);
		vehicleModels.put(model2.getId(), model2);
		vehicleModels.put(model3.getId(), model3);
		vehicleModels.put(model4.getId(), model4);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 24);
		Date deliveryDate = calendar.getTime();
		
		Order vehicleOrder1 = new Order(1, customer1, model1, 6, deliveryDate);
		Order vehicleOrder2 = new Order(2, customer1, model2, 1, deliveryDate);
		Order vehicleOrder3 = new Order(3, customer2, model1, 5, deliveryDate);
		Order vehicleOrder4 = new Order(4, customer2, model3, 2, deliveryDate);
		Order vehicleOrder5 = new Order(5, customer3, model2, 4, deliveryDate);
		Order vehicleOrder6 = new Order(6, customer3, model4, 3, deliveryDate);
		vehicleOrders.put(vehicleOrder1.getId(), vehicleOrder1);
		vehicleOrders.put(vehicleOrder2.getId(), vehicleOrder2);
		vehicleOrders.put(vehicleOrder3.getId(), vehicleOrder3);
		vehicleOrders.put(vehicleOrder4.getId(), vehicleOrder4);
		vehicleOrders.put(vehicleOrder5.getId(), vehicleOrder5);
		vehicleOrders.put(vehicleOrder6.getId(), vehicleOrder6);
		
		vehicleOrderIdCounter = 6;
	}
	
	@Override
	public Customer retrieveCustomer (long id)
	{
		return customers.get(id);
	}
	
	@Override
	public List<Customer> retrieveCustomers ()
	{
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public List<Customer> findCustomers(String firstName, String lastName)
	{
		List<Customer> foundCustomers = new ArrayList<Customer>();
		for(Customer c : customers.values())
		{
			if((c.getFirstName().equals(firstName)) && (c.getLastName().equals(lastName)))
			{
				foundCustomers.add(c);
			}
		}
		return foundCustomers;
	}

	@Override
	public Customer createCustomer(String firstName, String lastName)
	{
		Customer newCustomer = new Customer(++customerIdCounter, firstName, lastName);
		customers.put(newCustomer.getId(), newCustomer);
		return newCustomer;
	}
	
	@Override
	public VehicleModel retrieveVehicleModel (long id)
	{
		return vehicleModels.get(id);
	}
	
	@Override
	public List<VehicleModel> retrieveVehicleModels ()
	{
		return new ArrayList<VehicleModel>(vehicleModels.values());
	}
	
	@Override
	public List<VehicleModel> retrieveVehicleModels (VehicleType filterByType)
	{
		List<VehicleModel> results = new ArrayList<VehicleModel>();
		for(VehicleModel vm : vehicleModels.values())
		{
			if(vm.getType().equals(filterByType))
			{
				results.add(vm);
			}
		}
		return results;
	}
	
	@Override
	public VehicleType retrieveVehicleType (long id)
	{
		return vehicleTypes.get(id);
	}
	
	@Override
	public List<VehicleType> retrieveVehicleTypes ()
	{
		return new ArrayList<VehicleType>(vehicleTypes.values());
	}
	
	@Override
	public Order retrieveVehicleOrder (long id)
	{
		return vehicleOrders.get(id);
	}
	
	@Override
	public List<Order> retrieveVehicleOrders ()
	{
		return new ArrayList<Order>(vehicleOrders.values());
	}
	
	@Override
	public List<Order> retrieveVehicleOrders(String filterByCustomersFirstName, String filterByCustomersLastName, VehicleModel filterByModel)
	{
		List<Order> results = new ArrayList<Order>(vehicleOrders.values());
		if(filterByCustomersFirstName != null)
		{
			filterVehicleOrdersByCustomersFirstName(results, filterByCustomersFirstName);
		}
		if(filterByCustomersLastName != null)
		{
			filterVehicleOrdersByCustomersLastName(results, filterByCustomersLastName);
		}
		if(filterByModel != null)
		{
			filterVehicleOrdersByModel(results, filterByModel);
		}
		return results;
	}
	
	@Override
	public Order createVehicleOrder(Customer customer, VehicleModel model, int quantity, Date deliveryDate)
	{
		Order newOrder = new Order(++vehicleOrderIdCounter, customer, model, quantity, deliveryDate);
		vehicleOrders.put(newOrder.getId(), newOrder);
		return newOrder;
	}
	
	private void filterVehicleOrdersByCustomersFirstName(List<Order> vehicleOrders, String firstName)
	{
		for(int index = vehicleOrders.size() - 1 ; index >= 0 ; index--)
		{
			String currentName = vehicleOrders.get(index).getCustomer().getFirstName();
			if(!currentName.contains(firstName))
			{
				vehicleOrders.remove(index);
			}
		}
	}
	
	private void filterVehicleOrdersByCustomersLastName(List<Order> vehicleOrders, String lastName)
	{
		for(int index = vehicleOrders.size() - 1 ; index >= 0 ; index--)
		{
			String currentName = vehicleOrders.get(index).getCustomer().getLastName();
			if(!currentName.contains(lastName))
			{
				vehicleOrders.remove(index);
			}
		}
	}
	
	private void filterVehicleOrdersByModel(List<Order> vehicleOrders, VehicleModel model)
	{
		for(int index = vehicleOrders.size() - 1 ; index >= 0 ; index--)
		{
			VehicleModel currentModel = vehicleOrders.get(index).getModel();
			if(!currentModel.equals(model))
			{
				vehicleOrders.remove(index);
			}
		}
	}
}