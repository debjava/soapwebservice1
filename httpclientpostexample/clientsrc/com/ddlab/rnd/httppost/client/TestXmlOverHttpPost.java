package com.ddlab.rnd.httppost.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class TestXmlOverHttpPost 
{
	public static String makeHTTPCall(String xml, String url1)
	{
		//Making HTTP call using input xml
		StringBuffer buf = new StringBuffer();
		OutputStreamWriter out = null;
		try {
			
			//Net URL
			URL url = new URL(url1);
			//cretaing connection url
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			out = new OutputStreamWriter(connection.getOutputStream());
			//Writing xml on url
			out.write(xml);
			out.close();
			//Fetching response data
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				buf.append(decodedString);
				buf.append("\n");
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return buf.toString();
	}
	public static void main(String[] args) 
	{
//		String url = "http://localhost:8080/httpclientpostexample/HttpXMLServlet";//For Jboss server 4.2.0
		String url = "http://localhost:7001/httpclientpostexample/HttpXMLServlet";//For weblogic server 10.3.2
		System.out.println(makeHTTPCall("<test><name>Deba</name></test>", url));
	}

}
