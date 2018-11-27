import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub;
import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.GetZodiacSign;
import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.GetZodiacSignE;

//import com.ddlab.webservice.client.ZodiaccalculatorserviceStub;
//import com.ddlab.webservice.client.ZodiaccalculatorserviceStub.GetZodiacSign;
//import com.ddlab.webservice.client.ZodiaccalculatorserviceStub.GetZodiacSignResponse;

public class TestUsingAxis2GeneratedStub 
{
	public static void main(String[] args) throws Exception
	{
		String targetEndPoint = "http://localhost:8080/zodiaccalculatorservice_axis2_manyclients/services/zodiaccalculatorservice?wsdl";
		try 
		{
			// For Axis2 generated stub
//			ZodiaccalculatorserviceStub stub = new ZodiaccalculatorserviceStub();
//			GetZodiacSign sign = new GetZodiacSign();
//			sign.setDay(25);
//			sign.setMonth(07);
//			sign.setYear(1923);
//			GetZodiacSignResponse response = stub.getZodiacSign(sign);
//			System.out.println(response.get_return());
			
			ZodiacCalculatorImplServiceStub stub = new ZodiacCalculatorImplServiceStub();
			
			GetZodiacSign zodSign = new GetZodiacSign();
			zodSign.setArg0(27);
			zodSign.setArg1(07);
			zodSign.setArg2(1977);
			GetZodiacSignE zod = new GetZodiacSignE();
			zod.setGetZodiacSign(zodSign);
			System.out.println(stub.getZodiacSign(zod).getGetZodiacSignResponse().get_return());
			
			
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
