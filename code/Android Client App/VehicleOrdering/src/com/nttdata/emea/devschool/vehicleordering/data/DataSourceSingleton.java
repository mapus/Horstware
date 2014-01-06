package com.nttdata.emea.devschool.vehicleordering.data;

import com.nttdata.emea.devschool.vehicleordering.data.impl.DummyDataSource;

public class DataSourceSingleton
{
	private final static DataSource dataSource = new DummyDataSource();
	
	private DataSourceSingleton () {}
	
	public static DataSource getInstance ()
	{
		return dataSource;
	}
}