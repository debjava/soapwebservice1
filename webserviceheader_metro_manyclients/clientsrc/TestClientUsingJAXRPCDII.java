import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.encoding.XMLType;


public class TestClientUsingJAXRPCDII 
{
	private static String targetEndPoint = "http://localhost:8080/zodiaccalculatorservice_metro_manyclients/ZodiacCalculatorService?WSDL";
	private static String serviceName = "ZodiacCalculatorImplService";//zodiaccalculatorservice
    private static String qnamePort = "ZodiacCalculatorImplPort";
    private static String namespace = "http://server.metro.webservice.ddlab.com/";//http://zodiaccalculatorservice.com/xsd
//    private static String NS_XSD = "http://www.w3.org/2001/XMLSchema";//xml:xs in wsdl,http://schemas.xmlsoap.org/soap/encoding/,http://www.w3.org/2001/XMLSchema
    private static String operationName = "getZodiacSign";
    private static String encodingStyle = "http://schemas.xmlsoap.org/wsdl/soap/";//http://schemas.xmlsoap.org/soap/encoding/
    
    public static void main(String[] args) throws Exception
	{
		
		ServiceFactory factory = ServiceFactory.newInstance();
        Service service = factory.createService(new QName(serviceName));
        QName port = new QName(qnamePort);
        Call call = service.createCall(port);
//        Call call = service.createCall();
        
        call.setProperty(Call.SOAPACTION_USE_PROPERTY, new Boolean(true)); //Optional setting
        call.setProperty(Call.SOAPACTION_URI_PROPERTY, "");//Optional setting
        
        // specify the RPC-style operation.
        call.setProperty(Call.OPERATION_STYLE_PROPERTY,"rpc");
        
        call.setTargetEndpointAddress(targetEndPoint);
        call.setProperty(Call.ENCODINGSTYLE_URI_PROPERTY,encodingStyle);// 
        
        //It is required to set the return type
        call.setReturnType(XMLType.XSD_STRING);
        
        //Get the namespace from wsdl, It is mandatory
        call.setOperationName( new QName(namespace,operationName));
        
        //Add the required parameter
        call.addParameter("arg0", XMLType.XSD_INT, int.class, 
            ParameterMode.IN);
        call.addParameter("arg1", XMLType.XSD_INT, int.class, 
                ParameterMode.IN);//QNAME_TYPE_STRING
        call.addParameter("arg2", XMLType.XSD_INT, int.class, 
                ParameterMode.IN);
        Integer[] params = { 27,07,1979 };

        String result = (String)call.invoke(params);
        System.out.println("Zodiac Sign :::"+result);

	}

}
