package com.nttdata.emea.devschool.vehicleordering.network;


public class VehicleOrderingAPI
{
	private static final String SERVER_HOSTNAME = "http://192.168.178.42:8080";;
	private static final String SERVICE_URL = "vehicle-ordering/services/";
	
	private static VehicleOrderingAPI instance = new VehicleOrderingAPI();
	
	private VehicleOrderingAPI() {}
	
	public static VehicleOrderingAPI getInstance() {
		return instance;
	}
	
	public void getOrders (OnRestResponse listener) {
		String URL = SERVICE_URL + "orders";
		get(listener, URL);
	}
	
	public void getOrders (String customerFirstName, String customerLastName, Long id, OnRestResponse listener) {
		String URL = SERVICE_URL + "orders?";
		boolean first = true;
		
		if(customerFirstName != null)
		{
			URL += ("first=" + customerFirstName);
			first = false;
		}
		if(customerLastName != null)
		{
			if(!first) URL += "&";
			URL += ("last=" + customerFirstName);
			first = false;
		}
		if(id != null)
		{
			if(!first) URL += "&";
			URL += ("model=" + id.toString());
		}
		get(listener, URL);
	}
	
	public void getOrder (long id, OnRestResponse listener) {
		String URL = SERVICE_URL + "orders/" + String.valueOf(id);
		get(listener, URL);
	}
	
	public void getCustomers(OnRestResponse listener) {
		String URL = SERVICE_URL + "customers";
		get(listener, URL);
	}
	
	public void getCustomers(String firstName, String lastName, OnRestResponse listener) {
		boolean first = true;
		String URL = SERVICE_URL + "customers?";
		if(firstName != null)
		{
			URL += ("first=" + firstName);
			first = false;
		}
		if(lastName != null)
		{
			if(!first) URL += "&";
			URL += ("last=" + lastName);
		}
		get(listener, URL);
	}
	
	public void getCustomer (long id, OnRestResponse listener) {
		String URL = SERVICE_URL + "customers/" + String.valueOf(id);
		get(listener, URL);
	}
	
	public void getModels (OnRestResponse listener) {
		String URL = SERVICE_URL + "models";
		get(listener, URL);
	}
	
	public void getModels (Long typeId, OnRestResponse listener) {
		String URL = SERVICE_URL + "models";
		if(typeId != null) URL += ("?type=" + typeId.toString());
		get(listener, URL);
	}
	
	public void getModel (long id, OnRestResponse listener) {
		String URL = SERVICE_URL + "models/" + String.valueOf(id);
		get(listener, URL);
	}
	
	public void getTypes (OnRestResponse listener) {
		String URL = SERVICE_URL + "types";
		get(listener, URL);
	}
	
	public void getType (long id, OnRestResponse listener) {
		String URL = SERVICE_URL + "types/" + String.valueOf(id);
		get(listener, URL);
	}
	
	private void get (OnRestResponse listener, String URL) {
		RestClient client = new RestClient(SERVER_HOSTNAME);
		client.addHeader("Content-Type", "application/xml");
		client.get(URL, listener);
	}
}