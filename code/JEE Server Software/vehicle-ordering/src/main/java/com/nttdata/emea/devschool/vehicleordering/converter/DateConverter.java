package com.nttdata.emea.devschool.vehicleordering.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.nttdata.emea.devschool.vehicleordering.converter.DateConverter")
public class DateConverter implements Converter
{
	private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final static String DAYS_FROM_NOW_PREFIX = "+";
	
	private DateFactory dateFactory = new DateFactory();
	
	public void setDateFactory (DateFactory dateFactory)
	{
		this.dateFactory = dateFactory;
	}
	
	public Object getAsObject (FacesContext context, UIComponent component, String value)
	{
		try
		{
			if(value.startsWith(DAYS_FROM_NOW_PREFIX))
			{
				int beginIndex = DAYS_FROM_NOW_PREFIX.length();
				String truncatedValue = value.substring(beginIndex);
				int daysFromNow = Integer.parseInt(truncatedValue);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateFactory.now());
				calendar.add(Calendar.DAY_OF_MONTH, daysFromNow);
				return calendar.getTime();
			}
			else
			{
					return FORMAT.parse(value);
			}
		}
		catch (ParseException | NumberFormatException e)
		{
			return null;
		}
	}

	public String getAsString (FacesContext context, UIComponent component, Object value)
	{
		return FORMAT.format((Date)value);
	}
}