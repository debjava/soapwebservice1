package com.ddlab.rnd.httppost.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpXMLServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException ,IOException 
	{
		doPost(req, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException 
	{
		InputStream in = request.getInputStream();
		byte[] buffer = new byte[1024];
		in.read(buffer);
		in.close();
		System.out.println("In coming xml contents \n"+ new String( buffer ));

		Enumeration<?> paramNames = request.getParameterNames();
		String parName = null;
		boolean emptyEnum = false;
		if (! paramNames.hasMoreElements())
			emptyEnum = true;
		if (emptyEnum)
		{
			System.out.println("The request does not contain any request parameters");
		}
		else 
		{
			//do nothing
		}
		System.out.println("Request Parameter Name and Value\n");
		while(paramNames.hasMoreElements())
		{
			parName = (String) paramNames.nextElement();
			System.out.println(parName+"<->"+request.getParameter(parName));
		}
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		out.println("<Info><name>Some Info</name></Info>");
	}

}
