package com.nttdata.emea.devschool.vehicleordering.activities;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

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
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleType;
import com.nttdata.emea.devschool.vehicleordering.network.OnRestResponse;
import com.nttdata.emea.devschool.vehicleordering.network.VehicleOrderingAPI;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLTypeResponse;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleModelResponse;

public class BrowseModelsActivity extends Activity
{
	private final static int TYPE_SPINNER_DEFAULT_ENTRY_POSITION = 0;
	private final static int TYPE_SPINNER_DEFAULT_ENTRY_OFFSET = 1;
	
	private List<VehicleType> types;
	private List<VehicleModel> models;
	
	private ArrayAdapter<String> modelListAdapter;
	private List<String> modelListEntries;
	
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_models);
		
		VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
		api.getTypes(new OnRestResponse()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					Serializer serializer = new Persister();
					XMLTypeResponse xmlTypeResponse = serializer.read(XMLTypeResponse.class, response);
					types = xmlTypeResponse.getTypes();
					models = new ArrayList<VehicleModel>();
					setupTypeSpinner();
					setupModelList();
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
	
	private void setupTypeSpinner ()
	{
		Spinner typeSpinner = (Spinner) findViewById(R.id.browseModels_typeSpinner);
		setupTypeSpinnerAdapter(typeSpinner);
		setupTypeSpinnerOnItemSelectedListener(typeSpinner);
	}
	
	private void setupModelList ()
	{
		ListView modelList = (ListView) findViewById(R.id.browseModels_modelListView);
		setupModelListAdapter(modelList);
		setupModelListOnItemClickListener(modelList);
	}
	
	private void setupTypeSpinnerAdapter (Spinner typeSpinner)
	{
		List<String> entries = new ArrayList<String>();
		String typeSpinnerDefaultEntry = getResources().getString(R.string.browseModels_typeSpinnerDefaultEntry);
		entries.add(typeSpinnerDefaultEntry);
		for(VehicleType type : types)
		{
			entries.add(type.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_browse_models_type_spinner, entries);
		typeSpinner.setAdapter(adapter);
	}
	
	private void setupTypeSpinnerOnItemSelectedListener (Spinner typeSpinner)
	{
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				updateModels(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}
	
	private void setupModelListAdapter (ListView modelList)
	{
		modelListEntries = new ArrayList<String>();
		modelListAdapter = new ArrayAdapter<String>(this, R.layout.item_browse_models_model_list, modelListEntries);
		modelList.setAdapter(modelListAdapter);
	}
	
	private void setupModelListOnItemClickListener (ListView modelList)
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
	
	private void updateModels (int typeSpinnerPosition)
	{
		if(typeSpinnerPosition == TYPE_SPINNER_DEFAULT_ENTRY_POSITION)
		{
			models.clear();
			updateModelList();
		}
		else
		{
			int index = typeSpinnerPosition - TYPE_SPINNER_DEFAULT_ENTRY_OFFSET;
			VehicleType filterByType = types.get(index);
			VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
			api.getModels(filterByType.getId(), new OnRestResponse()
			{
				@Override
				public void onResponse(String response)
				{
					try
					{
						Serializer serializer = new Persister();
						XMLVehicleModelResponse xmlModelResponse = serializer.read(XMLVehicleModelResponse.class, response);
						models = xmlModelResponse.getModels();
						updateModelList();
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
	
	private void updateModelList ()
	{
		modelListEntries.clear();
		for(VehicleModel model : models)
		{
			modelListEntries.add(model.getName());
		}
		modelListAdapter.notifyDataSetChanged();
	}
}