package com.ddlab.ajax.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DropDownServerlet extends HttpServlet 
{
	private static final long serialVersionUID = -3899198620050524535L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		System.out.println("Servlet for Ajax Called...............");
		response.setContentType("text/xml");
		String responseData = "<Cars> 	<Name>Audi</Name> 	<Name>Mercedes</Name> 	<Name>BMW</Name> 	<Name>Maruti</Name> 	<Name>Vokswagen</Name> </Cars>	";
		PrintWriter out = response.getWriter();
		out.write(responseData);
		out.close();
	}
}
