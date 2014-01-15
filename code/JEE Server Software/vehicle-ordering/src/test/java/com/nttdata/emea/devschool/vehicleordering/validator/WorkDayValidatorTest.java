package com.nttdata.emea.devschool.vehicleordering.validator;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.validator.ValidatorException;

import org.junit.BeforeClass;
import org.junit.Test;

public class WorkDayValidatorTest
{
	private final static SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	
	private static WorkDateValidator workDateValidator;
	
	@BeforeClass
	public static void setup ()
	{
		workDateValidator = new WorkDateValidator();
	}
	
	@Test
	public void testMonday() throws ParseException
	{
		Date aMonday = FORMAT.parse("04.11.2013");
		try
		{
			workDateValidator.validate(null, null, aMonday);
		}
		catch(ValidatorException e)
		{
			fail();
		}
	}
	
	@Test
	public void testSunday() throws ParseException
	{
		Date aSunday = FORMAT.parse("03.11.2013");
		try
		{
			workDateValidator.validate(null, null, aSunday);
			fail();
		}
		catch(ValidatorException expected) {}
	}
}
