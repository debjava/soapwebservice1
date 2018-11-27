import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TestClientUsingSoapXml 
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("testdata/SoapRequestXmlZodiacCalculatorJaxrpc.xml");
		InputStream in = new FileInputStream(file);
		int len = (int)file.length();
		byte[] datas = new byte[len];
		in.read(datas);
		SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = sfc.createConnection();
		
		MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		SOAPMessage sm = mf.createMessage();
		SOAPPart soapPart             = sm.getSOAPPart();   
		ByteArrayInputStream stream   = new ByteArrayInputStream(datas);   
		StreamSource source           = new StreamSource(stream);   

		soapPart.setContent(source);   
		sm.saveChanges();
		sm.writeTo(System.out);   

		URL endpoint = new URL("http://localhost:8080/zodiaccalculatorservice_axis2_manyclients/services/zodiaccalculatorservice?wsdl");
		SOAPMessage response = connection.call(sm, endpoint);
		response.setContentDescription("text/xml");//ContentType("text/xml");

		System.out.println(response.getContentDescription());

		SOAPBody soapBody = response.getSOAPBody();  
		System.out.println("----------- From Data -------------");
		System.out.println(soapBody);
		
		
		response.writeTo(System.out);   
		
		NodeList nl = soapBody.getChildNodes(); 
		for( int i = 0 ; i < nl.getLength() ; i++ )
		{
			Node n1 = nl.item(i);
			NodeList n2 = n1.getChildNodes();
			for( int j = 0 ; j < n2.getLength(); j++ )
			{
				System.out.println( n2.item(j).getFirstChild().getTextContent()  );
			}
		}
	}

}
