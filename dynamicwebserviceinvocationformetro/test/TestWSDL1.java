import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;

import org.apache.wsif.util.WSIFUtils;


public class TestWSDL1 
{

	public static void main(String[] args) 
	{
		try 
		{
			String wsdlLocation = "https://www.uc.se/UCSoapWeb/services/ucOrders2/wsdl/UCOrders.wsdl";//OK
//			String wsdlLocation = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";//OK
//			String wsdlLocation = "http://localhost:8080/orgservice/services/orgservice?wsdl";
//			String wsdlLocation = "WSDLS/cdyne_wsdls/Default.wsdl";//OK
			Definition def = WSIFUtils.readWSDL(null, wsdlLocation);
			String destination = MyWSDLParser.getDestination(def);
			System.out.println("Destination End Point :::"+destination);
			//Testing Code
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
			
			PortType portType = WSIFUtils.selectPortType(def, portTypeNS, portTypeName);
			List opList = portType.getOperations();
			for (Iterator i = opList.iterator(); i.hasNext();) 
			{
				Operation op = (Operation) i.next();
				String operationName = op.getName();
				System.out.println("Operation Name ::::"+operationName);
				Output output = op.getOutput();
				System.out.println("Out Val :::"+output.getMessage().getQName().getLocalPart());
				
			}
			
			//Testing code
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
