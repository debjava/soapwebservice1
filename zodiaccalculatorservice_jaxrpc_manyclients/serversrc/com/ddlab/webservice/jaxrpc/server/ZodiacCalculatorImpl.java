package com.ddlab.webservice.jaxrpc.server;

import java.rmi.RemoteException;

public class ZodiacCalculatorImpl implements ZodiacCalculator 
{
	public String getZodiacSign(int day, int month, int year) throws InvalidZodiacSignException,RemoteException
	{
		String dateStr = day+"-"+month+"-"+year;
		String dateFormat = "dd-MM-yyyy";
		String zodiacSign = null;
		try
		{
			if( ZodiacCalculatorUtil.isDateValid(dateStr, dateFormat) )
			{
				zodiacSign = ZodiacCalculatorUtil.getSign(day, month);
			}
			else
			{
				//Invalid Date
				throw new InvalidZodiacSignException("Since the date is not valid, Zodiac Sign can not be found");
			}
		}
		catch( InvalidZodiacSignException ize )
		{
			ize.printStackTrace();
			zodiacSign = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( NullPointerException npe )
		{
			throw new InvalidZodiacSignException("Since the date is not valid, Zodiac Sign can not be found");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return zodiacSign;
	}
	
}
