package com.ddlab.rnd.httppost.client;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestClientHttpPost
{
	public static void main(String[] args) throws Exception
	{
		String url = "http://localhost:7001/jerseyhelloworld/HelloWorld/post";//For Weblogic server 10.3.2
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("name", "piku"));
		formparams.add(new BasicNameValuePair("value", "runu"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(entity);
		
		HttpResponse response = httpclient.execute(httppost);
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
        HttpEntity resEntity = response.getEntity();
        System.out.println("resEntity :::"+resEntity);
        
        Header[] headers = response.getAllHeaders();
        for( int i = 0 ; i < headers.length ; i++ )
        {
        	System.out.println(headers[i].getName()+":::"+headers[i].getValue());
        	HeaderElement[] elmts = headers[i].getElements();
        	
        	for( int j = 0 ; j < elmts.length ; j++ )
        	{
        		System.out.println(elmts[j].getName()+"----"+elmts[j].getValue());
        	}
        	
        }
        
        
        
        
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
