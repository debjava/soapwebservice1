import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class StringReplacerInFile 
{
	
	public static void replaceStringInFile( String filePath , String strToBeReplaced , String replacingString ) throws Exception
	{
		File file = new File(filePath);
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
		long fpointer = randomAccessFile.getFilePointer();
		String lineData = "";
		
		while((lineData = randomAccessFile.readLine()) != null)
		{
//			System.out.println(lineData);
			fpointer = randomAccessFile.getFilePointer() - lineData.length()-2;
			if(lineData.indexOf(strToBeReplaced) > 0)
			{
				System.out.println("Changing string in file "+file.getName());
				randomAccessFile.seek(fpointer);
				randomAccessFile.writeBytes(replacingString);
			}
		}

	}
	
	
	public static void main(String[] args) throws Exception
	{
		String filePath = "data/generated/Translation GD Error Message RU_Prop_Done_Base Insurance Amount.properties";
		String strToBeReplaced = "amount";
		String replacingString =" sasdasdas";
		replaceStringInFile(filePath, strToBeReplaced, replacingString);
	}

}
