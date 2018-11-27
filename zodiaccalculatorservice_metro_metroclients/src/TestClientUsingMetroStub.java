import com.ddlab.client.metro.ZodiacCalculatorImpl;
import com.ddlab.client.metro.ZodiacCalculatorImplService;


public class TestClientUsingMetroStub
{

	public static void main(String[] args) throws Exception
	{
		ZodiacCalculatorImpl zocl = new ZodiacCalculatorImplService().getZodiacCalculatorImplPort();
		System.out.println(zocl.getZodiacSign(27, 07, 1977));
		
	}

}
