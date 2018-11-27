package com.ddlab.rnd.httppost.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TestClientUsinghttPost
{
	private static String buildWebQuery(Map<String, String> parameters) throws Exception 
	{  

		StringBuilder sb = new StringBuilder();  

		for (Map.Entry<String, String> entry : parameters.entrySet()) {  

			String key = URLEncoder.encode(entry.getKey(), "UTF-8");  

			String value = URLEncoder.encode(entry.getValue(), "UTF-8");  

			sb.append(key).append("=").append(value).append("&");  

		}  

		return sb.toString().substring(0, sb.length() - 1);  

	}  

	public static void main(String[] args) throws Exception
	{
		String destination = "http://localhost:7001/zodiaccalculatorservice_axis2_doc/services/zodiaccalculatorservice";
		
		URL url = new URL(destination);  

		    

		        // make post mode connection  

		        URLConnection urlc = null;  

		        urlc = url.openConnection();  
		         urlc.setDoOutput(true);  

		         urlc.setAllowUserInteraction(false);  

		   

		         // send query  

		         PrintStream ps = new PrintStream(urlc.getOutputStream());  
		         
		         Map map = new HashMap();
		         map.put("day", "27");
		         map.put("month", "07");
		          map.put("year", "1978");

		         ps.print(buildWebQuery(map));  

		        ps.close();  

		    

		        // retrieve result  

		         BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF-8"));  

		         StringBuilder sb = new StringBuilder();  

		         String line;  

		         while ((line = br.readLine()) != null) {  

		             sb.append(line);  

		             sb.append("\n");  

		        }  

		         br.close();  

		        System.out.println(sb.toString());  

	}

}
