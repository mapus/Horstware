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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class BrowseModelsActivity extends Activity
{
	private final static String TYPE_SPINNER_DEFAULT_ENTRY = "choose a vehicle type";
	private final static int TYPE_SPINNER_DEFAULT_ENTRY_POSITION = 0;
	private final static int TYPE_SPINNER_DEFAULT_ENTRY_OFFSET = 1;
	
	
	private DataSource dataSource;
	private List<VehicleType> types;
	private List<VehicleModel> models;
	
	private ArrayAdapter<String> modelListAdapter;
	private List<String> modelListEntries;
	
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_models);
		
		dataSource = DataSourceFactory.createDataSource();
		types = dataSource.loadVehicleTypes();
		models = new ArrayList<VehicleModel>();
		
		setupTypeSpinner();
		setupModelList();
	}
	
	private void setupTypeSpinner ()
	{
		Spinner typeSpinner = (Spinner) findViewById(R.id.browseModels_typeSpinner);
		configureTypeSpinnerAdapter(typeSpinner);
		configureTypeSpinnerOnItemSelectedListener(typeSpinner);
	}
	
	private void setupModelList ()
	{
		ListView modelList = (ListView) findViewById(R.id.browseModels_modelListView);
		configureModelListAdapter(modelList);
		configureModelListOnItemClickListener(modelList);
	}
	
	private void configureTypeSpinnerAdapter (Spinner typeSpinner)
	{
		List<String> entries = new ArrayList<String>();
		entries.add(TYPE_SPINNER_DEFAULT_ENTRY);
		for(VehicleType type : types)
		{
			entries.add(type.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_item, entries);
		typeSpinner.setAdapter(adapter);
	}
	
	private void configureTypeSpinnerOnItemSelectedListener (Spinner typeSpinner)
	{
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(position == TYPE_SPINNER_DEFAULT_ENTRY_POSITION)
				{
					models.clear();
				}
				else
				{
					int index = position - TYPE_SPINNER_DEFAULT_ENTRY_OFFSET;
					VehicleType filterByType = types.get(index);
					models = dataSource.loadVehicleModels(filterByType);
				}
				updateModelListEntries();
				modelListAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}
	
	private void configureModelListAdapter (ListView modelList)
	{
		modelListEntries = new ArrayList<String>();
		updateModelListEntries();
		modelListAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, modelListEntries);
		modelList.setAdapter(modelListAdapter);
	}
	
	private void configureModelListOnItemClickListener (ListView modelList)
	{
		final Context context = this;
		modelList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(context, ViewModelDetailsActivity.class);
				long modelId = models.get(position).getId();
				intent.putExtra(ExtraKeys.MODEL_ID, modelId);
				startActivity(intent);
			}
		});
	}
	
	private void updateModelListEntries ()
	{
		modelListEntries.clear();
		for(VehicleModel model : models)
		{
			modelListEntries.add(model.getName());
		}
	}
}