package com.ddlab.rnd.httppost.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpPostTextServlet extends HttpServlet
{

	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException ,IOException 
	{
		doPost(req, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException 
	{
		response.setContentType("text/text");
		PrintWriter out = response.getWriter();

		Enumeration<?> paramNames = request.getParameterNames();
		String parName = null;
		boolean emptyEnum = false;
		if (! paramNames.hasMoreElements())
			emptyEnum = true;

		String textMsg = "This is a text message from server";
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
			textMsg+= parName + "---" + request.getParameter(parName);
		}
		out.println(textMsg);
	}

}
