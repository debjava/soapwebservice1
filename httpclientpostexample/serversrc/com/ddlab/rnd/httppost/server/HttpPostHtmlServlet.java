package com.ddlab.rnd.httppost.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HttpPostHtmlServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException ,IOException 
	{
		doPost(req, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Enumeration paramNames = request.getParameterNames();
		String parName = null;
		boolean emptyEnum = false;
		if (! paramNames.hasMoreElements())
			emptyEnum = true;

		out.println("<html><head>");
		out.println("<title>Submitted Parameters</title></head><body>");
		if (emptyEnum)
		{
			System.out.println("The request does not contain any request parameters");
			out.println("<h2>Sorry, the request does not contain any parameters</h2>");
		}
		else 
		{
			out.println("<h2>Here are the submitted parameter values</h2>");
		}
		System.out.println("Request Parameter Name and Value\n");
		while(paramNames.hasMoreElements())
		{
			parName = (String) paramNames.nextElement();
			System.out.println(parName+"<->"+request.getParameter(parName));
			out.println("<strong>" + parName + "</strong> : " + request.getParameter(parName));
			out.println("<br/>");
		}
		out.println("</body></html>");
	}

}
