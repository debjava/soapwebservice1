package com.iit.bsass.rnd.pos.server;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil 
{
	public static void showAllRequestParams( HttpServletRequest request )
	{
		System.out.println("************************ Request Paramerets **************************");
		Enumeration en = request.getParameterNames();
		while( en.hasMoreElements() )
		{
			try 
			{
				String requestParamName = (String)en.nextElement();
				String requestParamValue = request.getParameter(requestParamName);
				System.out.println(requestParamName+"---------------->"+requestParamValue);
			}
			catch (Exception e) 
			{
				continue;
			}
		}
		System.out.println("************************ Request Paramerets **************************");
	}

	public static void showMethodInfo( HttpServletRequest request )
	{
		System.out.println("########################## Request Method Details ##########################");
		Method[] methods = request.getClass().getMethods();
		for( int i = 0 ; i < methods.length ; i++ )
		{
			try 
			{
				String name = methods[i].getName();
				if( name.startsWith("get"))
				{
					System.out.println(name+" <----> "+methods[i].invoke( request ));
				}
			}
			catch (Exception e) 
			{
				continue;
			}
		}
		System.out.println("########################## Request Method Details ##########################");
	}
	
	public static void showRequestParameterMap( HttpServletRequest request )
	{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ Request Param Map Details @@@@@@@@@@@@@@@@@@@@@@@");
		Map paramMap = request.getParameterMap();
		Iterator itr = paramMap.entrySet().iterator();
		System.out.println("Key <----------> Value");
		while( itr.hasNext() )
		{
			try 
			{
				Map.Entry me = ( Map.Entry )itr.next();
				System.out.println(me.getKey()+"<--->"+me.getValue());
			}
			catch (Exception e) 
			{
				continue;
			}
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ Request Param Map Details @@@@@@@@@@@@@@@@@@@@@@@");
	}
}
