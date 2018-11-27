package com.ddlab.rnd.httppost.client;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TestClientHttpXmlPost 
{
	public static void main(String[] args) throws Exception
	{
//		String url = "http://localhost:8080/httpclientpostexample/HttpXMLServlet";//For Jboss server 4.2
		String url = "http://localhost:7001/httpclientpostexample/HttpXMLServlet";//For Weblogic server 10.3.2
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		String xmlMsg = "<test><name>Deba</name></test>";
		
		StringEntity reqEntity = new StringEntity(xmlMsg);
		reqEntity.setContentType("text/xml");
		httppost.setEntity(reqEntity);
		
		HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();
        
        if (resEntity != null) 
        {
            long len = resEntity.getContentLength();
            if (len != -1 && len < 2048)
            {
                System.out.println(EntityUtils.toString(resEntity));
            }
            else 
            {
                // Stream content out
            }
        }
        httpclient.getConnectionManager().shutdown();        
	}

}
