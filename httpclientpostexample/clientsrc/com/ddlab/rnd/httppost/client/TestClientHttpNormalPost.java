package com.ddlab.rnd.httppost.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TestClientHttpNormalPost
{
	public static String makeHTTPCall( String url1 )
	{
		//Making HTTP call using input xml
		StringBuffer buf = new StringBuffer();
//		OutputStreamWriter out = null;
		try {
			String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("piku", "UTF-8");
		    data += "&" + URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode("runu", "UTF-8");
			//Net URL
			URL url = new URL(url1);
			//cretaing connection url
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		    wr.write(data);
		    wr.flush();
			
			
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
	
	public static void main(String[] args) throws Exception
	{
		String url = "http://localhost:7001/jerseyhelloworld/HelloWorld/post";//For Weblogic server 10.3.2
		System.out.println(makeHTTPCall(url));
	}

}
