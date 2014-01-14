package com.nttdata.emea.devschool.vehicleordering.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleOrderResponse;

import android.accounts.NetworkErrorException;
import android.util.Log;


public class VehicleOrderingAPI implements OnRestResponse {
	private static final String SERVER_HOSTNAME ="https://learning-campus.fh-rosenheim.de/"; //http://inf-be04.inf.fh-rosenheim.de";
	private static final String SERVER_REST_API= "/webservice/rest/server.php";
	private static VehicleOrderingAPI instance = new VehicleOrderingAPI();

	private String response = "";

	public static VehicleOrderingAPI getInstance() {
		return instance;
	}
	private VehicleOrderingAPI()
	{
		
	}
	


	public void executeRequest(String token, String modulName,

			
	List<NameValuePair> params, OnRestResponse listener) throws NetworkErrorException {
		
		this.response="";
		RestClient client = new RestClient(SERVER_HOSTNAME);
		if(params==null)
		{
			params = new ArrayList<NameValuePair>();
		}
        Log.d("VehicleOrderingAPI","calling api");
        client.callPost(SERVER_REST_API,params,listener);
		
	}
	@Override
	public void onResponse(String response) {
		this.response=response;
		Log.d("[API-Response]",response);
		
	}
	@Override
	public void onError(Integer errorCode, String errorMessage) {
		this.response=errorMessage;
		Log.d("[API-Response]",errorMessage);
	}
	
	public List<Order> getOrders()
	{
		Serializer serializer = new Persister();
		XMLVehicleOrderResponse responseObjekt=null;
		
		RestClient client = new RestClient(SERVER_HOSTNAME);
		try {
			client.callPost(SERVER_REST_API, null, this);
		} catch (NetworkErrorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(this.response==null);		//Busywait
		
		try {
		responseObjekt= serializer.read(XMLVehicleOrderResponse.class,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseObjekt.getOrders();
	}

}
