package com.nttdata.emea.devschool.vehicleordering.data;

import java.util.Date;
import java.util.List;

import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;

public interface DataSource
{
	public Customer loadCustomer (long id);
	public List<Customer> loadCustomers ();
	public Customer findCustomer(String firstName, String lastName);
	public Customer createCustomer(String firstName, String lastName);
	
	public VehicleModel loadVehicleModel (long id);
	public List<VehicleModel> loadVehicleModels ();
	public List<VehicleModel> loadVehicleModels (VehicleType filterByType);
	
	public VehicleType loadVehicleType (long id);
	public List<VehicleType> loadVehicleTypes ();
	
	public VehicleOrder loadVehicleOrder (long id);
	public List<VehicleOrder> loadVehicleOrders ();
	public List<VehicleOrder> loadVehicleOrders (String filterByCustomersFirstName, String filterByCustomersLastName, VehicleModel filterByModel);
	public VehicleOrder createVehicleOrder(Customer customer, VehicleModel model, int amount, Date deliveryDate);
}
