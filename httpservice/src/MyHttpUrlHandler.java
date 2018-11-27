import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class MyHttpUrlHandler implements HttpHandler
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
			
			File file = new File("images/IMG_0944.JPG");
			InputStream in = new FileInputStream(file);
			byte[] bb = new byte[(int)file.length()];
			in.read(bb);
			
			responseBody.write(	bb );
			in.close();
			
//			String response = "<Emp><Name>Deba</Name></Emp>";
//			String response = "<Name>Deba</Name>";
//			responseBody.write(response.getBytes());
			
			responseBody.close();
		}
	}
}