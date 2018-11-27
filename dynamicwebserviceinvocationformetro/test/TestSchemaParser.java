import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.wsif.util.WSIFUtils;

import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.BindingOperationImpl;
import com.ibm.wsdl.extensions.soap.SOAPBindingImpl;
import com.ibm.wsdl.extensions.soap.SOAPBodyImpl;

public class TestSchemaParser
{
	private static String getFormattedXMLString( String xmlContents )
	{
		String xmlString = null;
		try 
		{
			Source xmlInput = new StreamSource(new StringReader(xmlContents)); 
			StringWriter stringWriter = new StringWriter(); 
			StreamResult xmlOutput = new StreamResult(stringWriter); 
			Transformer transformer = TransformerFactory.newInstance().newTransformer();  
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(5)); 
			transformer.transform(xmlInput, xmlOutput); 
			xmlString = xmlOutput.getWriter().toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return xmlString;
	}

	public static boolean isPrimitive( String dataType )
	{
		boolean flag = false;
		if( dataType.equalsIgnoreCase("String"))
			flag = true;
		else if( dataType.equalsIgnoreCase("int"))
			flag = true;
		else if( dataType.equalsIgnoreCase("float"))
			flag = true;
		else if( dataType.equalsIgnoreCase("double"))
			flag = true;
		else if( dataType.equalsIgnoreCase("boolean"))
			flag = true;
		else if( dataType.equalsIgnoreCase("long"))
			flag = true;
		return flag;
	}


	public static void formSoapMessage( Map<String, String> inputParamMap , List<MyWSDLParser.ComplexObject> complexObjectList , SOAPElement bodyElement )
	{
		try 
		{
			Iterator itr = inputParamMap.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry<String,String> me = (Map.Entry<String,String>)itr.next();
				String inParamName = me.getKey();
				String dataType = me.getValue();
				//				System.out.println(inParamName+"-"+dataType);
				if( isPrimitive(dataType))
				{
					//					bodyElement.addChildElement(inParamName,"ns1").addTextNode("enter value");
					//					bodyElement.addChildElement(inParamName,"ns1").addTextNode("23");
					bodyElement.addChildElement(inParamName).addTextNode("1");//For jaxrpc
				}
				else
				{
					//It is a complex one
					SOAPElement master = bodyElement.addChildElement(inParamName,"ns1");
					formSoapMessage(MyWSDLParser.getObjectInfoMap(complexObjectList, dataType), complexObjectList, master);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static SOAPMessage getSoapMessage(String operation,
			String nameSpace, Map<String, String> inputParamMap,
			List<MyWSDLParser.ComplexObject> complexObjectList,int soapVersion)
	{
		SOAPMessage message = null;
		try 
		{
			MessageFactory messageFactory = null;
			if( soapVersion == 11 )
			{
				messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			}
			else
			{
				messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
			}
			message = messageFactory.createMessage();
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();

			body.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");//optional for jaxrpc

			SOAPElement bodyElement = body.addChildElement(
					envelope.createName(operation, "ns1", nameSpace));
			//Form the soap message
			formSoapMessage(inputParamMap, complexObjectList, bodyElement);
			message.saveChanges();
			//			System.out.println(getFormattedXMLString(envelope.toString()));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return message;
	}

	private static void invokeWebservice( SOAPMessage message , String destination )
	{
		try 
		{
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();
			SOAPMessage reply = connection.call(message, destination);

			System.out.println("\n\n");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			reply.writeTo(out);
			String soapResponseXml = new String( out.toByteArray());
			System.out.println("Soap Response XML \n"+ getFormattedXMLString(soapResponseXml));
			//			reply.writeTo(System.out);

			//			SOAPPart soapPart = reply.getSOAPPart();
			//			SOAPEnvelope envelope = soapPart.getEnvelope();
			//			SOAPBody body = envelope.getBody();
			//			System.out.println("\n Response \n"+getFormattedXMLString(envelope.toString()));
			//			System.out.println(getFormattedXMLString(envelope.toString()));
			connection.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		String wsdlLocation = "http://localhost:8080/orgservice/services/orgservice?wsdl";
		//		String wsdlLocation = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
		Definition def = WSIFUtils.readWSDL(null, wsdlLocation);
		String destination = MyWSDLParser.getDestination(def);
		System.out.println("Destination End Point :::"+destination);
		String wsdlNamespace = (String)def.getNamespaces().get("ns");
		//		System.out.println("Wsdl Namespace :::"+wsdlNamespace);
		//		System.out.println(def.getNamespaces());
		wsdlNamespace = wsdlNamespace == null ? MyWSDLParser.getNamespace(def) : wsdlNamespace ;
		String namespace = MyWSDLParser.getNamespace(def);
		System.out.println("Namespace :::"+namespace);

		int soapVersion = (String)def.getNamespaces().get("soap") == null ? 12 : 11;
		System.out.println("Soap Version ::::"+soapVersion);
		String serviceName = MyWSDLParser.getServiceName(def);
		System.out.println("Service Name ------>"+serviceName);

		String styleNuse = MyWSDLParser.getSoapBindingStyleNUse(def);
		System.out.println("Soap Binding Style :::"+styleNuse.split("[:]")[0]);
		System.out.println("Soap Binding Use :::"+styleNuse.split("[:]")[1]);
		String operationName = MyWSDLParser.getOperationsList(def).get(0);
		Map<String, String> inParamMap = MyWSDLParser.getInputParametersMap(def, operationName);
		if( inParamMap.size() == 0 )
		{
			/*
			 * With the assumption that WSDL provided is correct and the WSDl does not contain
			 * inline schema. It refers to external schema.
			 */
			inParamMap = MyWSDLParser.getInputParametersMap1(def, operationName);
		}
		//Display the list of Input parameters for a given operation
		Iterator itr = inParamMap.entrySet().iterator();
		while( itr.hasNext() )
		{
			Map.Entry<String, String> me = (Map.Entry<String, String>)itr.next();
			System.out.println(me.getKey()+" : "+me.getValue());
		}

		//		List<MyWSDLParser.ComplexObject> complexObjectList = MyWSDLParser.getComplexObectList(def);
		//		System.out.println("Complex Object List size :::"+complexObjectList.size());
		//		SOAPMessage message = getSoapMessage(operationName, wsdlNamespace, inParamMap, complexObjectList,soapVersion);
		//		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//		message.writeTo(out);
		//		String soapRequestXml = new String( out.toByteArray());
		//		System.out.println("Soap Request XML \n"+ getFormattedXMLString(soapRequestXml));
		//		//Invoke the web service
		//		invokeWebservice(message, destination);


	}
}
