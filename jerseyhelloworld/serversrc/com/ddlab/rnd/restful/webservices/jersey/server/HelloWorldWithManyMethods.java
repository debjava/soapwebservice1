package com.ddlab.rnd.restful.webservices.jersey.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/All")
public class HelloWorldWithManyMethods 
{
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@GET @Path("/sayHello")
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
	
	@GET @Path("/sayHelloWithPathParam/{name}/{value}")
	@Produces("text/plain")
	public String sayHelloWithPathParam(@PathParam("name")String name,@PathParam("value")String value) 
	{
		System.out.println(" Inside the methods sayHelloWithPathParam() using jersey");
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
	
	@POST @Path("/sayHelloWithPostFormParam")
	@Produces("text/plain")
	public String sayHelloWithFormParam(@FormParam("name")String name,@FormParam("value")String value) 
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
	
	@GET @Path("/getValueUsingQuery")
	@Produces("text/plain")
	public String getValueUsingQuery( @QueryParam("name")String name , @QueryParam("value")String value)
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
