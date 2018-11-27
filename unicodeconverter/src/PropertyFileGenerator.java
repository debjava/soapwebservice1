import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class PropertyFileGenerator
{
	public static void main(String[] args) 
	{
		try 
		{
			String dirPath = "data"+File.separator+"consolidated"+File.separator+"Messages RU-Prop-Done";
			File dirFile = new File(dirPath);
			File[] files = dirFile.listFiles();
			for( int i = 0 ; i < files.length ; i++ )
			{
				String filePath = files[i].getAbsolutePath();
				String onlyFileName = files[i].getName();

				Map<String,Properties> enRuMap = ConvertXlsToProperties.getCellInfo(filePath, 5);
				Iterator itr = enRuMap.entrySet().iterator();
				while( itr.hasNext() )
				{
					Map.Entry<String, Properties> me = (Map.Entry<String, Properties>)itr.next();
					String sheetName = me.getKey();
					System.out.println(sheetName);
					System.out.println("----------------------------------------");
					Properties prop = me.getValue();
					System.out.println(prop);
					
					String destnFileName = "data" + File.separator + "generated"
					+ File.separator
					+ onlyFileName.substring(0, onlyFileName.indexOf("."))+"_"+sheetName
					+ ".properties";
					System.out.println(destnFileName);
					OutputStream out = new FileOutputStream(destnFileName);
					prop.store(out, sheetName);
					out.close();
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
