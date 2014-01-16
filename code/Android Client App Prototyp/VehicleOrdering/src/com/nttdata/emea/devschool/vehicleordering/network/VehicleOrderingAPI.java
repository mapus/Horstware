package com.nttdata.emea.devschool.vehicleordering.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.accounts.NetworkErrorException;
import android.util.Log;


public class VehicleOrderingAPI implements OnRestResponse {
	private static final String SERVER_HOSTNAME ="https://learning-campus.fh-rosenheim.de/"; //http://inf-be04.inf.fh-rosenheim.de";
	private static final String SERVER_REST_API= "/webservice/rest/server.php";
	private static VehicleOrderingAPI instance = new VehicleOrderingAPI();


	public static VehicleOrderingAPI getInstance() {
		return instance;
	}
	private VehicleOrderingAPI()
	{
		
	}
	


	public void executeRequest(String token, String modulName,
			List<NameValuePair> params, OnRestResponse listener) throws NetworkErrorException {
		
		RestClient client = new RestClient(SERVER_HOSTNAME);
		if(params==null)
		{
			params = new ArrayList<NameValuePair>();
		}
        Log.d("VehicleOrderingAPI","calling api");
        client.call(SERVER_REST_API,params,listener);
		
	}
	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(Integer errorCode, String errorMessage) {
		// TODO Auto-generated method stub
		
	}

}
