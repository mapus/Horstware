package com.nttdata.emea.devschool.vehicleordering.activities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceSingleton;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleModel;
import com.nttdata.emea.devschool.vehicleordering.exceptions.InvalidDeliveryDateException;
import com.nttdata.emea.devschool.vehicleordering.exceptions.InvalidNameException;
import com.nttdata.emea.devschool.vehicleordering.exceptions.InvalidQuantityException;
import com.nttdata.emea.devschool.vehicleordering.network.OnRestResponse;
import com.nttdata.emea.devschool.vehicleordering.network.VehicleOrderingAPI;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleModelResponse;

public class OrderActivity extends Activity
{
	private VehicleModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		long modelId = getIntent().getExtras().getLong(ExtraKeys.MODEL_ID);
		VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
		api.getModel(modelId, new OnRestResponse()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					Serializer serializer = new Persister();
					XMLVehicleModelResponse xmlModelResponse = serializer.read(XMLVehicleModelResponse.class, response);
					model = xmlModelResponse.getModels().get(0);
					setModelName();
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
	
	private void setModelName ()
	{
		TextView modelNameView = (TextView) findViewById(R.id.order_modelName);
		modelNameView.setText(model.getName());
	}
	
	public void submit (View view)
	{
		if(valid())
		{
			Order order = createOrder();
			startViewOrderDetailsActivity(order);
		}
	}
	
	private boolean valid ()
	{
		boolean result = true;
		
		try
		{
			getQuantity();
		}
		catch(InvalidQuantityException e)
		{
			showValidationMessage(R.id.order_quantity, R.string.order_quantityValidationMessage);
			result = false;
		}
		
		try
		{
			getDeliveryDate();
		}
		catch(InvalidDeliveryDateException e)
		{
			showValidationMessage(R.id.order_deliveryDate, R.string.order_deliveryDateValidationMessage);
			result = false;
		}
		
		try
		{
			getCustomerFirstName();
		}
		catch(InvalidNameException e)
		{
			showValidationMessage(R.id.order_customerFirstName, R.string.order_nameValidationMessage);
			result = false;
		}
		
		try
		{
			getCustomerLastName();
		}
		catch(InvalidNameException e)
		{
			showValidationMessage(R.id.order_customerLastName, R.string.order_nameValidationMessage);
			result = false;
		}
		
		return result;
	}
	
	private void showValidationMessage (int viewId, int msgId)
	{
		TextView tv = (TextView) findViewById(viewId);
		String msg = getResources().getString(msgId);
		tv.setError(msg);
	}
	
	private Order createOrder ()
	{
		String firstName, lastName;
		int quantity;
		Date deliveryDate;
		
		try
		{
			firstName = getCustomerFirstName();
			lastName = getCustomerLastName();
			quantity = getQuantity();
			deliveryDate = getDeliveryDate();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
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
		
		Order order = dataSource.createVehicleOrder(customer, model, quantity, deliveryDate);
		
		return order;
	}
	
	private void startViewOrderDetailsActivity (Order order)
	{
		Intent intent = new Intent(this, ViewOrderDetailsActivity.class);
		intent.putExtra(ExtraKeys.ORDER_ID, order.getId());
		intent.putExtra(ExtraKeys.IRREVERSIBLE, true);
		startActivity(intent);
	}
	
	private String getCustomerFirstName () throws InvalidNameException
	{
		TextView tv = (TextView) findViewById(R.id.order_customerFirstName);
		String firstName = tv.getText().toString();
		if(firstName.length() != 0)
		{
			return firstName;
		}
		else
		{
			throw new InvalidNameException();
		}
	}
	
	private String getCustomerLastName () throws InvalidNameException
	{
		TextView tv = (TextView) findViewById(R.id.order_customerLastName);
		String lastName = tv.getText().toString();
		if(lastName.length() != 0)
		{
			return lastName;
		}
		else
		{
			throw new InvalidNameException();
		}
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