import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class TestXMLSender
{
	public static String getContents( String filePath )
	{
		String contents = null;
		try 
		{
			File file = new File( filePath );
			InputStream in = new FileInputStream(file);
			byte[] buffer = new byte[(int)file.length()];
			in.read(buffer);
			contents = new String(buffer);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return contents;
	}
	
	public static void sendAndReceive( String webUrl , String xmlContents )
	{
		try 
		{
			URL url = new URL(webUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(xmlContents);
			out.close();

			BufferedReader br = new BufferedReader(
						new InputStreamReader(
						connection.getInputStream()));
			String responseStr;
			StringBuilder sb = new StringBuilder();
			while ((responseStr = br.readLine()) != null)
			{
				sb.append(responseStr);
			}
				br.close();
			System.out.println("Response from server\n"+sb.toString());	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		String xmlContents = getContents("xmldata/request.xml");
//		String url = "http://localhost:8080/normalxmloverhttp/SimpleXmlReceiverServlet";//For Jboss server 4.2.0
		String url = "http://localhost:7001/normalxmloverhttp/SimpleXmlReceiverServlet";//For weblogic server 11g
		sendAndReceive(url, xmlContents);
	}

}
