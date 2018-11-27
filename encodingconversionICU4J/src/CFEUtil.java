import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.ibm.icu.charset.CharsetICU;


public class CFEUtil 
{
	//Method used to calculate checksum
	public static String calculateCheckSum(HashMap h)throws
	UnsupportedEncodingException,
	NoSuchAlgorithmException{
		String hash = "";
		try{
//			String first = new String(h.get("firstname").toString().getBytes("UTF-8"));
//			String last = new String(h.get("lastname").toString().getBytes("UTF-8"));
			
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			String lcStringtohash = h.get("partnerid").toString() +
			h.get("tranid").toString()+
			h.get("trandate").toString()+
			h.get("trantype").toString()+
			
			h.get("firstname").toString()+h.get("lastname")+
			
//			first+
//			last+
			h.get("partnerid").toString();

			//For testing
//			String unistr = UnicodeConverter.convertFromOriginalToUnicode(lcStringtohash);
//			byte[] data = unistr.getBytes("UTF-8");
			//For testing
			
			//For testing
			Charset c = CharsetICU.forNameICU("UTF-8");
//			Charset c = Charset.forName("UTF-8");
			CharsetEncoder en = c.newEncoder(); 
			ByteBuffer data = en.encode(CharBuffer.wrap(lcStringtohash)); 
			
			
			
//			byte[] data = c.encode(lcStringtohash).array();
			
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			DataOutputStream dos = new DataOutputStream(baos);
//			dos.writeUTF(lcStringtohash);
//			byte[] data = baos.toByteArray();

			//For testing
			
			
			
//			byte[] data = lcStringtohash.getBytes("UTF-8");
			digest.update(data);
			hash = toHexString(digest.digest());
			//System.out.println("SHA-1 checksum: " + hash);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
//		catch(UnsupportedEncodingException unse)
//		{
//			unse.printStackTrace();
//		}
//		catch(NoSuchAlgorithmException nae)
//		{
//			nae.printStackTrace();
//		}		
		return hash.trim();
	}//calculateCheckSum()

	
	public static String calculateCheckSum1(HashMap h)throws
	UnsupportedEncodingException,
	NoSuchAlgorithmException{
		String hash = "";
		try{
//			String first = new String(h.get("firstname").toString().getBytes("UTF-8"));
//			String last = new String(h.get("lastname").toString().getBytes("UTF-8"));
			
//			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			String lcStringtohash = h.get("partnerid").toString() +
			h.get("tranid").toString()+
			h.get("trandate").toString()+
			h.get("trantype").toString()+
			h.get("firstname").toString()+h.get("lastname")+
			h.get("partnerid").toString();
			
			hash = getMD5Checksum(lcStringtohash);
			
			
//			byte[] data = lcStringtohash.getBytes("UTF-8");
//			digest.update(data);
//			hash = toHexString(digest.digest());
			//System.out.println("SHA-1 checksum: " + hash);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
//		catch(UnsupportedEncodingException unse)
//		{
//			unse.printStackTrace();
//		}
//		catch(NoSuchAlgorithmException nae)
//		{
//			nae.printStackTrace();
//		}		
		return hash.trim();
	}//calculateCheckSum()
	
	public static String getMD5Checksum(String string) throws Exception { 
	      MessageDigest msgdigest = MessageDigest.getInstance("SHA-1"); 
	      StringBuffer  md5hex    = new StringBuffer(); 
	      char[]         chars    = string.toCharArray(); 
	       byte[]         bytes    = new byte[chars.length]; 

	       for (int i = 0; i < chars.length; i++) { 
	           bytes[i] = (byte)chars[i]; 
	       } 

	       byte[] md5bytes = msgdigest.digest(bytes); 
	       
	       for (int i = 0; i < md5bytes.length; i++) { 
	           int val = ((int)md5bytes[i]) & 0xff; 

	           if (val < 16) { 
	               md5hex.append("0"); 
	           } 

	           md5hex.append(Integer.toHexString(val)); 
	       } 

	       String md5string = md5hex.toString(); 
	       
	       return md5string; 
	   } 


	
	
	
	//Method to get toHexString
	public static String toHexString(byte[] block){
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
	}//toHexString()
	
	public static String getCFETypeCheckSum( String str ) throws Exception
	{
		String checkSum = null;
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = str.getBytes("UTF-8");
		digest.update(data);
		String hash = toHexString(digest.digest());
//		System.out.println("SHA-1 checksum: " + hash);
		checkSum = hash;
		return checkSum;
	}
	
	public static void main(String[] args) throws Exception
	{
		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId = "1";
		String lcTranDate = "20020325154526";
		String lcTranType = "103";
		String lcCurCode = "USD";
		String firstName = "LarsÅke ÅÄÖ åäö";
		String lastName = "invent";
		String lcStringtohash = lcPartnerId + lcTranId +
		lcTranDate + lcTranType + lcCurCode + lcPartnerId+firstName+lastName;
		
		Charset cs = Charset.defaultCharset();
		System.out.println(cs.name());
		
		String ss = "LarsÅke ÅÄÖ åäö";
		System.out.println( new String (ss.getBytes(),"UTF-8"));
		
//		Charset cs1 = Charset.forName("UTF-8");
//		byte[] bb = cs1.encode(ss).array();
//		System.out.println( new String (bb,"UTF-8") );
//		
//		System.out.println(cs.name());
		
//		DataInputStream.
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(ss);
		
		byte[] b1 = baos.toByteArray();
		System.out.println( "--->"+new String(b1));
		System.out.println( "--->"+new String(b1,"UTF-8"));
		
		//Cfe type style
//		System.out.println("Check Sum Value CFE---->"+getCFETypeCheckSum(lcStringtohash));
//		
//		//Based upon Unicode conversion
//		String uniStr = UnicodeConverter.convertFromOriginalToUnicode(lcStringtohash);
//		System.out.println("Check Sum Value MY---->"+getCFETypeCheckSum(uniStr));
		
	}

}
