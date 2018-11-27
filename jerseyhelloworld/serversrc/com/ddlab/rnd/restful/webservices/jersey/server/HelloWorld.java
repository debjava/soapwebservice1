package com.ddlab.rnd.restful.webservices.jersey.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/HelloWorld")
public class HelloWorld 
{
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;

	@GET
	@Produces("text/plain")
	public String sayHello() 
	{
		System.out.println(" Inside the methods sayHello() using jersey");
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements())
		{
			String parName = (String) paramNames.nextElement();
			System.out.println(parName+"<->"+request.getParameter(parName));
		}
		return "Hello, Welcome to the world of RESTfull Webservices";
	}

}
