package com.nttdata.emea.devschool.vehicleordering.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.emea.devschool.vehicleordering.util.DateUtil;

public class DateConverterTest {
	
	private DateFactoryMock dateFactoryMock;
	
	private DateConverter dateConverter;
	
	@Before
	public void setUp(){
		setUpDateFactoryMock();
		setUpDateConverter();
	}

	private void setUpDateFactoryMock() {
		dateFactoryMock = new DateFactoryMock();
		dateFactoryMock.setNow(DateUtil.createDate(2013, 5, 20));
	}
	
	private void setUpDateConverter() {
		dateConverter = new DateConverter();
		dateConverter.setDateFactory(dateFactoryMock);
	}

	@Test
	public void whenParsingYearMonthDayStringThenDateIsReturned(){
		Date expectedDate = DateUtil.createDate(2013, 5, 25);
		
		Object convertedDate = dateConverter.getAsObject(null, null, "2013-05-25");
		
		Assert.assertEquals(expectedDate, convertedDate);
	}
	
	@Test
	public void whenParsingPlusThreeStringThenDateInThreeDaysIsReturned(){
		Date inThreeDays = DateUtil.createDate(2013, 5, 23);
		Object convertedDate = dateConverter.getAsObject(null, null, "+3");
		
		Assert.assertEquals(inThreeDays, convertedDate);
	}
	
}
