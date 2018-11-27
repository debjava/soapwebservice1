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

public class CardServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6881569196850078304L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doPost Method of CardServlet ...");
		RequestUtil.showAllRequestParams(request);
		RequestUtil.showRequestParameterMap(request);
		RequestUtil.showMethodInfo(request);
//		msgid=100
//		pin=
//		name=
//		cardno=
//		cvv=
		PrintWriter out = response.getWriter();
		out.println("Card verification OK.");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of CardServlet ...");
		doPost(request, response);
	}

}
