package com.nttdata.emea.devschool.vehicleordering.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceSingleton;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class SearchOrdersActivity extends Activity
{
	private final static int MODEL_SPINNER_DEFAULT_ENTRY_POSITION = 0;
	private final static int MODEL_SPINNER_DEFAULT_ENTRY_OFFSET = 1;
	
	private List<VehicleModel> models;
	private VehicleModel selectedModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_orders);
		
		models = DataSourceSingleton.getInstance().retrieveVehicleModels();
		selectedModel = null;
		
		setupModelSpinner();
	}
	
	private void setupModelSpinner()
	{
		Spinner modelSpinner = (Spinner) findViewById(R.id.searchOrders_modelFilter);
		setupModelSpinnerAdapter(modelSpinner);
		setupModelSpinerOnItemSelectedListener(modelSpinner);		
	}
	
	private void setupModelSpinnerAdapter (Spinner modelSpinner)
	{
		List<String> entries = new ArrayList<String>();
		String modelSpinnerDefaultEntry = getResources().getString(R.string.searchOrders_modelSpinnerDefaultEntry);
		entries.add(modelSpinnerDefaultEntry);
		for(VehicleModel model : models)
		{
			entries.add(model.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_search_orders_model_spinner, entries);
		modelSpinner.setAdapter(adapter);
	}
	
	
	private void setupModelSpinerOnItemSelectedListener (Spinner modelSpinner)
	{
		modelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(position != MODEL_SPINNER_DEFAULT_ENTRY_POSITION)
				{
					int index = position - MODEL_SPINNER_DEFAULT_ENTRY_OFFSET;
					selectedModel = models.get(index);
				}
				else
				{
					selectedModel = null;
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}
	
	public void start (View view)
	{
		Intent intent = new Intent(this, DisplaySearchResultsActivity.class);
		
		addFirstNameFilterExtra(intent);
		addLastNameFilterExtra(intent);
		addModelFilterExtra(intent);
		
		startActivity(intent);
	}
	
	private void addFirstNameFilterExtra (Intent intent)
	{
		TextView firstNameFilterView = (TextView) findViewById(R.id.searchOrders_firstNameFilter);
		String firstName = firstNameFilterView.getText().toString();
		if(firstName.length() != 0)
		{
			intent.putExtra(ExtraKeys.FILTER_BY_FIRST_NAME, true);
			intent.putExtra(ExtraKeys.FIRST_NAME, firstName);
		}
	}
	
	private void addLastNameFilterExtra (Intent intent)
	{
		TextView firstNameFilterView = (TextView) findViewById(R.id.searchOrders_lastNameFilter);
		String lastName = firstNameFilterView.getText().toString();
		if(lastName.length() != 0)
		{
			intent.putExtra(ExtraKeys.FILTER_BY_LAST_NAME, true);
			intent.putExtra(ExtraKeys.LAST_NAME, lastName);
		}
	}
	
	private void addModelFilterExtra (Intent intent)
	{
		if(selectedModel != null)
		{
			intent.putExtra(ExtraKeys.FILTER_BY_MODEL, true);
			intent.putExtra(ExtraKeys.MODEL_ID, selectedModel.getId());
		}
	}
}