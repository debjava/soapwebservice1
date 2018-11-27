import java.io.StringWriter;
import java.net.URL;

import org.apache.ws.commons.serialize.XMLWriter;
import org.apache.ws.commons.serialize.XMLWriterImpl;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientRequestImpl;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.serializer.XmlRpcWriter;

public class JavaClient 
{
	public static String getRequestXML( XmlRpcClient client , Object[] params ) throws Exception
	{
		String requestXml = null;
//		XmlRpcRequest request = new XmlRpcClientRequestImpl(config, "add", params);
		XmlRpcRequest request = new XmlRpcClientRequestImpl(client.getClientConfig(), "add", params);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriterImpl();
//		xw.setEncoding("US-ASCII");
		xw.setDeclarating(true);
		xw.setIndenting(false);
		xw.setWriter(sw);
		XmlRpcWriter xrw = new XmlRpcWriter((XmlRpcStreamConfig) client.getConfig(), xw, client.getTypeFactory());
		xrw.write(request);
		requestXml = sw.toString();
		return requestXml;
	}
	public static void main (String [] args) 
	{
		try {

			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc1/xmlrpc1"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			Object[] params = new Object[]{new Integer(40), new Integer(9)};

			Integer result = (Integer) client.execute("Calculator.add", params);
			System.out.println("Result :::"+result);
			System.out.println("request XML ::: \n"+getRequestXML(client, params));
			

		} catch (Exception exception)
		{
			System.err.println("JavaClient: " + exception);
		}
	}
}

