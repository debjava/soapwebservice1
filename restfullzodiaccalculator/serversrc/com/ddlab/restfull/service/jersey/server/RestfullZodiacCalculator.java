package com.ddlab.restfull.service.jersey.server;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ddlab.restfullservice.jersey.server.util.InvalidZodiacSignException;
import com.ddlab.restfullservice.jersey.server.util.ZodiacCalculatorUtil;

/**This class is used to provide a restfull service for obtaining the zodiac sign
 * based upon day,month and year. This restful service has been developed on the 
 * basis of jersey framework.
 * @author Debadatta Mishra(PIKU)
 *
 */
@Path("/zodiaccalculator")
public class RestfullZodiacCalculator 
{
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	/**This method is used to obtain the name of the zodiac sign based upon day,month and year.
	 * In this case Http GET method used to retrieve the zodiac sign. This method provides
	 * the name of zodiac sign as plain text
	 * In this case the url is <code>http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/textget?day=27&month=07&year=1977</code>
	 * @param day of type String indicating the day
	 * @param month of type String indicating the month
	 * @param year of type String indicating the year
	 * @author Debadatta Mishra(PIKU)
	 * @return String of zodiac sign
	 */
	@GET @Path("/textget")
	@Produces("text/plain")
	public String getZodiacSignTextGet( @QueryParam("day")String day , @QueryParam("month")String month , @QueryParam("year")String year ) 
	{
		//URL : http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/textget?day=27&month=07&year=1977
		String signResult = null;
		try
		{
			int birthDay = Integer.parseInt(day);
			int birthMonth = Integer.parseInt(month);
			int birthYear = Integer.parseInt(year);
			signResult = ZodiacCalculatorUtil.getZodiacSign(birthDay, birthMonth, birthYear);
		}
		catch (InvalidZodiacSignException e)
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( NumberFormatException nfe )
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( Exception ex )
		{
			signResult = "Error";
		}
		return signResult;
	}
	
	/**This method is used to obtain the name of the zodiac sign based upon day,month and year.
	 * In this case Http GET method used to retrieve the zodiac sign. This method provides
	 * the name of zodiac sign as xml message.
	 * In this case the url is <code>http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/xmlget?day=27&month=07&year=1977</code>
	 * @param day of type String indicating the day
	 * @param month of type String indicating the month
	 * @param year of type String indicating the year
	 * @author Debadatta Mishra(PIKU)
	 * @return String of zodiac sign
	 */
	@GET
	@Path("/xmlget")
	@Produces(MediaType.TEXT_XML)
	public String getZodiacSignXMLGet( @QueryParam("day")int day , @QueryParam("month")int month , @QueryParam("year")int year )
	{
		//URL : http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/xmlget?day=27&month=07&year=1977
		String signResult = null;
		try
		{
			signResult = ZodiacCalculatorUtil.getZodiacSign(day, month, year);
		}
		catch (InvalidZodiacSignException e)
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( Exception ex )
		{
			signResult = "Error";
		}
		return "<response><status>ok</status><result>"+signResult+"</result></response>";
	}
	
	/**This method is used to obtain the name of the zodiac sign based upon day,month and year.
	 * In this case Http POST method used to retrieve the zodiac sign. This method provides
	 * the name of zodiac sign as plain text message.
	 * In this case the url is <code>http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/textpost</code>
	 * @param day of type String indicating the day
	 * @param month of type String indicating the month
	 * @param year of type String indicating the year
	 * @author Debadatta Mishra(PIKU)
	 * @return String of zodiac sign
	 */
	@POST @Path("/textpost")
	@Produces(MediaType.TEXT_PLAIN)
	public String getZodiacSignTextPost( @FormParam("day")String day , @FormParam("month")String month , @FormParam("year")String year )
	{
		String signResult = null;
		try
		{
			int birthDay = Integer.parseInt(day);
			int birthMonth = Integer.parseInt(month);
			int birthYear = Integer.parseInt(year);
			signResult = ZodiacCalculatorUtil.getZodiacSign(birthDay, birthMonth, birthYear);
		}
		catch (InvalidZodiacSignException e)
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( NumberFormatException nfe )
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( Exception ex )
		{
			signResult = "Error";
		}
		return signResult;
	}
	
	/**This method is used to obtain the name of the zodiac sign based upon day,month and year.
	 * In this case Http POST method used to retrieve the zodiac sign. This method provides
	 * the name of zodiac sign as xml message.
	 * In this case the url is <code>http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/xmlpost</code>
	 * @param day of type String indicating the day
	 * @param month of type String indicating the month
	 * @param year of type String indicating the year
	 * @author Debadatta Mishra(PIKU)
	 * @return String of zodiac sign
	 */
	@POST
	@Path("/xmlpost")
	@Produces(MediaType.TEXT_XML)
	public String getZodiacSignXMLPost( @FormParam("day")String day , @FormParam("month")String month , @FormParam("year")String year )
	{
		String signResult = null;
		try
		{
			int birthDay = Integer.parseInt(day);
			int birthMonth = Integer.parseInt(month);
			int birthYear = Integer.parseInt(year);
			signResult = ZodiacCalculatorUtil.getZodiacSign(birthDay, birthMonth, birthYear);
		}
		catch (InvalidZodiacSignException e)
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( NumberFormatException nfe )
		{
			signResult = "Since the date is not valid, Zodiac Sign can not be found";
		}
		catch( Exception ex )
		{
			signResult = "Error";
		}
		return "<response><status>ok</status><result>"+signResult+"</result></response>";
	}

}
