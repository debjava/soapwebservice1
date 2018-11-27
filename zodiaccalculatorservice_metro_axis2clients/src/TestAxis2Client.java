import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub;
import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.GetZodiacSign;
import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.GetZodiacSignE;
import com.ddlab.webservice.client.ZodiacCalculatorImplServiceStub.GetZodiacSignResponseE;


public class TestAxis2Client 
{
	public static void main(String[] args) throws Exception
	{
		ZodiacCalculatorImplServiceStub stub = new ZodiacCalculatorImplServiceStub();
		GetZodiacSignE zode = new GetZodiacSignE();
		
		GetZodiacSign zod = new GetZodiacSign();
		zod.setArg0(27);
		zod.setArg1(07);
		zod.setArg2(1977);
		
		zode.setGetZodiacSign(zod);
		
		GetZodiacSignResponseE res = stub.getZodiacSign(zode);
		System.out.println(res.getGetZodiacSignResponse().get_return());
		
		
		
	}
}
