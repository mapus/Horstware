package com.nttdata.emea.devschool.vehicleordering.util;

import java.util.Date;

public class DateUtil {
	
	@SuppressWarnings("deprecation")
	public static Date createDate(final int year, int month, int day){
		// crazy java date API forces '- 1900' and '- 1'
		return new Date(year - 1900, month - 1, day);
	}

}
