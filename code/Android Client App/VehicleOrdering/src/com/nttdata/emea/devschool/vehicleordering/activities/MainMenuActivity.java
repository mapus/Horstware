package com.nttdata.emea.devschool.vehicleordering.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.network.VehicleOrderingAPI;

public class MainMenuActivity extends Activity
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}
	
	public void browseModels (View view)
	{
		VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
		api.getOrders();
		//Intent intent = new Intent(this, BrowseModelsActivity.class);
		//startActivity(intent);
	}
	
	public void searchOrders (View view)
	{
		Intent intent = new Intent(this, SearchOrdersActivity.class);
		startActivity(intent);
	}
}