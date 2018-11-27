package com.ddlab.webservice.axis2.server;

public class InvalidZodiacSignException extends Exception 
{
	private static final long serialVersionUID = 5563130246582947358L;

	public InvalidZodiacSignException( String msg ) 
	{
		super(msg);
	}
	
	public InvalidZodiacSignException() 
	{
		super();
	}
	
	public String getMessage() 
	{
		return "Zodiac sign can not be found as the date is invalid, please provide a valid date";
	}
}
