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
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class SearchOrdersActivity extends Activity
{
	private final static String MODEL_SPINNER_DEFAULT_ENTRY = "no filter";
	private final static int MODEL_SPINNER_DEFAULT_ENTRY_POSITION = 0;
	private final static int MODEL_SPINNER_DEFAULT_ENTRY_OFFSET = 1;
	
	private DataSource dataSource;
	private List<VehicleModel> models;
	private VehicleModel selectedModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_orders);
		
		dataSource = DataSourceFactory.createDataSource();
		models = dataSource.loadVehicleModels();
		selectedModel = null;
		
		setupModelSpinner();
	}
	
	private void setupModelSpinner()
	{
		Spinner modelSpinner = (Spinner) findViewById(R.id.searchOrders_modelFilter);
		configureModelSpinerAdapter(modelSpinner);
		configureModelSpinerOnItemSelectedListener(modelSpinner);		
	}
	
	private void configureModelSpinerAdapter (Spinner modelSpinner)
	{
		List<String> entries = new ArrayList<String>();
		entries.add(MODEL_SPINNER_DEFAULT_ENTRY);
		for(VehicleModel model : models)
		{
			entries.add(model.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_item, entries);
		modelSpinner.setAdapter(adapter);
	}
	
	
	private void configureModelSpinerOnItemSelectedListener (Spinner modelSpinner)
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
		
		intent.putExtra(ExtraKeys.FIRST_NAME_FILTER, getNameFilter(R.id.searchOrders_firstNameFilter));
		intent.putExtra(ExtraKeys.LAST_NAME_FILTER, getNameFilter(R.id.searchOrders_lastNameFilter));
		
		if(selectedModel != null)
		{
			intent.putExtra(ExtraKeys.FILTER_BY_MODEL, true);
			intent.putExtra(ExtraKeys.MODEL_ID, selectedModel.getId());
		}
		
		startActivity(intent);
	}
	
	private String getNameFilter (int textViewId)
	{
		TextView firstNameFilterView = (TextView) findViewById(textViewId);
		String text = firstNameFilterView.getText().toString();
		if(text.length() != 0)
		{
			return text;
		}
		else
		{
			return null;
		}
	}
}