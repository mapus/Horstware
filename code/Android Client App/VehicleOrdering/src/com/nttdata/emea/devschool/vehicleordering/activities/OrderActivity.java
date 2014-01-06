package com.nttdata.emea.devschool.vehicleordering.activities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceSingleton;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;
import com.nttdata.emea.devschool.vehicleordering.utility.InvalidDeliveryDateException;
import com.nttdata.emea.devschool.vehicleordering.utility.InvalidQuantityException;

public class OrderActivity extends Activity
{
	private VehicleModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		long modelId = getIntent().getExtras().getLong(ExtraKeys.MODEL_ID);
		model = DataSourceSingleton.getInstance().retrieveVehicleModel(modelId);
		
		setModelName();
	}
	
	private void setModelName ()
	{
		TextView modelNameView = (TextView) findViewById(R.id.order_modelName);
		modelNameView.setText(model.getName());
	}
	
	public void submit (View view)
	{
		try
		{
			VehicleOrder order = createOrder();
			startViewOrderDetailsActivity(order);
		}
		catch (InvalidQuantityException e)
		{
			String msg = getResources().getString(R.string.order_quantityValidationMessage);
			TextView tv = (TextView) findViewById(R.id.order_quantity);
			tv.setError(msg);
		}
		catch (InvalidDeliveryDateException e)
		{
			String msg = getResources().getString(R.string.order_deliveryDateValidationMessage);
			TextView tv = (TextView) findViewById(R.id.order_deliveryDate);
			tv.setError(msg);
		}
	}
	
	private VehicleOrder createOrder () throws InvalidQuantityException, InvalidDeliveryDateException
	{
		String firstName = getCustomerFirstName();
		String lastName = getCustomerLastName();
		int quantity = getQuantity();
		Date deliveryDate = getDeliveryDate();
		
		DataSource dataSource = DataSourceSingleton.getInstance();
		
		Customer customer;
		List<Customer> foundCustomers = dataSource.findCustomers(firstName, lastName);
		if(foundCustomers.size() != 0)
		{
			customer = foundCustomers.get(0);
		}
		else
		{
			customer = dataSource.createCustomer(firstName, lastName);
		}
		
		VehicleOrder order = dataSource.createVehicleOrder(customer, model, quantity, deliveryDate);
		
		return order;
	}
	
	private void startViewOrderDetailsActivity (VehicleOrder order)
	{
		Intent intent = new Intent(this, ViewOrderDetailsActivity.class);
		intent.putExtra(ExtraKeys.ORDER_ID, order.getId());
		intent.putExtra(ExtraKeys.IRREVERSIBLE, true);
		startActivity(intent);
	}
	
	private String getCustomerFirstName ()
	{
		TextView tv = (TextView) findViewById(R.id.order_customerFirstName);
		return tv.getText().toString();
	}
	
	private String getCustomerLastName ()
	{
		TextView tv = (TextView) findViewById(R.id.order_customerLastName);
		return tv.getText().toString();
	}
	
	private int getQuantity () throws InvalidQuantityException
	{
		try
		{
			TextView tv = (TextView) findViewById(R.id.order_quantity);
			String s = tv.getText().toString();
			return Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			throw new InvalidQuantityException();
		}
	}
	
	private Date getDeliveryDate () throws InvalidDeliveryDateException
	{
		try
		{
			TextView tv = (TextView) findViewById(R.id.order_deliveryDate);
			String s = tv.getText().toString();
			return SimpleDateFormat.getDateInstance(DateFormat.SHORT).parse(s);
		}
		catch(ParseException e)
		{
			throw new InvalidDeliveryDateException();
		}
	}
}