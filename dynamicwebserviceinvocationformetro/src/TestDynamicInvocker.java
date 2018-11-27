
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
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

import org.apache.wsif.WSIFPort;
import org.apache.wsif.WSIFService;
import org.apache.wsif.WSIFServiceFactory;
import org.apache.wsif.providers.soap.apacheaxis.WSIFPort_ApacheAxis;
import org.apache.wsif.util.WSIFUtils;


import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.PortTypeImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;

public class TestDynamicInvocker 
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
//			MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
			message = messageFactory.createMessage();
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();
			
//			body.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");//optional for jaxrpc
			
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
			reply.writeTo(System.out);
			
			SOAPPart soapPart = reply.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();
			System.out.println("\n Response \n"+envelope.toString());
//			System.out.println(getFormattedXMLString(envelope.toString()));
			connection.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		/*
		 * employeeinfoservice - Complex - ok
		 * webcalculator - Simple - ok 
		 * arrayofprimitivedatatypeservice - Simple - ok
		 * bookservice - Complex - ok
		 * orgservice - Complex - ok
		 * empservice - Complex - ok
		 * primitivearrayservice - Simple - ok
		 * studentservice - Complex - ok
		 * 
		 */
		String destination = null;
		String wsdlLocation = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
		try 
		{
			Definition def = WSIFUtils.readWSDL(null, wsdlLocation);
			destination = MyWSDLParser.getDestination(def);
			
//			destination = "https://api.postalmethods.com/PostalWS.asmx";
			
			System.out.println("Destination Endpoint :::"+destination);
//			int soapVersion = 11;//http://schemas.xmlsoap.org/soap/envelope/
			int soapVersion = (String)def.getNamespaces().get("soap") == null ? 12 : 11;
			System.out.println("Soap Version ::::"+soapVersion);
			System.out.println(def.getNamespaces());
			
			String wsdlNamespace = (String)def.getNamespaces().get("ns");
			wsdlNamespace = wsdlNamespace == null ? def.getTargetNamespace() : wsdlNamespace;
			System.out.println( "WSDL namespace ----->"+wsdlNamespace );
			List<MyWSDLParser.ComplexObject> complexObjectList = MyWSDLParser.getComplexObectList(def);
			String serviceName = MyWSDLParser.getServiceName(def);
			System.out.println("Service Name ------>"+serviceName);
			String operationName = MyWSDLParser.getOperationsList(def).get(0);
			System.out.println("Operations ---->"+operationName);
			
			Map<String, String> inputParamMap = null;
			
			for( int i = 0 ; i < complexObjectList.size() ; i++ )
			{
				MyWSDLParser.ComplexObject co = complexObjectList.get(i);
				System.out.println("Object Name ::: "+co.getObectName());
				if( co.getObectName().equals("getZodiacSign"))
				{
					List<Map<String, String>> dataList = co.getDataTypesList();
					for( int j = 0 ; j < dataList.size() ; j++ )
					{
						Map<String,String> dataMap = dataList.get(j);
						inputParamMap = dataMap;
						
						Iterator itr = dataMap.entrySet().iterator();
						while( itr.hasNext() )
						{
							Map.Entry<String, String> me = (Map.Entry<String, String>)itr.next();
							System.out.println(me.getKey()+" : "+me.getValue());
						}
					}
					break;
				}
			}
			
//			Map<String, String> inputParamMap = MyWSDLParser.getOperationInputParameters(def, operationName);
			SOAPMessage message = getSoapMessage(operationName, wsdlNamespace, inputParamMap, complexObjectList,soapVersion);
			message.writeTo(System.out);
			System.out.println("\n\n\n");
			invokeWebservice(message, destination);
			
			
//			List<MyWSDLParser.ComplexObject> cl = MyWSDLParser.getComplexObectList(def);
			
			
			
			
			
			
			
			
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
