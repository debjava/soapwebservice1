package com.ddlab.rnd.httpget.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class TestClientPlainGet 
{
	public static String makeHTTPCall( String url1 )
	{
		//Making HTTP call using input xml
		StringBuffer buf = new StringBuffer();
//		OutputStreamWriter out = null;
		try {
			
			//Net URL
			URL url = new URL(url1);
			//cretaing connection url
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			//Fetching response data
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				buf.append(decodedString);
				buf.append("\n");
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	public static void main(String[] args) 
	{
		String url = "http://localhost:7001/jerseyhelloworld/HelloWorld/query?name=deba&value=piku";//For Weblogic server 10.3.2
		System.out.println(makeHTTPCall(url));
	}

}
