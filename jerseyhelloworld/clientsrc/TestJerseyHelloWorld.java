import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class TestJerseyHelloWorld 
{
	private static URI getBaseURI( String endPointUrl )
	{
		return UriBuilder.fromUri(endPointUrl).build();
	}

	public static void invoke1()
	{
//		String endPointUrl = "http://localhost:7001/jerseyhelloworld";
		String endPointUrl = "http://localhost:8080/jerseyhelloworld";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI(endPointUrl));
		// Fluent interfaces
		ClientResponse response = service.path("HelloWorld").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		System.out.println(response.getClient());
	}
	
	public static void invoke2()
	{
//		String endPointUrl = "http://localhost:7001/jerseyhelloworld/HelloWorld";
		String endPointUrl = "http://localhost:8080/jerseyhelloworld/HelloWorld";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);
		
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
	    params.add("foo", "X");
	    params.add("bar", "Y");

	    String response = service.queryParams(params).get(String.class);
	    System.out.println(response);
		
//		String response = service.accept(MediaType.TEXT_PLAIN).get(String.class);
//		System.out.println(response);
	}
	
	public static void invoke3()
	{
//		String endPointUrl = "http://localhost:7001/jerseyhelloworld/HelloWorld/query";
		String endPointUrl = "http://localhost:8080/jerseyhelloworld/HelloWorld/query";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);
		
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
	    params.add("name", "Deba");
	    params.add("value", "Y");

	    String response = service.queryParams(params).get(String.class);
	    System.out.println(response);
	}
	
	public static void invoke4()
	{
//		String endPointUrl = "http://localhost:7001/jerseyhelloworld/HelloWorld/post";
		String endPointUrl = "http://localhost:8080/jerseyhelloworld/HelloWorld/post";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);
		
		Form params = new Form();
	    params.add("name", "Deba");
	    params.add("value", "PIKU");
	    
	    String response = service.post(String.class,params);
	    System.out.println(response);
	}
	
	public static void invoke5()
	{
//		String endPointUrl = "http://localhost:7001/jerseyhelloworld/All/sayHelloWithPostFormParam";
		String endPointUrl = "http://localhost:8080/jerseyhelloworld/All/sayHelloWithPostFormParam";
		Client client = Client.create();
		WebResource service = client.resource(endPointUrl);
		
		Form params = new Form();
	    params.add("name", "Deba");
	    params.add("value", "PIKU");
	    
	    String response = service.post(String.class,params);
	    System.out.println(response);
	}


	public static void main(String[] args) throws Exception
	{
		//		String endPointUrl = "http://localhost:7001/jerseyhelloworld/HelloWorld?a=ba&sd=bcd&asdf=PIKU";
		
		invoke1();
		invoke2();
		invoke3();
		invoke4();
		invoke5();

	}
}
