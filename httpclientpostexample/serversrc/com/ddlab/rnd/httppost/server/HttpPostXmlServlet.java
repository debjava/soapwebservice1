package com.ddlab.rnd.httppost.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpPostXmlServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException ,IOException 
	{
		doPost(req, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException 
	{
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();

		Enumeration<?> paramNames = request.getParameterNames();
		String parName = null;
		boolean emptyEnum = false;
		if (! paramNames.hasMoreElements())
			emptyEnum = true;

		String xmlVersion = "\"1.0"+"\"";
		String xmlEncoding = "\"UTF-8"+"\"";
		out.println("<? xml version="+xmlVersion+" encoding="+xmlEncoding+"?>");
		out.println("<Emp><Name>Deba</Name><Age>23</Age><Salary>1234</Salary><Designation>Software Engineer</Designation></Emp>");
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
	}

}
