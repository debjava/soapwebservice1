import java.util.List;

import javax.wsdl.Definition;
import javax.wsdl.extensions.UnknownExtensibilityElement;

import org.apache.wsif.WSIFMessage;
import org.apache.wsif.WSIFOperation;
import org.apache.wsif.WSIFPort;
import org.apache.wsif.WSIFService;
import org.apache.wsif.WSIFServiceFactory;
import org.apache.wsif.util.WSIFUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Tester11 
{
	public static void main(String[] args) throws Exception
	{
		String wsdlLocation = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
		Definition def = WSIFUtils.readWSDL(null, wsdlLocation);
		List l = def.getTypes().getExtensibilityElements();
//		System.out.println(l);
		for( int i = 0 ; i < l.size() ; i++ )
		{
			UnknownExtensibilityElement ue = (UnknownExtensibilityElement)l.get(i);
			
			Element schemaElmt = ue.getElement();
			NodeList nodesList = schemaElmt.getChildNodes();
			for( int j = 0 ; j < nodesList.getLength() ; j++ )
			{
//				System.out.println(nodesList.item(j).getFirstChild());
//				System.out.println(nodesList.item(j).getLocalName());
				System.out.println(nodesList.item(j).getNodeName());
			}
			
//			System.out.println(ue.getElement().getNodeName());
//			System.out.println(ue.getElement().getAttribute("schemaLocation"));
		}
		
//		 WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
//	        WSIFService service =
//	            factory.getService(
//	            		wsdlLocation,
//	                null,
//	                null,
//	                "http://server.metro.webservice.ddlab.com/",
//	                "ZodiacCalculatorImpl");
//	        
//	        WSIFPort port = service.getPort();
//
//	        // create the operation
//	        WSIFOperation operation = port.createOperation("getZodiacSign");
//
//	        // create the input, output and fault messages associated with this operation
//	        WSIFMessage input = operation.createInputMessage("ZodiacCalculatorService");
//	        System.out.println(input);
//	        WSIFMessage output = operation.createOutputMessage();
//	        WSIFMessage fault = operation.createFaultMessage();
	        
	        
	}
}
