import java.security.MessageDigest;


public class TempCheckSumCalc1 
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
	
	public static String getCheckSum( String lcStringtohash ) throws Exception
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = lcStringtohash.getBytes("UTF-8");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static void main(String[] args) throws Exception
	{
//		String first = "Patrick";
//		String last =  "Cornwallis";
//		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
//		String lcTranId = "4712";
//		String lcTranDate = "20020402154524";
//		String lcTranType = "140";
		
		
//		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
//		String lcTranId = "1";
//		String lcTranDate = "20020402154524";
//		String lcTranType = "-1";
//		String first = "Patrick";
//		String last =  "Cornwallis";
		
		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId = "1";
		String lcTranDate = "20020325154526";
		String lcTranType = "103";
		String lcCurCode = "USD";
		String lcStringtohash = lcPartnerId + lcTranId + lcTranDate + lcTranType + lcCurCode+lcPartnerId;
		
//		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
//		String lcTranId = "1";
//		String lcTranDate = "20020325154526";
//		String lcTranType = "103";
//		String lcCurCode = "USD";
//		String lcStringtohash = lcPartnerId + lcTranId +
//		lcTranDate + lcTranType + lcCurCode + lcPartnerId;
		
		
//		String lcStringtohash = lcPartnerId+lcTranId+lcTranDate+lcTranType+lcPartnerId;//+first+last;
		System.out.println(lcStringtohash);
		String value = getCheckSum(lcStringtohash);
		System.out.println("Actual CheckSum->"+"488A1F0F178FE18CB7A4CA5A7206C6D4E5E0F668");
		System.out.println("CheckSum->"+value);
	}

}
