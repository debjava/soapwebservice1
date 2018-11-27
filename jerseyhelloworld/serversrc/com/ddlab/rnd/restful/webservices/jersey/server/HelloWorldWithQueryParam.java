package com.ddlab.rnd.restful.webservices.jersey.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("/HelloWorld/query")
public class HelloWorldWithQueryParam 
{
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@GET
	@Produces("text/plain")
	public String getSomeResponseValue( @QueryParam("name")String name , @QueryParam("value")String value)
	{
		System.out.println("Inside Method getSomeResponseValue()");
		System.out.println("name----->"+name);
		System.out.println("value----->"+value);
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements())
		{
			String parName = (String) paramNames.nextElement();
			System.out.println(parName+"<->"+request.getParameter(parName));
		}
		return "Request parameters are "+name+":"+value;
	}
}
