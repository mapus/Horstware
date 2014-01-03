package com.nttdata.emea.devschool.vehicleordering.validator;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.nttdata.emea.devschool.vehicleordering.validator.WorkDayValidator")
public class WorkDayValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Date date = (Date) value;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
			final FacesMessage message = new FacesMessage("Date must be weekday.");
			
			throw new ValidatorException(message);
		}
	}

}
