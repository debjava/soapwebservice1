package com.ddlab.rnd.httpget.client;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestClientHttpGet 
{
	public static void main(String[] args) throws Exception
	{
//		String url = "http://localhost:7001/jerseyhelloworld/HelloWorld/query?name=deba&value=piku";//For Weblogic server 10.3.2
		String url = "http://localhost:7001/jerseyhelloworld/HelloWorld/query";//For Weblogic server 10.3.2
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("name", "deba"));
		qparams.add(new BasicNameValuePair("value", "piku"));
		
		URI uri = URIUtils.createURI("http", "localhost", 7001, "jerseyhelloworld/HelloWorld/query", 
			    URLEncodedUtils.format(qparams, "UTF-8"), null);
		
		HttpGet httpget = new HttpGet(uri.toString());
		System.out.println(httpget.getURI());
		
		
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
        HttpEntity resEntity = response.getEntity();
        System.out.println("resEntity :::"+resEntity);
        
        if (resEntity != null) 
        {
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	resEntity.writeTo(baos);
        	System.out.println( new String( baos.toByteArray()));
            long len = resEntity.getContentLength();
            System.out.println(len);
        }
        httpclient.getConnectionManager().shutdown();        
	}

}
