package com.ddlab.rnd.restfullservice.client;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**This is the testharness class to test the restfull service.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class TestRestfullZodiacCalculatorService 
{
	/**This method is used to test to obtain the name of the zodiac sign
	 * by making a Get request and response will be in plain text. 
	 * @param day of type int indicating the day
	 * @param month of type int indicating the month
	 * @param year of type int indicating the year
	 */
	public static void invokeGetText( String day , String month ,String year )
	{
		String endPointUrl = "http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/textget";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("day", day);
		params.add("month", month);
		params.add("year", year);

		String response = service.queryParams(params).get(String.class);
		System.out.println("Zodiac Sign using get method with plain text :::"+response);
	}
	
	/**This method is used to test to obtain the name of the zodiac sign
	 * by making a Get request and response will be in XML. 
	 * @param day of type int indicating the day
	 * @param month of type int indicating the month
	 * @param year of type int indicating the year
	 */
	public static void invokeGetXml( String day , String month ,String year )
	{
		String endPointUrl = "http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/xmlget";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("day", day);
		params.add("month", month);
		params.add("year", year);

		String response = service.queryParams(params).get(String.class);
		System.out.println("Zodiac Sign using get method with plain xml :::"+response);
	}
	
	/**This method is used to test to obtain the name of the zodiac sign
	 * by making a Post request and response will be in plain text. 
	 * @param day of type int indicating the day
	 * @param month of type int indicating the month
	 * @param year of type int indicating the year
	 */
	public static void invokePostText( String day , String month ,String year )
	{
		String endPointUrl = "http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/textpost";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);

		Form params = new Form();
		params.add("day", 27);
		params.add("month", 07);
		params.add("year", 1977);

		String response = service.post(String.class,params);
		System.out.println("Zodiac Sign using Post method with plain text :::"+response);
	}
	
	/**This method is used to test to obtain the name of the zodiac sign
	 * by making a Post request and response will be in xml. 
	 * @param day of type int indicating the day
	 * @param month of type int indicating the month
	 * @param year of type int indicating the year
	 */
	public static void invokePostXml( String day , String month ,String year )
	{
		String endPointUrl = "http://localhost:7001/restfullzodiaccalculator/zodiaccalculator/xmlpost";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);

		Form params = new Form();
		params.add("day", day);
		params.add("month", month);
		params.add("year", year);

		String response = service.post(String.class,params);
		System.out.println("Zodiac Sign using Post method with plain xml :::"+response);
	}

	public static void main(String[] args) 
	{
		invokeGetText("27", "07", "1977");
		invokeGetXml("27", "07", "1977");
		invokePostText("27", "07", "1977");
		invokePostXml("27", "07", "1977");
	}

}
