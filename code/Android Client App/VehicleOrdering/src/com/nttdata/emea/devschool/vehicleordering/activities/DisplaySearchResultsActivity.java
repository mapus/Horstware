package com.nttdata.emea.devschool.vehicleordering.activities;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.network.OnRestResponse;
import com.nttdata.emea.devschool.vehicleordering.network.VehicleOrderingAPI;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleOrderResponse;

public class DisplaySearchResultsActivity extends Activity
{
	private List<Order> orders;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_search_results);
		
		searchOrders();
	}
	
	private void searchOrders ()
	{
		Bundle extras = getIntent().getExtras();
		
		if(extras == null)
		{
			VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
			api.getOrders(new OnRestResponse()
			{
				@Override
				public void onResponse(String response)
				{

						Log.d("lol","lol");
						Log.d("lol",response);
						Serializer serializer = new Persister();
						XMLVehicleOrderResponse xmlVehicleOrderResponse = null;
						try {
							
							xmlVehicleOrderResponse = serializer.read(XMLVehicleOrderResponse.class, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						orders = xmlVehicleOrderResponse.getOrders();
						Log.d("size",orders.size()+"");
						setupOrderList();

				}
				
				@Override
				public void onError(Integer errorCode, String errorMessage) {}
			});
		}
		else
		{
			String firstNameFilter = getFirstNameFilter(extras);
			String lastNameFilter = getLastNameFilter(extras);
			Long modelIdFilter = getModelFilter(extras);
			VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
			api.getOrders(firstNameFilter, lastNameFilter, modelIdFilter, new OnRestResponse()
			{
				@Override
				public void onResponse(String response)
				{
					Log.d("derp","test");
					try
					{
						Serializer serializer = new Persister();
						XMLVehicleOrderResponse xmlVehicleOrderResponse = serializer.read(XMLVehicleOrderResponse.class, response);
						

						orders = xmlVehicleOrderResponse.getOrders();

						setupOrderList();
					}
					catch (Exception e)
					{
						new RuntimeException(e);
					}
				}
				
				@Override
				public void onError(Integer errorCode, String errorMessage) {}
			});
		}
	}
	
	private String getFirstNameFilter (Bundle extras)
	{
		boolean filterByFirstName = extras.getBoolean(ExtraKeys.FILTER_BY_FIRST_NAME);
		if(filterByFirstName)
		{
			return extras.getString(ExtraKeys.FIRST_NAME);
		}
		else 
		{
			return null;
		}
	}
	
	private String getLastNameFilter (Bundle extras)
	{
		boolean filterByLastName = extras.getBoolean(ExtraKeys.FILTER_BY_LAST_NAME);
		if(filterByLastName)
		{
			return extras.getString(ExtraKeys.LAST_NAME);
		}
		else 
		{
			return null;
		}
	}
	
	private Long getModelFilter (Bundle extras)
	{
		boolean filterByModel = extras.getBoolean(ExtraKeys.FILTER_BY_MODEL);
		if(filterByModel)
		{
			Long modelId = extras.getLong(ExtraKeys.MODEL_ID);
			return modelId;
		}
		else 
		{
			return null;
		}
	}
	
	private void setupOrderList ()
	{
		ListView orderListView = (ListView) findViewById(R.id.displaySearchResults_ordersList);
		setupOrderListAdapter(orderListView);
		setupOrderListOnItemClickListener(orderListView);
	}
	
	private void setupOrderListAdapter (ListView orderListView)
	{
		List<String> entries = new ArrayList<String>();
		if(orders.size() > 0)
		{
			for(Order order : orders)
			{
				entries.add(order.toString());
			}
		}
		else
		{
			String noSearchResults = getResources().getString(R.string.searchOrders_searchWithoutResults);
			entries.add(noSearchResults);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_display_search_results_result_list, entries);
		orderListView.setAdapter(adapter);
	}
	
	private void setupOrderListOnItemClickListener (ListView orderListView)
	{
		final Context context = this;
		orderListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(context, ViewOrderDetailsActivity.class);
				long orderId = orders.get(position).getId();
				intent.putExtra(ExtraKeys.ORDER_ID, orderId);
				startActivity(intent);
			}
		});
	}
}