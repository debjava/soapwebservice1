import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class MyHttpServiceServer 
{
	public static void main(String[] args) 
	{
		try 
		{
			InetSocketAddress addr = new InetSocketAddress(3090);
		    HttpServer server = HttpServer.create(addr, 0);
		    server.createContext("/test/ajax", new MyHttpHandler1());
		    server.setExecutor(Executors.newCachedThreadPool());
		    server.start();
		    System.out.println("Server is listening on port 3090" );
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
