
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.xml.namespace.QName;

import org.apache.wsif.WSIFException;
import org.apache.wsif.WSIFPort;
import org.apache.wsif.WSIFService;
import org.apache.wsif.WSIFServiceFactory;
import org.apache.wsif.providers.soap.apacheaxis.WSIFDynamicProvider_ApacheAxis;
import org.apache.wsif.providers.soap.apacheaxis.WSIFPort_ApacheAxis;
import org.apache.wsif.schema.ComplexType;
import org.apache.wsif.schema.ElementType;
import org.apache.wsif.schema.Parser;
import org.apache.wsif.schema.SequenceElement;
import org.apache.wsif.util.WSIFUtils;
import org.apache.wsif.wsdl.extensions.java.JavaBinding;

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

	public static Map<String,String> getOperationInputParameters( Definition def , String operationName )
	{
		Map<String,String> operationInputParamMap = new LinkedHashMap<String, String>();

		try 
		{
			Service service = WSIFUtils.selectService(def, null, getServiceName(def));
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
				//				System.out.println("Key ---->"+qk.getNamespaceURI());
				break;
			}





			//			PortType portType = WSIFUtils.selectPortType(def, null, null);
			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);
			WSIFServiceFactory factory = WSIFServiceFactory.newInstance();
			WSIFService dpf = factory.getService(def, service, portType);
			WSIFPort port = null;
			String portName = null;


			//			if (portName == null)
			//				port = dpf.getPort();
			//			else
			//				port = dpf.getPort(portName);



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
			}




			List operationList = portType.getOperations();
			for (Iterator i = operationList.iterator(); i.hasNext();) 
			{
				Operation op = (Operation) i.next();
				if( operationName.equalsIgnoreCase(op.getName()))
				{
					//Get the input parameters list
					Input opInput = op.getInput();
					String[] inNames = new String[0];
					Class[] inTypes = new Class[0];
					if (opInput != null) 
					{
						List parts = opInput.getMessage().getOrderedParts(null);
						unWrapIfWrappedDocLit(parts, operationName, def);
						int count = parts.size();
						inNames = new String[count];
						inTypes = new Class[count];

						for (int j = 0; j < inNames.length; j++)
						{
							Part part = (Part) parts.get(j);
							inNames[j] = part.getName();
							//							System.out.println("object name ::: "+inNames[j]);
							QName partType = part.getTypeName();
							if (partType == null) 
							{
								partType = part.getElementName();
							}
							String dataType = partType.getLocalPart();
							//							System.out.println("datatype ::::"+dataType);
							operationInputParamMap.put(inNames[j], dataType);
						}
					}
				}
				else
				{
					continue;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return operationInputParamMap;
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




	//	public static void main(String[] args)
	//	{
	//
	//	}

}
