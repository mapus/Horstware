package com.nttdata.emea.devschool.vehicleordering.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.nttdata.emea.devschool.vehicleordering.converter.DateConverter")
public class DateConverter implements Converter {
	
	private DateFactory dateFactory = new DateFactory();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(isRelativeDateFormat(value)){
			return parseRelativeDateFormat(value);
		}
		else{
			return parseAbsoluteDateFormat(value);
		}
	}

	private boolean isRelativeDateFormat(String value) {
		return value.startsWith("+");
	}

	private Object parseRelativeDateFormat(String value) {
		int daysDelta = Integer.parseInt(value.substring(1));
		
		Date now = dateFactory.now();
		
		return new Date(now.getTime() + daysDelta * 24l * 60l * 60l * 1000l);
	}

	private Object parseAbsoluteDateFormat(String value) {
		DateFormat dateFormat = getAbsoluteDateFormat();
		
		try {
			return dateFormat.parseObject(value);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private DateFormat getAbsoluteDateFormat() {
		return new SimpleDateFormat("dd.MM.yyyy");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return getAbsoluteDateFormat().format(value);
	}
	
	public void setDateFactory(DateFactory dateFactory) {
		this.dateFactory = dateFactory;
	}

}
