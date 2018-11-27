package com.iit.bsass.rnd.pos.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = -5458235574230271836L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doPost Method of LoginServlet ...");
		RequestUtil.showAllRequestParams(request);
		RequestUtil.showRequestParameterMap(request);
		RequestUtil.showMethodInfo(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("/pospage.jsp");
		rd.forward(request, response);
		
//		PrintWriter out = response.getWriter();
//		out.println("You are logged in.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		System.out.println("doGet Method of LoginServlet ...");
		doPost(request, response);
	}

}
