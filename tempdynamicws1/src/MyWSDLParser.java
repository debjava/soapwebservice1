
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.Types;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.wsif.WSIFException;
import org.apache.wsif.WSIFPort;
import org.apache.wsif.WSIFService;
import org.apache.wsif.WSIFServiceFactory;
import org.apache.wsif.schema.ComplexType;
import org.apache.wsif.schema.ElementType;
import org.apache.wsif.schema.Parser;
import org.apache.wsif.schema.SequenceElement;
import org.apache.wsif.util.WSIFUtils;
import org.apache.wsif.wsdl.WSIFWSDLLocatorImpl;
import org.apache.wsif.wsdl.extensions.java.JavaBinding;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.wsdl.BindingImpl;
import com.ibm.wsdl.BindingOperationImpl;
import com.ibm.wsdl.PortImpl;
import com.ibm.wsdl.ServiceImpl;
import com.ibm.wsdl.extensions.soap.SOAPAddressImpl;
import com.ibm.wsdl.extensions.soap12.SOAP12AddressImpl;

public class MyWSDLParser 
{
	public static class ComplexObject
	{
		private String obectName = null;
		private List<Map<String,String>> dataTypesList = new LinkedList<Map<String,String>>();

		public String getObectName() {
			return obectName;
		}

		public void setObectName(String obectName) {
			this.obectName = obectName;
		}
		public List<Map<String, String>> getDataTypesList() {
			return dataTypesList;
		}
		public void setDataTypesList(List<Map<String, String>> dataTypesList) {
			this.dataTypesList = dataTypesList;
		}

	}

	private static void unWrapIfWrappedDocLit(List parts, String operationName, Definition def) throws WSIFException
	{
		Part p = WSIFUtils.getWrappedDocLiteralPart(parts, operationName);
		if (p != null) {
			List unWrappedParts = WSIFUtils.unWrapPart(p, def);
			parts.remove(p);
			parts.addAll(unWrappedParts);
		}
	}

	public static List<ComplexObject> getComplexObectList( Definition def )
	{
		List<ComplexObject> complexList = new LinkedList<ComplexObject>();
		try
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
			if( ll.size() == 0 )
			{
				String wsdlUrl = null;
				String xsdPath = getExternalSchemaLocation(def);
				Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(xsdPath, wsdlUrl, null));
			}
			for( int b = 0 ; b < ll.size() ; b++ )
			{
				List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();

				Object objType = ll.get(b);
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
				}
				if( objType instanceof ComplexType )
				{
					ComplexObject co = new ComplexObject();
					ComplexType ct = (ComplexType)ll.get(b);
					if( ct.getTypeName().getLocalPart() != null )
					{
//						System.out.println("Name ...>>>"+ct.getTypeName().getLocalPart());
						co.setObectName(ct.getTypeName().getLocalPart());
						SequenceElement[] se = ct.getSequenceElements();
						Map<String, String> dataTypeMap = new HashMap<String,String>();
						for( int a = 0 ; a < se.length ; a++ )
						{
							dataTypeMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
						}
						dataTypeMapList.add(dataTypeMap);
						co.setDataTypesList(dataTypeMapList);
					}
					complexList.add(co);
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return complexList;
	}
	
