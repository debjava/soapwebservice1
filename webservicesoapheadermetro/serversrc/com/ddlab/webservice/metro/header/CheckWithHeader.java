package com.ddlab.webservice.metro.header;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class CheckWithHeader 
{
	@WebMethod
	public String getHeaderInfo( @WebParam(header=true) String hValue , String mValue )
	{
		System.out.println("Web Service Header value :::"+hValue);
		System.out.println("You are calling getHeaderInfo() method");
		System.out.println("Web Service Method Param value :::"+mValue);
		return "Deba";
	}
}
