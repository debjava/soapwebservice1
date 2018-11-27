import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class MyHttpHandler3 implements HttpHandler
{
	public void handle( HttpExchange httpExchange ) throws IOException 
	{
		String requestMethod = httpExchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET") || requestMethod.equalsIgnoreCase("POST")) 
		{
			System.out.println("Service Called.........");
			Headers responseHeaders = httpExchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/xml");
			httpExchange.sendResponseHeaders(200, 0);

			OutputStream responseBody = httpExchange.getResponseBody();
			String response = MyFileUtil.getFileContents("testdata/ajaxTest3.xml");
			responseBody.write(response.getBytes());
			responseBody.close();
		}
	} 

}
