package com.iit.bsass.rnd.pos.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 3260071173504646776L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doPost Method of TransactionServlet ...");
		RequestUtil.showAllRequestParams(request);
		RequestUtil.showRequestParameterMap(request);
		RequestUtil.showMethodInfo(request);
		//Make a web service call...
		PrintWriter out = response.getWriter();
		out.println("Transaction Successful.");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of TransactionServlet ...");
		doPost(request, response);
	}


}
