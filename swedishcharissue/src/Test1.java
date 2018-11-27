import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.Normalizer;


public class Test1 
{
	public static void main(String[] args) throws Exception
	{
		String name = "Lars�";//"LarsÅke ÅÄÖ åäö";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(name);
		dos.flush();
		byte[] data = baos.toByteArray();
		byte[] tempData = new byte[data.length - 2 ];
		int c = 0;
		for( int i = 0 ; i < data.length ; i++ )
		{
			if( i == 0 || i == 1 )
				continue;
			else
			{
				tempData[c] = data[i];
				c++;
			}
		}
		
		System.out.println("Value using truncated data--->"+new String(tempData,"UTF-8"));
		
		dos.close();
		System.out.println( new String(data,"UTF-8") );
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bis);
		String someValue = dis.readUTF();
		System.out.println("Now the value :::"+someValue);
		byte[] bb = new byte[bis.available()];
		System.out.println("Now the value :::"+ new String(bb,"UTF-8"));
		
//		String ss = Normalizer.normalize(new String(data,"UTF-8"), Normalizer.Form.NFC);
//		String ss = Normalizer.normalize(new String(data,"UTF-8"), Normalizer.Form.NFD);
//		String ss = Normalizer.normalize(new String(data,"UTF-8"), Normalizer.Form.NFKC);
//		String ss = Normalizer.normalize(new String(data,"UTF-8"), Normalizer.Form.NFKD);
//		System.out.println(ss);
		
		
		
	}
}
