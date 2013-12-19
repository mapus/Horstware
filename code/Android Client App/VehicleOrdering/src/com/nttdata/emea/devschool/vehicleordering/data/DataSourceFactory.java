package com.nttdata.emea.devschool.vehicleordering.data;

import com.nttdata.emea.devschool.vehicleordering.data.impl.DummyDataSource;

public class DataSourceFactory
{
	private DataSourceFactory () {}
	
	public static DataSource createDataSource ()
	{
		return new DummyDataSource();
	}
}