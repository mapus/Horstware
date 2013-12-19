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
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class DisplaySearchResultsActivity extends Activity
{
	private DataSource dataSource;
	private List<VehicleOrder> orders;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_search_results);
		
		dataSource = DataSourceFactory.createDataSource();
		orders = searchOrders();
		
		setupOrderList();
	}
	
	private List<VehicleOrder> searchOrders ()
	{
		Bundle extras = getIntent().getExtras();
		
		String firstNameFilter = extras.getString(ExtraKeys.FIRST_NAME_FILTER);
		String lastNameFilter = extras.getString(ExtraKeys.LAST_NAME_FILTER);
		
		VehicleModel modelFilter = null;
		boolean filterByModel = extras.getBoolean(ExtraKeys.FILTER_BY_MODEL);
		if(filterByModel)
		{
			long modelId = extras.getLong(ExtraKeys.MODEL_ID);
			modelFilter = dataSource.loadVehicleModel(modelId);
		}
		
		List<VehicleOrder> result = dataSource.loadVehicleOrders(firstNameFilter, lastNameFilter, modelFilter);
		return result;
	}
	
	private void setupOrderList ()
	{
		ListView orderListView = (ListView) findViewById(R.id.displaySearchResults_ordersList);
		configureOrderListAdapter(orderListView);
		configureOrderListOnItemClickListener(orderListView);
	}
	
	private void configureOrderListAdapter (ListView orderListView)
	{
		List<String> entries = new ArrayList<String>();
		for(VehicleOrder order : orders)
		{
			entries.add(order.toString());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_item, entries);
		orderListView.setAdapter(adapter);
	}
	
	private void configureOrderListOnItemClickListener (ListView orderListView)
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