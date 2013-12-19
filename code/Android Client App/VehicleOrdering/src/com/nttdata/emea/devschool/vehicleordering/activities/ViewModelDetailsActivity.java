package com.nttdata.emea.devschool.vehicleordering.activities;

import java.io.IOException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.utility.Convertor;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class ViewModelDetailsActivity extends Activity
{
	private DataSource dataSource;
	private VehicleModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_model_details);
		
		dataSource = DataSourceFactory.createDataSource();
		long modelId = getIntent().getExtras().getLong(ExtraKeys.MODEL_ID);
		model = dataSource.loadVehicleModel(modelId);
		
		fillLayoutWithContent();
	}
	
	private void fillLayoutWithContent ()
	{
		setModelName();
		setImage();
		setDescription();
		setPrice();
	}
	
	private void setModelName ()
	{
		TextView nameView = (TextView) findViewById(R.id.viewModelDetails_name);
		nameView.setText(model.getName());
	}
	
	private void setImage ()
	{
		new AsyncTask<Void, Void, Bitmap>()
		{
			@Override
			protected Bitmap doInBackground (Void... url)
			{
				try
				{
					URL imageUrl = new URL(model.getImageUrl());
					Bitmap result = BitmapFactory.decodeStream(imageUrl.openStream());
					return result;
				}
				catch (IOException e)
				{
					return null;
				}
			}
			
			@Override
			protected void onPostExecute (Bitmap bitmap)
			{
				if(bitmap != null)
				{
					ImageView imageView = (ImageView) findViewById(R.id.viewModelDetails_image);
					imageView.setImageBitmap(bitmap);
				}
			}
		}.execute();
	}
	
	private void setDescription ()
	{
		TextView descriptionView = (TextView) findViewById(R.id.viewModelDetails_description);
		descriptionView.setText(model.getDescription());
	}
	
	private void setPrice ()
	{
		TextView priceView = (TextView) findViewById(R.id.viewModelDetails_price);
		String priceTag = Convertor.euroCentFromLongToString(model.getPriceInEuroCent());
		priceView.setText(priceTag);
	}
	
	public void order (View view)
	{
		Intent intent = new Intent(this, OrderActivity.class);
		intent.putExtra(ExtraKeys.MODEL_ID, model.getId());
		startActivity(intent);
	}
}