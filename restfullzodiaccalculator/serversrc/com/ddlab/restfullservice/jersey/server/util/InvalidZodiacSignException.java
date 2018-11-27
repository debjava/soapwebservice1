package com.ddlab.restfullservice.jersey.server.util;

/**This is default exception class for handling invlid dates
 * for zodiac calculaton.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class InvalidZodiacSignException extends Exception 
{
	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 5563130246582947358L;

	/**Default Constructor
	 * @param msg of type String
	 * @author Debadatta Mishra(PIKU)
	 */
	public InvalidZodiacSignException( String msg ) 
	{
		super(msg);
	}
	
	/**
	 * Default constructor
	 * @author Debadatta Mishra(PIKU)
	 */
	public InvalidZodiacSignException() 
	{
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() 
	{
		return "Zodiac sign can not be found as the date is invalid, please provide a valid date";
	}
}
