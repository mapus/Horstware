package com.nttdata.emea.devschool.vehicleordering.utility;

public class Convertor
{
	private Convertor () {}
	
	public static String euroCentFromLongToString (long amount)
	{
		String euros = String.valueOf(amount/100);
		String cent = String.valueOf(amount%100);
		return euros + "," + cent + "€";
	}
}