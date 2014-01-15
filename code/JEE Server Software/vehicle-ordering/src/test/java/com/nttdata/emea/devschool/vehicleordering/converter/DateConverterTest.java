package com.nttdata.emea.devschool.vehicleordering.converter;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class DateConverterTest
{
	private static DateConverter converter;
	private static DateFactoryMock dateFactoryMock;
	
	@BeforeClass
	public static void setup()
	{
		converter = new DateConverter();
		dateFactoryMock = new DateFactoryMock();
		converter.setDateFactory(dateFactoryMock);
	}
	
	@Test
	public void testPattern()
	{
		Date outputDate = (Date)converter.getAsObject(null, null, "2000-12-30");
		assertTrue(checkDate(outputDate, 30, 12, 2000));		
	}
	
	@Test
	public void testDaysFromNow() throws ParseException
	{
		Date now = new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-01");
		dateFactoryMock.setNow(now);
		Date outputDate = (Date)converter.getAsObject(null, null, "+7");
		assertTrue(checkDate(outputDate, 8, 12, 2000));
	}
	
	private boolean checkDate(Date date, int day, int month, int year)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int dayFromDate = calendar.get(Calendar.DAY_OF_MONTH);
		int monthFromDate = calendar.get(Calendar.MONTH) + 1;
		int yearFromDate = calendar.get(Calendar.YEAR);
		
		if((day == dayFromDate) && (month == monthFromDate) && (year == yearFromDate))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
