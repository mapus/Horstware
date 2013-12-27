package com.nttdata.emea.devschool.vehicleordering.validator;

import javax.faces.validator.ValidatorException;

import org.junit.Test;

import com.nttdata.emea.devschool.vehicleordering.util.DateUtil;

public class WorkDayValidatorTest {
	
	@Test
	public void whenMondayIsValidatedThenValidationIsSuccessful(){
		new WorkDayValidator().validate(null, null, DateUtil.createDate(2012, 10, 22));
	}
	
	@Test(expected = ValidatorException.class)
	public void whenSaturdayIsValidatedThenValidatiorExceptionIsThrown(){
		new WorkDayValidator().validate(null, null, DateUtil.createDate(2012, 10, 27));
	}

}
