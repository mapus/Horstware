package com.nttdata.emea.devschool.vehicleordering.data;

import java.util.Date;
import java.util.List;

import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;

public interface DataSource
{
	public VehicleType retrieveVehicleType (long id);
	public List<VehicleType> retrieveVehicleTypes ();
	
	public VehicleModel retrieveVehicleModel (long id);
	public List<VehicleModel> retrieveVehicleModels ();
	public List<VehicleModel> retrieveVehicleModels (VehicleType filterByType);
	
	public Customer createCustomer (String firstName, String lastName);
	public Customer retrieveCustomer (long id);
	public List<Customer> retrieveCustomers ();
	public List<Customer> findCustomers (String firstName, String lastName);
	
	public VehicleOrder createVehicleOrder (Customer customer, VehicleModel model, int quantity, Date deliveryDate);
	public VehicleOrder retrieveVehicleOrder (long id);
	public List<VehicleOrder> retrieveVehicleOrders ();
	public List<VehicleOrder> retrieveVehicleOrders (String filterByCustomersFirstName, String filterByCustomersLastName, VehicleModel filterByModel);
}
