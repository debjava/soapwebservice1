package com.ddlab.rnd.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.println("You are logged in.");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.println("You are logged in.");
	}



	/* public void destroy() 
    {

    }

    public String getServletInfo() 
    {
        return null;
    }

    public ServletConfig getServletConfig() 
    {
        return null;
    }*/

}
