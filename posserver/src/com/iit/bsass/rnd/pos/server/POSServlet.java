package com.iit.bsass.rnd.pos.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class POSServlet extends HttpServlet
{
	private static final long serialVersionUID = 7127228992524229255L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doPost Method of POSServlet ...");
		RequestUtil.showAllRequestParams(request);
		RequestUtil.showRequestParameterMap(request);
		RequestUtil.showMethodInfo(request);
		PrintWriter out = response.getWriter();
		out.println("You are logged in.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of POSServlet ...");
		doPost(request, response);
	}
	
}
