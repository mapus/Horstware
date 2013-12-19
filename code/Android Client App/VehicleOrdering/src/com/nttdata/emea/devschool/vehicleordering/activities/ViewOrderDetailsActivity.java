package com.nttdata.emea.devschool.vehicleordering.activities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.data.DataSource;
import com.nttdata.emea.devschool.vehicleordering.data.DataSourceFactory;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.VehicleOrder;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;

public class ViewOrderDetailsActivity extends Activity
{
	private DataSource dataSource;
	private VehicleOrder order;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order_details);
		
		dataSource = DataSourceFactory.createDataSource();
		long orderId = getIntent().getExtras().getLong(ExtraKeys.ORDER_ID);
		order = dataSource.loadVehicleOrder(orderId);
		
		fillLayoutWithContent();
	}
	
	private void fillLayoutWithContent ()
	{
		setOrderNumber();
		setModelName();
		setCustomerName();
		setAmount();
		setDeliveryDate();
	}
	
	private void setOrderNumber ()
	{
		TextView orderLabel = (TextView) findViewById(R.id.viewOrderDetails_orderLabel);
		String text = orderLabel.getText().toString();
		text += String.valueOf(order.getId());
		orderLabel.setText(text);
	}
	
	private void setModelName ()
	{
		TextView modelNameView = (TextView) findViewById(R.id.viewOrderDetails_modelName);
		String text = order.getModel().getName();
		modelNameView.setText(text);
	}
	
	private void setCustomerName ()
	{
		TextView customerNameView = (TextView) findViewById(R.id.viewOrderDetails_customerName);
		Customer customer = order.getCustomer();
		String text = customer.getFirstName() + " " + customer.getLastName();
		customerNameView.setText(text);
	}
	
	private void setAmount ()
	{
		TextView amountView = (TextView) findViewById(R.id.viewOrderDetails_amount);
		String text = String.valueOf(order.getAmount());
		amountView.setText(text);
	}
	
	private void setDeliveryDate ()
	{
		TextView deliveryDateView = (TextView) findViewById(R.id.viewOrderDetails_deliveryDate);
		String text = SimpleDateFormat.getDateInstance(DateFormat.SHORT).format(order.getDeliveryDate());
		deliveryDateView.setText(text);
	}
	
	@Override
	public void onBackPressed ()
	{
		boolean irreversible = getIntent().getExtras().getBoolean(ExtraKeys.IRREVERSIBLE);
		
		if(!irreversible)
		{
			super.onBackPressed();
		}
	}
}