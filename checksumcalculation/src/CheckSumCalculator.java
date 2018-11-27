import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;


public class CheckSumCalculator 
{
	public static String toHexString(byte[] block)
	{
		StringBuilder buf = new StringBuilder();
		char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F'};
		int len = block.length;
		int high;
		int low;
		for(int i = 0; i < len; i++)
		{
			high = ((block[i] & 0xf0) >> 4);
			low = (block[i] & 0x0f);
			buf.append(hexChars[high]);
			buf.append(hexChars[low]);
		}
		return buf.toString();
	}
	
	public static String getCheckSum11( String lcStringtohash ) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(baos, "ISO-8859-1");
        out.write(lcStringtohash);
        out.flush();
        out.close();
		byte[] data = baos.toByteArray();
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static void main(String[] args) throws Exception
	{
		String lcPartnerId31 = "C9E2ABCC-FD78-4FEA-ACF6-5B9DA2ED0772";
		String lcTranId31 = "6590";//6590
		String lcTranDate31 = "20101128091310";//20101128091310
		String lcTranType31 = "140";
		String first31 = "Förändringar";
		String last31 =  "söker på";
		String lcStringtohash31 = lcPartnerId31+lcTranId31+lcTranDate31+lcTranType31+first31+last31+lcPartnerId31;
		System.out.println("Actual String->"+lcStringtohash31);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash31));
		
		String lcPartnerId33 = "C9E2ABCC-FD78-4FEA-ACF6-5B9DA2ED0772";
		String lcTranId33 = "6591";
		String lcTranDate33 = "20101128091913";//20101128091310
		String lcTranType33 = "140";
		String first33 = "Förändringar";
		String last33 =  "söker på";
		String lcStringtohash33 = lcPartnerId33+lcTranId33+lcTranDate33+lcTranType33+first33+last33+lcPartnerId33;
		System.out.println("Actual String->"+lcStringtohash33);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash33));
	}

}
