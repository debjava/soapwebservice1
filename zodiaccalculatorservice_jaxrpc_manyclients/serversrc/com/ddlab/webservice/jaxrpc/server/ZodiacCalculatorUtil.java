package com.ddlab.webservice.jaxrpc.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZodiacCalculatorUtil 
{
	public static boolean isDateValid( String dateStr , String dateFormat )
	{
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		Date utilDate;
		try 
		{
			utilDate = sdf.parse(dateStr);
			System.out.println("Date is valid---->"+utilDate);
			flag = true;
		}
		catch (ParseException e) 
		{
			System.out.println("Invalid date---->"+dateStr);
			flag = false;
		}
		return flag;
	}
	
	public static String getSign( int day , int month )
	{
		String value = null;
		try 
		{
			if (month == 1 && day >=20 || month == 2 && day <=18) {value = "AQUARIUS";}
			if (month == 1 && day > 31) {value = "Invalid Date";}
			if (month == 2 && day >=19 || month == 3 && day <=20) {value = "PISCES";}
			if (month == 2 && day > 29) {value = "Invalid Date";}
			if (month == 3 && day >=21 || month == 4 && day <=19) {value = "ARIES";}
			if (month == 3 && day > 31) {value = "Invalid Date";}
			if (month == 4 && day >=20 || month == 5 && day <=20) {value = "TAURUS";}
			if (month == 4 && day > 30) {value = "Invalid Date";}
			if (month == 5 && day >=21 || month == 6 && day <=21) {value = "GEMINI";}
			if (month == 5 && day > 31) {value = "Invalid Date";}
			if (month == 6 && day >=22 || month == 7 && day <=22) {value = "CANCER";}
			if (month == 6 && day > 30) {value = "Invalid Date";}
			if (month == 7 && day >=23 || month == 8 && day <=22) {value = "LEO";}
			if (month == 7 && day > 31) {value = "Invalid Date";}
			if (month == 8 && day >=23 || month == 9 && day <=22) {value = "VIRGO";}
			if (month == 8 && day > 31) {value = "Invalid Date";}
			if (month == 9 && day >=23 || month == 10 && day <=22) {value = "LIBRA";}
			if (month == 9 && day > 30) {value = "Invalid Date";}
			if (month == 10 && day >=23 || month == 11 && day <=21) {value = "SCORPIO";}
			if (month == 10 && day > 31) {value = "Invalid Date";}
			if (month == 11 && day >=22 || month == 12 && day <=21) {value = "SAGITTARIUS";}
			if (month == 11 && day > 30) {value = "Invalid Date";}
			if (month == 12 && day >=22 || month == 1 && day <=19) {value = "CAPRICORN";}
			if (month == 12 && day > 31) {value = "Invalid Date";}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return value;
	}


}
