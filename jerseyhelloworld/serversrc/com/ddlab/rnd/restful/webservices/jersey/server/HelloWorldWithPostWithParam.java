package com.ddlab.rnd.restful.webservices.jersey.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("/HelloWorld/post")
public class HelloWorldWithPostWithParam 
{
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@POST
	@Produces("text/plain")
	public String sayHelloWithParam(@FormParam("name")String name,@FormParam("value")String value) 
	{
		System.out.println(" Inside the methods sayHello() using jersey");
		System.out.println("name----->"+name);
		System.out.println("value----->"+value);
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements())
		{
			String parName = (String) paramNames.nextElement();
			System.out.println(parName+"<->"+request.getParameter(parName));
		}
		return "Hello, Welcome to the world of RESTfull Webservices "+name+"<===>"+value;
	}
}
