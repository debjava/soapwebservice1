import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class Test 
{

	public static void main(String[] args) throws Exception
	{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("data/generated/Translation CRM Error Message RU_prop_done_CRM.properties");
		prop.load(in);
		System.out.println( prop );
	}

}
