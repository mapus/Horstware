package com.nttdata.emea.devschool.vehicleordering.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceSingleton;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class DisplaySearchResultsActivity extends Activity
{
	private List<VehicleOrder> orders;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_search_results);
		
		orders = searchOrders();
		
		setupOrderList();
	}
	
	private List<VehicleOrder> searchOrders ()
	{
		Bundle extras = getIntent().getExtras();
		
		if(extras == null)
		{
			return DataSourceSingleton.getInstance().retrieveVehicleOrders();
		}
		else
		{
			String firstNameFilter = getFirstNameFilter(extras);
			String lastNameFilter = getLastNameFilter(extras);
			VehicleModel modelFilter = getModelFilter(extras);
			return DataSourceSingleton.getInstance().retrieveVehicleOrders(firstNameFilter, lastNameFilter, modelFilter);
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
	
	private VehicleModel getModelFilter (Bundle extras)
	{
		boolean filterByModel = extras.getBoolean(ExtraKeys.FILTER_BY_MODEL);
		if(filterByModel)
		{
			long modelId = extras.getLong(ExtraKeys.MODEL_ID);
			return DataSourceSingleton.getInstance().retrieveVehicleModel(modelId);
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
			for(VehicleOrder order : orders)
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