	public static Map<String, String> getInputParametersMap1( Definition def , String operationName )
	{
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		try 
		{
			List ll = new LinkedList();
//			Parser.getAllSchemaTypes(def, ll, null);
//			System.out.println("List size :::"+ll.size());
			
			String externalSchemaLocation = getExternalSchemaLocation(def);
			String wsdlLocation = null;//"http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
			Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(externalSchemaLocation, wsdlLocation, null));
			
			param : for( int b = 0 ; b < ll.size() ; b++ )
			{
				Object objType = ll.get(b);
				
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
//					System.out.println("Operation Name :::"+et.getTypeName().getLocalPart());
					
					if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
						List childrenList = et.getChildren();
						if( childrenList.size() != 0 )
						{
							if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
							{
								ComplexType ct = (ComplexType)et.getChildren().get(0);
								SequenceElement[] se = ct.getSequenceElements();
								for( int a = 0 ; a < se.length ; a++ )
								{
									paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
								}
								break;
							}
						}
					}
				}
				if( objType instanceof ComplexType )
				{
					ComplexType ct = (ComplexType)objType;
					if( ct.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
						SequenceElement[] se = ct.getSequenceElements();
						for( int a = 0 ; a < se.length ; a++ )
						{
							paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
						}
						break;
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paramMap;
	}
	
	
	public static Map<String, String> getSchemaMap( Definition def , String operationName )
	{
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		try 
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
			if( ll.size() == 0 )
			{
				String externalSchemaLocation = getExternalSchemaLocation(def);
				String wsdlLocation = null;
				Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(externalSchemaLocation, wsdlLocation, null));
			}
			param : for( int b = 0 ; b < ll.size() ; b++ )
			{
				Object objType = ll.get(b);
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
					if( et.getTypeName().getLocalPart().equals(operationName) )
					{
//						System.out.println("Size of the details :::"+et.getChildren().size());
//						System.out.println("It is complex :::"+et.isComplex());
						List childList = et.getChildren();
						for( int i = 0 ; i < childList.size() ; i++ )
						{
//							System.out.println("------>"+childList.get(i).getClass());
							ComplexType ct = (ComplexType)et.getChildren().get(i);
							SequenceElement[] se = ct.getSequenceElements();
							for( int a = 0 ; a < se.length ; a++ )
							{
								paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
//								System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
							}
//							break;
						}
						
					}
				}
				else if( objType instanceof ComplexType )
				{
					ComplexType ct = (ComplexType)objType;
//					System.out.println(ct.getSequenceElements().length);
//					System.out.println("Now here operation name :::"+operationName);
					if( ct.getTypeName().getLocalPart().equals(operationName) )
					{
//						System.out.println("Now here operation name :::"+operationName);
						SequenceElement[] se = ct.getSequenceElements();
						for( int a = 0 ; a < se.length ; a++ )
						{
							paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
//							System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
						}
						
//						System.out.println("Size of the details :::"+ct.getChildren().size());
//						System.out.println("It is complex :::"+ct.isComplex());
//						List childList = ct.getChildren();
//						for( int i = 0 ; i < childList.size() ; i++ )
//						{
//							System.out.println("------>"+childList.get(i).getClass());
//							ComplexType ct1 = (ComplexType)ct.getChildren().get(i);
//							SequenceElement[] se1 = ct1.getSequenceElements();
//							for( int a = 0 ; a < se1.length ; a++ )
//							{
//								paramMap.put(se1[a].getTypeName().getLocalPart(), se1[a].getElementType().getLocalPart());
////								System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
//							}
////							break;
//						}
						
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paramMap;
	}

	
	public static Map<String, String> getInputParametersMap( Definition def , String operationName )
	{
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		try 
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
//			if( ll.size() == 0 )
//			{
//				String externalSchemaLocation = getExternalSchemaLocation(def);
//				String wsdlLocation = null;
//				Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(externalSchemaLocation, wsdlLocation, null));
//			}
			param : for( int b = 0 ; b < ll.size() ; b++ )
			{
				Object objType = ll.get(b);
				
				
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
					if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
//						System.out.println("Object Type ::::"+et.getChildren().get(0) ) ;
//						System.out.println(et.getTypeName().);
						
						
						ComplexType ct = (ComplexType)et.getChildren().get(0);
						
						
						System.out.println("Type Name :::"+ct.getChildren());
//						
						
						SequenceElement[] se = ct.getSequenceElements();
						for( int a = 0 ; a < se.length ; a++ )
						{
							paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
//							System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
							
							
						}
						
						
						
						break;
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paramMap;
	}
	
	public static Map<String, String> getWSDLOutputParametersMap( Definition def , String operationName )
	{
		Map<String, String> inParamMap = MyWSDLParser.getOutputParametersMap(def, operationName);
		if( inParamMap.size() == 0 )
		{
			/*
			 * With the assumption that WSDL provided is correct and the WSDl does not contain
			 * inline schema. It refers to external schema.
			 */
			inParamMap = MyWSDLParser.getOutputParametersMap1(def, operationName);
		}
		return inParamMap;
	}
	
	public static Map<String, String> getOutputParametersMap( Definition def , String operationName )
	{
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		try 
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
//			if( ll.size() == 0 )
//			{
//				String externalSchemaLocation = getExternalSchemaLocation(def);
//				String wsdlLocation = null;
//				Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(externalSchemaLocation, wsdlLocation, null));
//			}
			param : for( int b = 0 ; b < ll.size() ; b++ )
			{
				Object objType = ll.get(b);
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
					if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
						ComplexType ct = (ComplexType)et.getChildren().get(0);
						SequenceElement[] se = ct.getSequenceElements();
						for( int a = 0 ; a < se.length ; a++ )
						{
							paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
//							System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
						}
						break;
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paramMap;
	}
	
	public static Map<String, String> getOutputParametersMap1( Definition def , String operationName )
	{
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		try 
		{
			List ll = new LinkedList();
//			Parser.getAllSchemaTypes(def, ll, null);
//			System.out.println("List size :::"+ll.size());
			
			String externalSchemaLocation = getExternalSchemaLocation(def);
			String wsdlLocation = null;//"http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
			Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl(externalSchemaLocation, wsdlLocation, null));
			
			param : for( int b = 0 ; b < ll.size() ; b++ )
			{
				Object objType = ll.get(b);
				
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
//					System.out.println("Operation Name :::"+et.getTypeName().getLocalPart());
					
					if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
						List childrenList = et.getChildren();
						if( childrenList.size() != 0 )
						{
							if( et.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
							{
								ComplexType ct = (ComplexType)et.getChildren().get(0);
								SequenceElement[] se = ct.getSequenceElements();
								for( int a = 0 ; a < se.length ; a++ )
								{
									paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
								}
								break;
							}
						}
					}
				}
				if( objType instanceof ComplexType )
				{
					ComplexType ct = (ComplexType)objType;
					if( ct.getTypeName().getLocalPart().equalsIgnoreCase(operationName) )
					{
						SequenceElement[] se = ct.getSequenceElements();
						for( int a = 0 ; a < se.length ; a++ )
						{
							paramMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
						}
						break;
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paramMap;
	}


	
	public static List<ComplexObject> getComplexObectList11( Definition def )
	{
		List<ComplexObject> complexList = new LinkedList<ComplexObject>();
		try
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
			for( int b = 0 ; b < ll.size() ; b++ )
			{
				List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();

				Object objType = ll.get(b);
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
					System.out.println(et.getTypeName().getLocalPart());
					ComplexType ct = (ComplexType)et.getChildren().get(0);
					
					SequenceElement[] se = ct.getSequenceElements();
					for( int a = 0 ; a < se.length ; a++ )
					{
						System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
					}
					
//					if( et.getTypeName().getLocalPart() != null )
//					{
//						SequenceElement[] se = et.getSequenceElements();
//					}
				}
				if( objType instanceof ComplexType )
				{
					ComplexObject co = new ComplexObject();
					ComplexType ct = (ComplexType)ll.get(b);
					if( ct.getTypeName().getLocalPart() != null )
					{
						co.setObectName(ct.getTypeName().getLocalPart());
						SequenceElement[] se = ct.getSequenceElements();
						Map<String, String> dataTypeMap = new HashMap<String,String>();
						for( int a = 0 ; a < se.length ; a++ )
						{
							System.out.println(se[a].getTypeName().getLocalPart()+" : "+se[a].getElementType().getLocalPart());
							dataTypeMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
						}
						dataTypeMapList.add(dataTypeMap);
						co.setDataTypesList(dataTypeMapList);
					}
					complexList.add(co);
				}

			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return complexList;
	}

	
	public static List<ComplexObject> getComplexObectList1( Definition def , String operationName )
	{
		List<ComplexObject> complexList = new LinkedList<ComplexObject>();
		try
		{
			List ll = new LinkedList();
			Parser.getAllSchemaTypes(def, ll, null);
//			Parser.getAllSchemaTypes(def, ll, new WSIFWSDLLocatorImpl("http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?xsd=1", "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL", null));
			
			System.out.println(ll.size());
			
			for( int b = 0 ; b < ll.size() ; b++ )
			{
				List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();

				Object objType = ll.get(b);
				if( objType instanceof ElementType )
				{
					ElementType et = (ElementType)objType;
				}
				if( objType instanceof ComplexType )
				{
					ComplexObject co = new ComplexObject();
					ComplexType ct = (ComplexType)ll.get(b);
					if( ct.getTypeName().getLocalPart() != null )
					{
						System.out.println("Name ...>>>"+ct.getTypeName().getLocalPart());
						co.setObectName(ct.getTypeName().getLocalPart());
						SequenceElement[] se = ct.getSequenceElements();
						Map<String, String> dataTypeMap = new HashMap<String,String>();
						for( int a = 0 ; a < se.length ; a++ )
						{
							dataTypeMap.put(se[a].getTypeName().getLocalPart(), se[a].getElementType().getLocalPart());
						}
						dataTypeMapList.add(dataTypeMap);
						co.setDataTypesList(dataTypeMapList);
					}
					complexList.add(co);
				}

			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return complexList;
	}


	public static Map<String,String> getObjectInfoMap( List<ComplexObject> complexObjectList , String objectName )
	{
		Map<String,String> objectInfoMap = new LinkedHashMap<String, String>();
		try
		{
			for( ComplexObject co : complexObjectList )
			{
				if( objectName.equalsIgnoreCase(co.getObectName()))
				{
					objectInfoMap = co.getDataTypesList().get(0);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objectInfoMap;
	}

	public static String getServiceName( Definition def )
	{
		String serviceName = null;
		try
		{
			Map map = def.getServices();
			String portName = null;
			String portTypeNS = null;
			String portTypeName = null;
			Iterator itr = map.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry)itr.next();
				ServiceImpl si = (ServiceImpl)me.getValue();
				//				System.out.println("----------->"+si.getQName());
				javax.xml.namespace.QName qname = si.getQName();
				serviceName = qname.getLocalPart();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return serviceName;
	}


	public static String getDestination( Definition def )
	{
		String destinationName = null;
		try 
		{
			Map map = def.getServices();
			Iterator itr = map.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry)itr.next();
				ServiceImpl si = (ServiceImpl)me.getValue();
				Map pm = si.getPorts();
				Iterator itr1 = pm.entrySet().iterator();
				while( itr1.hasNext() )
				{
					Map.Entry me1 = (Map.Entry)itr1.next();
					PortImpl pi = (PortImpl)me1.getValue();
					List ll = pi.getExtensibilityElements();
					for( int i = 0 ; i < ll.size() ; i++ )
					{
						//						System.out.println(ll.get(i).getClass());
						if( ll.get(i) instanceof SOAP12AddressImpl)
						{
							SOAP12AddressImpl si12 = (SOAP12AddressImpl)ll.get(i);
							destinationName = si12.getLocationURI();


							//							System.out.println("Soap12 location URI ----->"+si12.getLocationURI());
						}
						if( ll.get(i) instanceof SOAPAddressImpl )
						{
							SOAPAddressImpl si11 = (SOAPAddressImpl)ll.get(i);
							destinationName = si11.getLocationURI();
							//							System.out.println("Soap11 location URI ----->"+si11.getLocationURI());
						}

					}
				}

			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return destinationName;
	}

	//	public static String getDestination( String wsdlLocation , Definition def )
	//	{
	//		String destinationName = null;
	//		try 
	//		{
	//			WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
	//			
	//			String portTypeName = null;
	//			String portTypeNS = null;
	//
	//			Map pm =  def.getPortTypes();
	//			Iterator itr = pm.entrySet().iterator();
	//			while( itr.hasNext() )
	//			{
	//				Map.Entry me = (Map.Entry) itr.next();
	//				QName qk = (QName)me.getKey();
	//				portTypeName = qk.getLocalPart();
	//				portTypeNS = qk.getNamespaceURI();
	//				break;
	//			}
	//
	////			System.out.println("PortTypeName :::"+portTypeName);
	////			System.out.println("portTypeNS :::"+portTypeNS);
	//			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);
	//			
	//			
	//			Service service = WSIFUtils.selectService(def, null, getServiceName(def));
	//			
	////			WSIFService service1 = factory.getService(wsdlLocation, null, getServiceName(def),portTypeNS, portTypeName);
	//			WSIFService service1 = factory.getService(def, service, portType);//factory.getService(wsdlLocation, null, getServiceName(def),
	////					null, null);
	//			WSIFPort p = service1.getPort();
	//			// override port
	//			WSIFPort_ApacheAxis pA = (WSIFPort_ApacheAxis) p;
	//			destinationName =  pA.getEndPoint().toString();
	////			System.out.println("Destination :::" + destinationName);
	//		} 
	//		catch (Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//		return destinationName;
	//	}
	
	public static List<?> getWSDLOperationsList( Definition def )
	{
		List opList = new LinkedList();
		List<String> operationsList = new LinkedList<String>();
		try
		{
			Service service = WSIFUtils.selectService(def, null, getServiceName(def));
			String portName = null;

			String portTypeName = null;
			String portTypeNS = null;

			Map pm =  def.getPortTypes();
			Iterator itr = pm.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry) itr.next();
				QName qk = (QName)me.getKey();
				portTypeName = qk.getLocalPart();
				portTypeNS = qk.getNamespaceURI();
				break;
			}

			//			System.out.println("PortTypeName :::"+portTypeName);
			//			System.out.println("portTypeNS :::"+portTypeNS);
			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);


			WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
			WSIFService dpf = factory.getService(def, service, portType);
			WSIFPort port = null;
			//			if (portName == null)
			//				port = dpf.getPort();
			//			else
			//				port = dpf.getPort(portName);
			//For testing
			Iterator ports = service.getPorts().values().iterator();
			while (ports.hasNext()) 
			{
				Port port1 = (Port) ports.next();
				// check the binding
				Binding binding = port1.getBinding();


				if (binding instanceof JavaBinding)
					port = dpf.getPort(port1.getName());
				//			    else
				//			    	port = (WSIFPort) service.getPorts().values().iterator().next();

				//				System.out.println("Ext Size :::"+port1.getExtensibilityElements().get(0).getClass());
			}
			// no java binding available, just return the first port
			//			Port firstPort = (Port) service.getPorts().values().iterator().next();

			//For testing


			opList = portType.getOperations();
//			for (Iterator i = operationList.iterator(); i.hasNext();) 
//			{
//				Operation op = (Operation) i.next();
//				String operationName = op.getName();
//				//				System.out.println("Operation Name ::::"+operationName);
//				operationsList.add(operationName);
//			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
//		return operationsList;
		return opList;
	}

	public static List<String> getOperationsList( Definition def )
	{
		List<String> operationsList = new LinkedList<String>();
		try
		{
			Service service = WSIFUtils.selectService(def, null, getServiceName(def));
			String portName = null;

			String portTypeName = null;
			String portTypeNS = null;

			Map pm =  def.getPortTypes();
			Iterator itr = pm.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry me = (Map.Entry) itr.next();
				QName qk = (QName)me.getKey();
				portTypeName = qk.getLocalPart();
				portTypeNS = qk.getNamespaceURI();
				break;
			}

			//			System.out.println("PortTypeName :::"+portTypeName);
			//			System.out.println("portTypeNS :::"+portTypeNS);
			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);


			WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
			WSIFService dpf = factory.getService(def, service, portType);
			WSIFPort port = null;
			//			if (portName == null)
			//				port = dpf.getPort();
			//			else
			//				port = dpf.getPort(portName);
			//For testing
			Iterator ports = service.getPorts().values().iterator();
			while (ports.hasNext()) 
			{
				Port port1 = (Port) ports.next();
				// check the binding
				Binding binding = port1.getBinding();


				if (binding instanceof JavaBinding)
					port = dpf.getPort(port1.getName());
				//			    else
				//			    	port = (WSIFPort) service.getPorts().values().iterator().next();

				//				System.out.println("Ext Size :::"+port1.getExtensibilityElements().get(0).getClass());
			}
			// no java binding available, just return the first port
			//			Port firstPort = (Port) service.getPorts().values().iterator().next();

			//For testing


			List operationList = portType.getOperations();
			for (Iterator i = operationList.iterator(); i.hasNext();) 
			{
				Operation op = (Operation) i.next();
				String operationName = op.getName();
				//				System.out.println("Operation Name ::::"+operationName);
				operationsList.add(operationName);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return operationsList;
	}
	
	public static String getNamespace( Definition def )
	{
		String namespace = null ;
		try 
		{
			List typesList = def.getTypes().getExtensibilityElements();
			typesLoop :for( int k = 0 ; k < typesList.size() ; k++ )
			{
				UnknownExtensibilityElement extElmt = (UnknownExtensibilityElement)typesList.get(k);
				Element elmt = extElmt.getElement();
				NodeList nodeList = elmt.getChildNodes();
				for( int l = 0 ; l < nodeList.getLength() ; l++ )
				{
					Node node = nodeList.item(l);
					NamedNodeMap attibMap = node.getAttributes();
					if( attibMap != null )
					{
						namespace = attibMap.getNamedItem("namespace").getNodeValue();
//						System.out.println("Name :::"+attibMap.getNamedItem("namespace").getNodeValue());
						break typesLoop;
					}
				}
			}
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return namespace;
	}

	public static String getExternalSchemaLocation( Definition def )
	{
		String schemaLocation = null ;
		try 
		{
			Types types = def.getTypes();
			if( types != null )
			{
				List typesList = types.getExtensibilityElements();
				for( int k = 0 ; k < typesList.size() ; k++ )
				{
					UnknownExtensibilityElement extElmt = (UnknownExtensibilityElement)typesList.get(k);
					Element elmt = extElmt.getElement();
					NodeList nodeList = elmt.getChildNodes();
					for( int l = 0 ; l < nodeList.getLength() ; l++ )
					{
						Node node = nodeList.item(l);
						NamedNodeMap attibMap = node.getAttributes();
						if( attibMap != null )
						{
							if( attibMap.getNamedItem("schemaLocation") != null )
							{
								schemaLocation = attibMap.getNamedItem("schemaLocation").toString();
							}
							else
							{
								/*
								 * no schema location is found.
								 * It means that the method has no input parameters
								 */
							}
//							System.out.println("Schema Location :::"+schemaLocation);
						}
					}
				}
			}
			
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return schemaLocation;
	}
	
	public static String getSoapBindingStyleNUse( Definition def )
	{
		String style = null;
		String use = null;
		try 
		{
			Map bindingMap = def.getBindings();
			System.out.println("Map size :::"+bindingMap.size());
			Iterator itr = bindingMap.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry<?, ?> me = (Map.Entry<?, ?>)itr.next();
				BindingImpl bi = (BindingImpl)me.getValue();
//				System.out.println("bi--->"+bi.getBindingOperations().get(0).getClass());
//				System.out.println("Extn List size :::"+bi.getExtensibilityElements().size());
				Object extnObj = bi.getExtensibilityElements().get(0);
				if( extnObj instanceof javax.wsdl.extensions.soap.SOAPBinding )
				{
					javax.wsdl.extensions.soap.SOAPBinding sob = (javax.wsdl.extensions.soap.SOAPBinding)extnObj;
					style = sob.getStyle();
				}
				
//				System.out.println("------------------------------------------------");
				Element elmt = null;
				BindingOperation bo = (BindingOperation)bi.getBindingOperations().get(0);
				Object boObj = bo.getExtensibilityElements().get(0);
				if( boObj instanceof SOAPOperation )
				{
					SOAPOperation sop = (SOAPOperation)boObj;
					style = style == null ? sop.getStyle() : style;
				}
				else if( boObj  instanceof javax.wsdl.extensions.UnknownExtensibilityElement )
				{
					javax.wsdl.extensions.UnknownExtensibilityElement unExtn = (javax.wsdl.extensions.UnknownExtensibilityElement) boObj;
					elmt = unExtn.getElement();
					style = style == null ? elmt.getAttribute("style") : style;
				}
				else if( boObj instanceof SOAPOperation )
				{
					SOAPOperation sop = (SOAPOperation)boObj;
					style = style == null ? sop.getStyle() : style;
				}
				
				
//				NodeList nodesList = unExtn.getElement().getChildNodes();
//				System.out.println(nodesList.getLength());
//				System.out.println("lenght LLL :::"+unExtn.getElement().getFirstChild().getNodeName());
				
//				List extList = bi.getExtensibilityElements();
//				javax.wsdl.extensions.UnknownExtensibilityElement extn = (javax.wsdl.extensions.UnknownExtensibilityElement)extList.get(0);
//				elmt = extn.getElement();
//				System.out.println("total length :::"+elmt.getChildNodes().getLength());
				
				
				
				
				
				
//				System.out.println("Node name :::"+unExtn.getElement().getChildNodes().item(0).getNodeName());
//				List extnList = bo.getExtensibilityElements();
//				for( int i = 0 ; i < extnList.size(); i++ )
//				{
//					javax.wsdl.extensions.UnknownExtensibilityElement unExtn = (javax.wsdl.extensions.UnknownExtensibilityElement)extnList.get(i);
//					Element elmt = unExtn.getElement();
//					System.out.println(elmt.getAttribute("style"));
//					System.out.println(elmt.getNodeName());
////					System.out.println(extnList.get(i).getClass());
//				}
//				System.out.println("Operation Style :::"+bo.getExtensibilityElements());
//				System.out.println("Operation Style :::"+bo.getOperation().getDocumentationElement().getNodeName());
//				System.out.println("------------------------------------------------");
				
				BindingOperationImpl boi = (BindingOperationImpl)bi.getBindingOperations().get(0);
//				System.out.println("boi--->"+boi);
				Object obj = boi.getBindingInput().getExtensibilityElements().get(0);
				if( obj instanceof javax.wsdl.extensions.UnknownExtensibilityElement )
				{
					javax.wsdl.extensions.UnknownExtensibilityElement extn = (javax.wsdl.extensions.UnknownExtensibilityElement)obj;
					elmt = extn.getElement();
					use = elmt.getAttribute("use");
//					System.out.println("Node Name :::"+elmt.getNodeName());
				}
				else if( obj instanceof javax.wsdl.extensions.soap.SOAPBody )
				{
					javax.wsdl.extensions.soap.SOAPBody soapBody = (javax.wsdl.extensions.soap.SOAPBody)obj;
//					System.out.println("soapBody :::"+soapBody);
					use = soapBody.getUse();
				}
//				Object sbObj = bi.getExtensibilityElements().get(0);
//				System.out.println("sbObj :::"+sbObj);
//				if( sbObj instanceof javax.wsdl.extensions.soap.SOAPBinding )
//				{
//					javax.wsdl.extensions.soap.SOAPBinding sb = (javax.wsdl.extensions.soap.SOAPBinding)sbObj;
//					System.out.println("sb :::"+sb);
//					System.out.println("Soap Transport URI :::"+sb.getTransportURI());
//					style = sb.getStyle();
//					
//				}
				if( style == null && use == null )
				{
					continue;
				}
				else
				{
					break;
				}
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return style+":"+use;
	}

// TODO UCdetector: Remove unused code: 
// 	public static Map<String,String> getOperationInputParameters( Definition def , String operationName )
// 	{
// 		Map<String,String> operationInputParamMap = new LinkedHashMap<String, String>();
// 
// 		try 
// 		{
// 			Service service = WSIFUtils.selectService(def, null, getServiceName(def));
// 			String portTypeName = null;
// 			String portTypeNS = null;
// 
// 			Map pm =  def.getPortTypes();
// 			Iterator itr = pm.entrySet().iterator();
// 			while( itr.hasNext() )
// 			{
// 				Map.Entry me = (Map.Entry) itr.next();
// 				QName qk = (QName)me.getKey();
// 				portTypeName = qk.getLocalPart();
// 				portTypeNS = qk.getNamespaceURI();
// 				//				System.out.println("Key ---->"+qk.getNamespaceURI());
// 				break;
// 			}
// 
// 
// 
// 
// 
// 			//			PortType portType = WSIFUtils.selectPortType(def, null, null);
// 			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);
// 			WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
// 			WSIFService dpf = factory.getService(def, service, portType);
// 			WSIFPort port = null;
// 			String portName = null;
// 
// 
// 			//			if (portName == null)
// 			//				port = dpf.getPort();
// 			//			else
// 			//				port = dpf.getPort(portName);
// 
// 
// 
// 			Iterator ports = service.getPorts().values().iterator();
// 			while (ports.hasNext()) 
// 			{
// 				Port port1 = (Port) ports.next();
// 				// check the binding
// 				Binding binding = port1.getBinding();
// 				if (binding instanceof JavaBinding)
// 					port = dpf.getPort(port1.getName());
// 				//			    else
// 				//			    	port = (WSIFPort) service.getPorts().values().iterator().next();
// 			}
// 
// 
// 
// 
// 			List operationList = portType.getOperations();
// 			for (Iterator i = operationList.iterator(); i.hasNext();) 
// 			{
// 				Operation op = (Operation) i.next();
// 				if( operationName.equalsIgnoreCase(op.getName()))
// 				{
// 					//Get the input parameters list
// 					Input opInput = op.getInput();
// 					String[] inNames = new String[0];
// 					Class[] inTypes = new Class[0];
// 					if (opInput != null) 
// 					{
// 						List parts = opInput.getMessage().getOrderedParts(null);
// 
// 
// 						System.out.println( getExternalSchemaLocation(def));
// 
// 
// 
// 						unWrapIfWrappedDocLit(parts, operationName, def);
// 						int count = parts.size();
// 						inNames = new String[count];
// 						inTypes = new Class[count];
// 
// 						for (int j = 0; j < inNames.length; j++)
// 						{
// 							Part part = (Part) parts.get(j);
// 							inNames[j] = part.getName();
// 							//							System.out.println("object name ::: "+inNames[j]);
// 							QName partType = part.getTypeName();
// 							if (partType == null) 
// 							{
// 								partType = part.getElementName();
// 							}
// 							String dataType = partType.getLocalPart();
// 							//							System.out.println("datatype ::::"+dataType);
// 							operationInputParamMap.put(inNames[j], dataType);
// 						}
// 					}
// 				}
// 				else
// 				{
// 					continue;
// 				}
// 			}
// 		}
// 		catch (Exception e)
// 		{
// 			e.printStackTrace();
// 		}
// 		return operationInputParamMap;
// 	}

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

	public static String getJavaTypeSkeletalMethodStructure( Definition def , String selectedOperationName )
	{
		String skeletalMethod = null;
		StringBuilder methodSructure = new StringBuilder(selectedOperationName);
		
		Map<String, String> inParamMap = MyWSDLParser.getInputParametersMap(def, selectedOperationName);
		if( inParamMap.size() == 0 )
		{
			/*
			 * With the assumption that WSDL provided is correct and the WSDl does not contain
			 * inline schema. It refers to external schema.
			 */
			inParamMap = MyWSDLParser.getInputParametersMap1(def, selectedOperationName);
		}
		
		if( inParamMap.size() != 0 )
		{
			methodSructure.append("( ");
			StringBuilder allParams = new StringBuilder("");
			Iterator itr = inParamMap.entrySet().iterator();
			while( itr.hasNext() )
			{
				Map.Entry<String, String> me = (Map.Entry<String, String>)itr.next();
				String objectRef = me.getKey();
				String objectTypeName = me.getValue();
				allParams.append(objectTypeName+" "+objectRef+",");
			}
			methodSructure.append(allParams.substring(0,allParams.lastIndexOf(",")).toString());
			
			methodSructure.append(" )");
			String operationOutName = selectedOperationName+"Response";
			Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
			if( outParamMap.size() == 0 )
			{
				/*
				 * With the assumption that WSDL provided is correct and the WSDl does not contain
				 * inline schema. It refers to external schema.
				 */
				outParamMap = MyWSDLParser.getInputParametersMap1(def, operationOutName);
			}
			Iterator outItr = outParamMap.entrySet().iterator();
			String returnTypeName = "";
			while( outItr.hasNext() )
			{
				Map.Entry<String, String> me = (Map.Entry<String, String>)outItr.next();
				returnTypeName = me.getValue();
				System.out.println("Return Type :::"+returnTypeName);
				methodSructure.insert(0, returnTypeName+" ");
			}
			methodSructure.insert(0, "public ");
			methodSructure.append("\n");
			methodSructure.append("{");
			methodSructure.append("\n\treturn "+returnTypeName+";");
			methodSructure.append("\n}");
			skeletalMethod = methodSructure.toString();

		}
		
		
		return skeletalMethod;
	}
	
	public static Map<String,Map<String, String>> getOperationalInputParametersList( Definition def )
	{
		Map<String,Map<String, String>> wsdlOperationalInputParametersMap = new LinkedHashMap<String,Map<String,String>>();
		List<String> operationsList = getOperationsList(def);
		for( int i = 0 , n = operationsList.size() ; i < n ; i++ )
		{
			String operationName = operationsList.get(i);
			Map<String, String> inParamMap = MyWSDLParser.getInputParametersMap(def, operationName);
			if( inParamMap.size() == 0 )
			{
				/*
				 * With the assumption that WSDL provided is correct and the WSDl does not contain
				 * inline schema. It refers to external schema.
				 */
				inParamMap = MyWSDLParser.getInputParametersMap1(def, operationName);
			}
			wsdlOperationalInputParametersMap.put(operationName,inParamMap);
		}
		return wsdlOperationalInputParametersMap;
	}




}
