import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class MyFileUtil 
{
	public static String getFileContents( String filePath )
	{
		String fileContents = null;
		try 
		{
			File file = new File(filePath);
			InputStream in = new FileInputStream(file);
			byte[] fileData = new byte[ ( int ) file.length() ];
			in.read(fileData);
			fileContents = new String(fileData);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fileContents = "";
		}
		return fileContents;
	}
	public static void main(String[] args) 
	{
		System.out.println(getFileContents("testdata/ajaxtest.xml"));
	}
}
