package com.ddlab.webservice.axis2.server;

public class ZodiacCalculator 
{
	/**
	 * This is the webservice method.
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws InvalidZodiacSignException
	 */
	public String getZodiacSign(int day, int month, int year) throws InvalidZodiacSignException
	{
		String dateStr = day+"-"+month+"-"+year;
		String dateFormat = "dd-MM-yyyy";
		String zodiacSign = null;
		try
		{
			if( ZodiacCalculatorUtill.isDateValid(dateStr, dateFormat) )
			{
				zodiacSign = ZodiacCalculatorUtill.getSign(day, month);
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
