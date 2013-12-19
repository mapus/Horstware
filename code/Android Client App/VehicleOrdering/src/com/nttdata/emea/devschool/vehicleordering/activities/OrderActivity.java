package com.nttdata.emea.devschool.vehicleordering.activities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class OrderActivity extends Activity
{
	private DataSource dataSource;
	private VehicleModel model;
	
	private TextView tvAmount;
	private TextView tvDeliveryDate;
	private TextView tvCustomerFirstName;
	private TextView tvCustomerLastName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		dataSource = DataSourceFactory.createDataSource();
		long modelId = getIntent().getExtras().getLong(ExtraKeys.MODEL_ID);
		model = dataSource.loadVehicleModel(modelId);
		
		tvAmount = (TextView) findViewById(R.id.order_amount);
		tvDeliveryDate = (TextView) findViewById(R.id.order_deliveryDate);
		tvCustomerFirstName = (TextView) findViewById(R.id.order_customerFirstName);
		tvCustomerLastName = (TextView) findViewById(R.id.order_customerLastName);
		
		setModelName();
	}
	
	private void setModelName ()
	{
		TextView modelNameView = (TextView) findViewById(R.id.order_modelName);
		modelNameView.setText(model.getName());
	}
	
	public void submit (View view)
	{
		if(validate() == true)
		{
			VehicleOrder order = createOrder();
			startViewOrderDetailsActivity(order);
		}
	}
	
	private boolean validate ()
	{
		boolean valid = true;
		
		if(!amountValid())
		{
			String validationMessage = getResources().getString(R.string.order_amountValidationMessage);
			tvAmount.setError(validationMessage);
			valid = false;
		}
		
		if(!deliveryDateValid())
		{
			String validationMessage = getResources().getString(R.string.order_deliveryDateValidationMessage);
			tvDeliveryDate.setError(validationMessage);
			valid = false;
		}
		
		return valid;
	}
	
	private VehicleOrder createOrder ()
	{
		String firstName = getFirstName();
		String lastName = getLastName();
		int amount = getAmount();
		Date deliveryDate = getDeliveryDate();
		
		Customer customer = dataSource.findCustomer(firstName, lastName);
		if(customer == null)
		{
			customer = dataSource.createCustomer(firstName, lastName);
		}
		VehicleOrder order = dataSource.createVehicleOrder(customer, model, amount, deliveryDate);
		
		return order;
	}
	
	private void startViewOrderDetailsActivity (VehicleOrder order)
	{
		Intent intent = new Intent(this, ViewOrderDetailsActivity.class);
		intent.putExtra(ExtraKeys.ORDER_ID, order.getId());
		intent.putExtra(ExtraKeys.IRREVERSIBLE, true);
		startActivity(intent);
	}
	
	private boolean amountValid ()
	{
		String amountString = tvAmount.getText().toString();
		try
		{
			Integer.valueOf(amountString);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	private boolean deliveryDateValid ()
	{
		String dateString = tvDeliveryDate.getText().toString();
		try
		{
			SimpleDateFormat.getDateInstance(DateFormat.SHORT).parse(dateString);
		}
		catch(ParseException e)
		{
			return false;
		}
		return true;
	}
	
	private String getFirstName ()
	{
		return tvCustomerFirstName.getText().toString();
	}
	
	private String getLastName ()
	{
		return tvCustomerLastName.getText().toString();
	}
	
	private int getAmount ()
	{
		String amountString = tvAmount.getText().toString();
		return Integer.parseInt(amountString);
	}
	
	private Date getDeliveryDate ()
	{
		String dateString = tvDeliveryDate.getText().toString();
		try
		{
			return SimpleDateFormat.getDateInstance(DateFormat.SHORT).parse(dateString);
		}
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}