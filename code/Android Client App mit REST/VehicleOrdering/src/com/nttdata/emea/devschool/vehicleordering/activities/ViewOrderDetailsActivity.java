package com.nttdata.emea.devschool.vehicleordering.activities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nttdata.emea.devschool.vehicleordering.R;
import com.nttdata.emea.devschool.vehicleordering.entities.Customer;
import com.nttdata.emea.devschool.vehicleordering.entities.Order;
import com.nttdata.emea.devschool.vehicleordering.network.OnRestResponse;
import com.nttdata.emea.devschool.vehicleordering.network.VehicleOrderingAPI;
import com.nttdata.emea.devschool.vehicleordering.utility.Convertor;
import com.nttdata.emea.devschool.vehicleordering.utility.ExtraKeys;
import com.nttdata.emea.devschool.vehicleordering.xml.impl.XMLVehicleOrderResponse;

public class ViewOrderDetailsActivity extends Activity
{
	private Order order;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order_details);
		
		long orderId = getIntent().getExtras().getLong(ExtraKeys.ORDER_ID);
		VehicleOrderingAPI api = VehicleOrderingAPI.getInstance();
		api.getOrder(orderId, new OnRestResponse()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					Serializer serializer = new Persister();
					XMLVehicleOrderResponse xmlVehicleOrderResponse = serializer.read(XMLVehicleOrderResponse.class, response);
					order = xmlVehicleOrderResponse.getOrders().get(0);
					fillLayoutWithContent();
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
	
	private void fillLayoutWithContent ()
	{
		setOrderNumber();
		setModelName();
		setCustomerName();
		setQuantity();
		setInvoiceTotal();
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
	
	private void setQuantity ()
	{
		TextView amountView = (TextView) findViewById(R.id.viewOrderDetails_quantity);
		String text = String.valueOf(order.getQuantity());
		amountView.setText(text);
	}
	
	private void setInvoiceTotal ()
	{
		TextView amountView = (TextView) findViewById(R.id.viewOrderDetails_invoiceTotal);
		String text = Convertor.euroCentFromLongToString(order.getInvoiceTotal());
		amountView.setText(text);
	}
	
	private void setDeliveryDate ()
	{
		TextView deliveryDateView = (TextView) findViewById(R.id.viewOrderDetails_deliveryDate);
		deliveryDateView.setText(order.date);
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