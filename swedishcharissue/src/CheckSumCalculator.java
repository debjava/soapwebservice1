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
/*		//For Plain English Characters
		String first = "Patrick";
		String last =  "Cornwallis";
		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId = "4712";
		String lcTranDate = "20020402154524";
		String lcTranType = "140";
		String lcStringtohash = lcPartnerId+lcTranId+lcTranDate+lcTranType+first+last+lcPartnerId;;
		System.out.println("Actual String->"+lcStringtohash);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash));
		
		//For Swedish Character
		String first1 = "Förändringar";
		String last1 =  "söker på";
		String partnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String tranId = "4712";
		String tranDate = "20020402154524";
		String tranType = "140";
		String lcStringtohash1 = partnerId+tranId+tranDate+tranType+first1+last1+partnerId;;
		System.out.println("Actual String->"+lcStringtohash1);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash1));
		
		//Another English Character Testing
		String first2 = "abcd1234";
		String last2 =  "deba897pqrws";
		String lcPartnerId2 = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId2 = "4712";
		String lcTranDate2 = "20020402154524";
		String lcTranType2 = "140";
		String lcStringtohash2 = lcPartnerId2+lcTranId2+lcTranDate2+lcTranType2+first2+last2+lcPartnerId2;;
		System.out.println("Actual String->"+lcStringtohash2);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash2));
		
		//More Swedish Character Testing
		String first3 = "Välkommen";
		String last3 =  "på ömsesidig";
		String lcPartnerId3 = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId3 = "4712";
		String lcTranDate3 = "20020402154524";
		String lcTranType3 = "140";
		String lcStringtohash3 = lcPartnerId3+lcTranId3+lcTranDate3+lcTranType3+first3+last3+lcPartnerId3;;
		System.out.println("Actual String->"+lcStringtohash3);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash3));
*/		
		String first31 = "Förändringar";
		String last31 =  "söker på";
		String lcPartnerId31 = "C9E2ABCC-FD78-4FEA-ACF6-5B9DA2ED0772";
		String lcTranId31 = "6591";//6590
		String lcTranDate31 = "20101128091913";//20101128091310
		String lcTranType31 = "140";
		String lcStringtohash31 = lcPartnerId31+lcTranId31+lcTranDate31+lcTranType31+first31+last31+lcPartnerId31;;
		System.out.println("Actual String->"+lcStringtohash31);
		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash31));
	}

}
