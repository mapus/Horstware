package com.nttdata.emea.devschool.vehicleordering.converter;

import java.util.Date;

public class DateFactoryMock extends DateFactory {
	
	private Date now;
	
	@Override
	public Date now() {
		return now;
	}
	
	public void setNow(Date now) {
		this.now = now;
	}

}
