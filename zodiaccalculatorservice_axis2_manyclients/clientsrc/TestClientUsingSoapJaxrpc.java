import java.io.ByteArrayOutputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;


public class TestClientUsingSoapJaxrpc 
{
	public static void main(String[] args) throws Exception
	{
		String destination = "http://localhost:8080/zodiaccalculatorservice_axis2_manyclients/services/zodiaccalculatorservice?wsdl";
		String operation = "getZodiacSign";//Method name    
		String namespace = "http://zodiaccalculatorservice.com/xsd";//namespace
		String encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/";

		SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = sfc.createConnection();

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage sm = mf.createMessage();
		
		SOAPBody sb = sm.getSOAPBody();
		sb.setEncodingStyle(encodingStyle);
		
		QName bodyName = new QName(namespace, operation, "ns1");//ns1
		SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
		
		bodyElement.addChildElement("day").addTextNode("27");
		bodyElement.addChildElement("month").addTextNode("07");
		bodyElement.addChildElement("year").addTextNode("2010");

		System.out.println("Soap Request:\n");
		sm.writeTo(System.out);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		System.out.println("\nSoap Response:\n");
		URL endpoint = new URL(destination);
		SOAPMessage response = connection.call(sm, endpoint);
		response.writeTo(bos);
		System.out.println(new String(bos.toByteArray()));
	}


}