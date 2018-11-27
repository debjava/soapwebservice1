package com.ddlab.xmloverhttp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleXmlReceiverServlet extends HttpServlet
{
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	throws ServletException, IOException
	{
		System.out.println("Contents received at server side\n");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String contents = null;
		StringBuilder sb = new StringBuilder();
		while( (contents = reader.readLine()) != null )
		{
			sb.append(contents);
		}
		System.out.println("Request XML from Client\n"+sb.toString());
		ServletOutputStream outStream = response.getOutputStream();
		//		outStream.println("getContentLength: " +request.getContentLength());
		//		outStream.println("getContentType: " +request.getContentType());
		String responseXML = "<?xml version="+"\"1.0\""+"encoding="+"\"UTF-8\""+"?>"+"<Employee type="
			+ "\"permanent\""
			+ "><Name>Piku</Name><Id>3674</Id><Age>34</Age>" 
			+"<Salary>12345</Salary><Designation>Software Engineer</Designation>" 
			+"<Location>India</Location></Employee>";
		outStream.println(responseXML);
	}
}
