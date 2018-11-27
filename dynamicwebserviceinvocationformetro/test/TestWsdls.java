import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;

import org.apache.wsif.util.WSIFUtils;


public class TestWsdls 
{
	private static void generateSkeletalMethodStructure( Definition def , String selectedOperationName )
	{
		System.out.println("Selected Operation Name :::"+selectedOperationName);
		String serviceName = MyWSDLParser.getServiceName(def);
		if( ! serviceName.equals(selectedOperationName) )
		{
			
				Map<String, String> inParamMap = MyWSDLParser.getInputParametersMap(def, selectedOperationName);
				if( inParamMap.size() == 0 )
				{
					/*
					 * With the assumption that WSDL provided is correct and the WSDl does not contain
					 * inline schema. It refers to external schema.
					 */
					inParamMap = MyWSDLParser.getInputParametersMap1(def, selectedOperationName);
				}
//				List<ComplexObject> complexObjectList = MyWSDLParser.getComplexObectList(def);
				Iterator<?> itr = inParamMap.entrySet().iterator();
				while( itr.hasNext() )
				{
					Map.Entry<String,String> me = (Map.Entry<String,String>)itr.next();
					String inParamName = me.getKey();
					String dataType = me.getValue();
					System.out.println(inParamName+"<->"+dataType);
//					if( MyWSDLParser.isPrimitive(me.getValue()))
//					{
////						AnalyzerPluginLogger.logInfo(me.getValue()+":::"+me.getKey());
//						TreeItem item1 = new TreeItem( inputParamTree,SWT.NULL );
//						item1.setText(inParamName+" : "+dataType);
//						item1.setExpanded(true);
//					}
//					else
//					{
//						//	AnalyzerPluginLogger.logInfo("######################## Complex Object #################################");
//						Map<?,?> complexObjectMap = MyWSDLParser.getObjectInfoMap(complexObjectList,me.getValue());
//						TreeItem subItem = new TreeItem( inputParamTree,SWT.NULL );
//						subItem.setText(inParamName+" : "+dataType);
//						showComplex(complexObjectMap, complexObjectList,subItem);
//						//							AnalyzerPluginLogger.logInfo("######################## Complex Object #################################");
//					}
					
					
				}
				String operationOutName = selectedOperationName+"Response";
				Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
				if( inParamMap.size() == 0 )
				{
					/*
					 * With the assumption that WSDL provided is correct and the WSDl does not contain
					 * inline schema. It refers to external schema.
					 */
					inParamMap = MyWSDLParser.getInputParametersMap1(def, operationOutName);
				}
				Iterator outItr = outParamMap.entrySet().iterator();
				String returnTypeName = "";
				while( outItr.hasNext() )
				{
					Map.Entry<String, String> me = (Map.Entry<String, String>)outItr.next();
					returnTypeName = me.getValue();
					System.out.println("Return Type :::"+returnTypeName);
					
					
				}
				
				
		}//End of if

	
		
		
	}
	public static void showOperationDetails( Definition def )
	{
		try
		{
			List<String> operationsList = MyWSDLParser.getOperationsList(def);
			for( String opName : operationsList )
			{
				StringBuilder methodSructure = new StringBuilder(opName);
				generateSkeletalMethodStructure(def, opName);
//				String operationName = opName ;
//				System.out.println("Web service Operation Name :::"+operationName);
//				System.out.println("=============="+operationName+"==============");
////				methodSructure.append(operationName+"(");
//				//Operation Input Parameters Details
//				Map<String, String> inParamMap = MyWSDLParser.getInputParametersMap(def, operationName);
//				if( inParamMap.size() == 0 )
//				{
//					/*
//					 * With the assumption that WSDL provided is correct and the WSDl does not contain
//					 * inline schema. It refers to external schema.
//					 */
//					inParamMap = MyWSDLParser.getInputParametersMap1(def, operationName);
//				}
//				//Display the list of Input parameters for a given operation
//				
//				methodSructure.append("(");
//				StringBuilder allParams = new StringBuilder("");
//				Iterator itr = inParamMap.entrySet().iterator();
//				while( itr.hasNext() )
//				{
//					Map.Entry<String, String> me = (Map.Entry<String, String>)itr.next();
//					String objectRef = me.getKey();
//					String objectTypeName = me.getValue();
//					System.out.println(objectRef+" : "+objectTypeName);
//					allParams.append(objectTypeName+" "+objectRef+",");
//					
//				}
//				methodSructure.append(allParams.substring(0,allParams.lastIndexOf(",")).toString());
//				methodSructure.append(")");
//				String operationOutName = operationName+"Response";
//				Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
//				if( inParamMap.size() == 0 )
//				{
//					/*
//					 * With the assumption that WSDL provided is correct and the WSDl does not contain
//					 * inline schema. It refers to external schema.
//					 */
//					inParamMap = MyWSDLParser.getInputParametersMap1(def, operationOutName);
//				}
//				Iterator outItr = outParamMap.entrySet().iterator();
//				String returnTypeName = "";
//				while( outItr.hasNext() )
//				{
//					Map.Entry<String, String> me = (Map.Entry<String, String>)outItr.next();
//					returnTypeName = me.getValue();
//					System.out.println("Return Type :::"+returnTypeName);
//					
//					methodSructure.insert(0, returnTypeName+" ");
//					
//				}
//				methodSructure.insert(0, "public ");
//				methodSructure.append("\n");
//				methodSructure.append("{");
//				methodSructure.append("\n\treturn "+returnTypeName+";");
//				methodSructure.append("\n}");
//				System.out.println("Final Method Structure\n"+methodSructure.toString());
				
//				String returnTypeName = (String)((Map.Entry<String, String>)outParamMap.entrySet().iterator().next().getValue());
				//Return type of web service method
//				showMethodRetunDetails(def, operationName);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
//				System.out.println("----------------Details of Complex Object---------------");
//				List<MyWSDLParser.ComplexObject> lco = MyWSDLParser.getComplexObectList(def);//getComplexReturnTypes(def, operationName);
//				for( int i = 0 ; i < lco.size() ; i++ )
//				{
//					MyWSDLParser.ComplexObject co = lco.get(i);
//					System.out.println("Co Object Name :::"+co.getObectName());
//					List<Map<String, String>> dataTypesList = co.getDataTypesList();
//					for( int j = 0 ; j < dataTypesList.size() ; j++ )
//					{
//						Map<String,String> dataMap = dataTypesList.get(j);
//						Iterator dataItr = dataMap.entrySet().iterator();
//						while( dataItr.hasNext() )
//						{
//							Map.Entry<String, String> me = (Map.Entry<String, String>)dataItr.next();
//							System.out.println(me.getKey()+" <-> "+me.getValue());
//						}
//					}
//				}
//				System.out.println("----------------Details of Complex Object---------------");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
//	List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();
	private static List<MyWSDLParser.ComplexObject> complexList = new LinkedList<MyWSDLParser.ComplexObject>();
	
	
	private static List<MyWSDLParser.ComplexObject> getComplexReturnTypes( Definition def , String operationName )
	{
		List<MyWSDLParser.ComplexObject> complexList = new LinkedList<MyWSDLParser.ComplexObject>();
		
		String operationOutName = operationName+"Response";
		Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
		Iterator itr1 = outParamMap.entrySet().iterator();
		String returnType = null;
		
		Map.Entry<String, String> me = (Map.Entry<String, String>)itr1.next();
		returnType = me.getValue();
		System.out.println("Return type:::"+returnType);
		System.out.println("-----------------Return type details----------------");
		
		List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();
		getRecursiveReturnTypeDetails11(def, returnType, complexList,null);
		return complexList;
	}
	
	public static List<MyWSDLParser.ComplexObject> getRecursiveReturnTypeDetails11(
			Definition def, String returnType,
			List<MyWSDLParser.ComplexObject> complexList,
			List<Map<String, String>> dataTypeMapList11)//List<Map<String, String>> dataTypeMapList
	{
		List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();
		if( MyWSDLParser.isPrimitive(returnType) )
		{
			MyWSDLParser.ComplexObject co = new MyWSDLParser.ComplexObject();
			co.setObectName(returnType);
			complexList.add(co);
		}
		else
		{
			MyWSDLParser.ComplexObject co = new MyWSDLParser.ComplexObject();
			Map<String, String> returnTypeMap = MyWSDLParser.getSchemaMap(def, returnType);
			Iterator retItr = returnTypeMap.entrySet().iterator();
			while( retItr.hasNext() )
			{
				Map.Entry<String, String> me = (Map.Entry<String, String>)retItr.next();
				String returnTypeName = me.getKey();
				String returnTypeObject = me.getValue();
				
				Map<String, String> dataTypeMap = new HashMap<String,String>();
				co.setObectName(returnType);
				dataTypeMap.put(returnTypeName, returnTypeObject);
				
//				System.out.println(returnTypeName+" : "+returnTypeObject);
				
				if( ! MyWSDLParser.isPrimitive(returnType) )
				{
//					getRecursiveReturnTypeDetails(def, returnTypeObject);
					getRecursiveReturnTypeDetails11(def, returnTypeObject, complexList, dataTypeMapList);
				}
				
				dataTypeMapList.add(dataTypeMap);
				co.setDataTypesList(dataTypeMapList);
			}
			complexList.add(co);
		}
		return complexList;
	}

	
	
	public static void showMethodRetunDetails( Definition def , String operationName )
	{
		String operationOutName = operationName+"Response";
		Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
		Iterator itr1 = outParamMap.entrySet().iterator();
		String returnType = null;
		
		Map.Entry<String, String> me = (Map.Entry<String, String>)itr1.next();
		returnType = me.getValue();
		System.out.println("Return type:::"+returnType);
		System.out.println("-----------------Return type details----------------");
		getRecursiveReturnTypeDetails(def, returnType);
	}
	
	public static void getRecursiveReturnTypeDetails( Definition def , String returnType )
	{
		System.out.println("return Type============>"+returnType);
		List<Map<String, String>> dataTypeMapList = new LinkedList<Map<String, String>>();
		if( MyWSDLParser.isPrimitive(returnType) )
		{
			
		}
		else
		{
			MyWSDLParser.ComplexObject co = new MyWSDLParser.ComplexObject();
			
			Map<String, String> returnTypeMap = MyWSDLParser.getSchemaMap(def, returnType);
			Iterator retItr = returnTypeMap.entrySet().iterator();
			while( retItr.hasNext() )
			{
				Map.Entry<String, String> me = (Map.Entry<String, String>)retItr.next();
				String returnTypeName = me.getKey();
				String returnTypeObject = me.getValue();
				
				Map<String, String> dataTypeMap = new HashMap<String,String>();
				co.setObectName(returnType);
				dataTypeMap.put(returnTypeName, returnTypeObject);
				
//				System.out.println(returnTypeName+" : "+returnTypeObject);
				
				if( ! MyWSDLParser.isPrimitive(returnTypeObject) )
				{
					getRecursiveReturnTypeDetails(def, returnTypeObject);
				}
				
				dataTypeMapList.add(dataTypeMap);
				co.setDataTypesList(dataTypeMapList);
			}
			complexList.add(co);
		}
	}
	
	public static void main(String[] args) 
	{
		try 
		{
//			String wsdlLocation = "WSDLS/sampleswsdls/zipcodeworldUS_webservice.wsdl";//OK
			String wsdlLocation = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";//OK
//			String wsdlLocation = "http://localhost:8080/orgservice/services/orgservice?wsdl";
//			String wsdlLocation = "WSDLS/cdyne_wsdls/Default.wsdl";//OK
			Definition def = WSIFUtils.readWSDL(null, wsdlLocation);
			String destination = MyWSDLParser.getDestination(def);
			System.out.println("Destination End Point :::"+destination);
			String wsdlNamespace = (String)def.getNamespaces().get("ns");
			wsdlNamespace = wsdlNamespace == null ? def.getTargetNamespace() : wsdlNamespace ;
			System.out.println("WSDL Namespace :::"+wsdlNamespace);

			int soapVersion = (String)def.getNamespaces().get("soap") == null ? 12 : 11;
			System.out.println("Soap Version ::::"+soapVersion);
			String serviceName = MyWSDLParser.getServiceName(def);
			System.out.println("Service Name ------>"+serviceName);

			String styleNuse = MyWSDLParser.getSoapBindingStyleNUse(def);
			System.out.println("Soap Binding Style :::"+styleNuse.split("[:]")[0]);
			System.out.println("Soap Binding Use :::"+styleNuse.split("[:]")[1]);
			
//			//List of web service operations
//			showOperationDetails(def);
			
			
			
			
			
			
			List<String> operationsList = MyWSDLParser.getOperationsList(def);
			for( String opName : operationsList )
			{
				String operationName =opName ;
				System.out.println("Web service Operation Name :::"+operationName);
				System.out.println("=============="+operationName+"==============");
				
				//Operation Input Parameters Details
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
				
				
				getReturnType(def, operationName);
				
				
				
				//Return type of web service method
//				String operationOutName = operationName+"Response";
//				Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap1(def, operationOutName);
//				Iterator itr1 = outParamMap.entrySet().iterator();
//				System.out.println("Return type:::");
//				String returnType = null;
//				while( itr1.hasNext() )
//				{
//					Map.Entry<String, String> me = (Map.Entry<String, String>)itr1.next();
//					System.out.println(me.getKey()+" : "+me.getValue());
//					returnType = me.getValue();
//				}
//				System.out.println("=============="+operationName+"==============");
//				
//				System.out.println("Return type details...........");
//				Map<String, String> returnTypeMap = MyWSDLParser.getSchemaMap(def, returnType);
//				Iterator retItr = returnTypeMap.entrySet().iterator();
//				while( retItr.hasNext() )
//				{
//					Map.Entry<String, String> me = (Map.Entry<String, String>)retItr.next();
//					System.out.println(me.getKey()+" : "+me.getValue());
//				}
			}
		}
		catch (WSDLException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void getReturnType( Definition def , String operationName )
	{
		String operationOutName = operationName+"Response";
		Map<String, String> outParamMap = MyWSDLParser.getInputParametersMap(def, operationOutName);
		if( outParamMap.size() == 0 )
		{
			/*
			 * With the assumption that WSDL provided is correct and the WSDl does not contain
			 * inline schema. It refers to external schema.
			 */
			outParamMap = MyWSDLParser.getInputParametersMap1(def, operationOutName);
		}
		Iterator itr1 = outParamMap.entrySet().iterator();
		System.out.println("Return type:::");
		String returnType = null;
		while( itr1.hasNext() )
		{
			Map.Entry<String, String> me = (Map.Entry<String, String>)itr1.next();
			System.out.println(me.getKey()+" : "+me.getValue());
			returnType = me.getValue();
		}
		
		System.out.println("Return type details..........."+returnType);
//		Map<String, String> returnTypeMap = MyWSDLParser.getSchemaMap(def, returnType);
//		Iterator retItr = returnTypeMap.entrySet().iterator();
//		while( retItr.hasNext() )
//		{
//			Map.Entry<String, String> me = (Map.Entry<String, String>)retItr.next();
//			System.out.println(me.getKey()+" : "+me.getValue());
//		}
	}

}
