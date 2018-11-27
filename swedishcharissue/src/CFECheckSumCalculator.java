import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;


public class CFECheckSumCalculator 
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
	}//toHexString()

	//Added by Deba
	public static String calculateCheckSumDeba1(HashMap h)throws
	UnsupportedEncodingException,
	NoSuchAlgorithmException
	{
		String hash = "";
		try
		{
			String first = (String)h.get("firstname");
			String last =  (String)h.get("lastname");

			String lcStringtohash = (String)h.get("partnerid") +
			(String)h.get("tranid")+
			(String)h.get("trandate")+
			(String)h.get("trantype")+
			first+
			last+
			(String)h.get("partnerid");

			hash = getCheckSum1(lcStringtohash);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return hash.trim();
	}
	//For testing, In the hope that it may work
	public static String getCheckSum11( String lcStringtohash ) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(baos, "ISO-8859-1");
        out.write(lcStringtohash);
        out.flush();
        out.close();
		byte[] data = baos.toByteArray();
//		System.out.println("Actual---->"+new String(data,"UTF-8"));
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static String getCheckSum10( String lcStringtohash ) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream(lcStringtohash.getBytes().length);
		OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
		osw.write(lcStringtohash);
		osw.flush();
		byte[] data = out.toByteArray();
		osw.close();
//		System.out.println("Actual---->"+new String(data,"UTF-8"));
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static String getCheckSum9( String lcStringtohash ) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(lcStringtohash.getBytes("UTF-8"));
		byte[] data = out.toByteArray();
//		System.out.println("Actual---->"+new String(data));
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static String getCheckSum8( String lcStringtohash ) throws Exception
	{
		String s1 = UnicodeConverter.convertFromOriginalToUnicode(lcStringtohash);
		byte[] data = s1.getBytes();
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}
	
	public static String getCheckSum7( String lcStringtohash ) throws Exception
	{
		String s1 = UnicodeConverter.convertFromOriginalToUnicode(lcStringtohash);
		byte[] data = s1.getBytes("utf-8");
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}

	public static String getCheckSum6( String lcStringtohash ) throws Exception
	{
//		System.out.println("Total String \n"+lcStringtohash);
		CharsetDetector cd = new CharsetDetector(); 
		cd.setText(lcStringtohash.getBytes());
		CharsetMatch cm = cd.detect();
		String language = cm.getLanguage();
		String encodingName = cm.getName();
//		System.out.println(encodingName+"---"+language); 
		String s1 = cd.getString(lcStringtohash.getBytes(), encodingName);
		Charset cs = Charset.forName("utf-8");
		ByteBuffer data = cs.encode(s1);

		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}

	//Third pary code
	public static String getCheckSum5( String lcStringtohash ) throws Exception
	{
		byte[] testByte = lcStringtohash.getBytes();
		byte[] data = UnicodeUtil.convert(testByte, "UTF-8");
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}

	public static String getCheckSum4( String lcStringtohash ) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StringReader sr = new StringReader(lcStringtohash);
		BufferedReader br = new BufferedReader(sr);
		String ssss = br.readLine();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(ssss);
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
		System.out.println("Actual--->"+new String(tempData,"UTF-8"));
		dos.flush();
		dos.close();
		
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(tempData);
		String hash = toHexString(digest.digest());
		//System.out.println("SHA-1 checksum: " + hash);
		return hash;
	}

	public static String getCheckSum3( String lcStringtohash ) throws Exception
	{
		Charset c = Charset.forName("UTF-8");
		CharsetEncoder en = c.newEncoder(); 
		ByteBuffer data = en.encode(CharBuffer.wrap(lcStringtohash)); 
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(data);
		String hash = toHexString(digest.digest());
		return hash;
	}

	public static String getCheckSum2( String lcStringtohash ) throws Exception
	{
		String unistr = UnicodeConverter.convertFromOriginalToUnicode(lcStringtohash);
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = unistr.getBytes("UTF-8");
		digest.update(data);
		String hash = toHexString(digest.digest());
		//System.out.println("SHA-1 checksum: " + hash);
		return hash;
	}

	public static String getCheckSum1( String lcStringtohash ) throws Exception
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = lcStringtohash.getBytes("UTF-8");
//		digest.update(data);
		String hash = toHexString(digest.digest(data));
		//System.out.println("SHA-1 checksum: " + hash);
		return hash;
	}
	
	//Default encoding
	public static String getCheckSum0( String lcStringtohash ) throws Exception
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = lcStringtohash.getBytes();
		digest.update(data);
		String hash = toHexString(digest.digest(data));
		//System.out.println("SHA-1 checksum: " + hash);
		return hash;
	}


	//According to Eric
	public static String calculateCheckSum1(HashMap h)throws
	UnsupportedEncodingException,
	NoSuchAlgorithmException
	{
		String hash = "";
		try
		{
			String first = new String(h.get("firstname").toString().getBytes());
			String last = new String(h.get("lastname").toString().getBytes());

			MessageDigest digest = MessageDigest.getInstance("SHA-1");

			String lcStringtohash = h.get("partnerid").toString() +
			h.get("tranid").toString()+
			h.get("trandate").toString()+
			h.get("trantype").toString()+
			first+
			last+
			h.get("partnerid").toString();

			byte[] data = lcStringtohash.getBytes();
			digest.update(data);
			hash = toHexString(digest.digest());
			System.out.println("SHA-1 checksum: " + hash);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return hash.trim();
	}//calculateCheckSum()


	public static String calculateCheckSum(HashMap h)throws
	UnsupportedEncodingException,
	NoSuchAlgorithmException
	{
		String hash = "";
		try
		{
			String first = new String(h.get("firstname").toString().getBytes("UTF-8"));
			String last = new String(h.get("lastname").toString().getBytes("UTF-8"));

			MessageDigest digest = MessageDigest.getInstance("SHA-1");

			String lcStringtohash = h.get("partnerid").toString() +
			h.get("tranid").toString()+
			h.get("trandate").toString()+
			h.get("trantype").toString()+
			first+
			last+
			h.get("partnerid").toString();

			byte[] data = lcStringtohash.getBytes("UTF-8");
			digest.update(data);
			hash = toHexString(digest.digest());
			System.out.println("SHA-1 checksum: " + hash);
		}catch(UnsupportedEncodingException unse){
			unse.printStackTrace();
		}catch(NoSuchAlgorithmException nae){
			nae.printStackTrace();
		}		
		return hash.trim();
	}//calculateCheckSum()

//	public static void main(String[] args) 
//	{
//		try 
//		{
//			HashMap hm = new LinkedHashMap();
//			hm.put("partnerid", "82219FCB-9A4E-45BD-BA33-3787EC5FAB46");//sweden server
//			hm.put("tranid", "105"); //
//			hm.put("trandate", "20101125123459");//20101125123456
//			hm.put("trantype", "140");
//			hm.put("firstname", "LarsÃ Verdandi Gränd");	//LarsÃ…ke Ã…Ã„Ã– Ã¥Ã¤Ã¶ //LarsÃ Verdandi Gränd //Verdandi Gränd
//			hm.put("lastname", "invent");
//			hm.put("ssn", "195806258736");//197202053136
//			hm.put("citizen", "SE");
//			hm.put("domicile", "SE");
//			hm.put("taxcountry", "SE");
//			hm.put("email", "abc123@c.com");
//			hm.put("passphrase", "");
//			String checkSum = calculateCheckSum(hm);
//			System.out.println("Calculated Check Sum :::"+checkSum);
//			//As suggested by Eric
//			checkSum = calculateCheckSum1(hm);
//			System.out.println("Calculated Check Sum :::"+checkSum);
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) 
//	{
//		try
//		{
//			String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
//			String lcTranId = "1";
//			String lcTranDate = "20020325154526";
//			String lcTranType = "103";
//			String lcCurCode = "USD";
//			String lcStringtohash = lcPartnerId + lcTranId + lcTranDate + lcTranType + lcCurCode;
//			lcStringtohash = lcStringtohash + lcPartnerId;
//			
//			System.out.println("Example String \n"+lcStringtohash);
//			
//			System.out.println("Using Method getCheckSum0()");
//			String value = getCheckSum0(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum1()");
//			value = getCheckSum1(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum2()");
//			value = getCheckSum2(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum3()");
//			value = getCheckSum3(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum4()");
//			value = getCheckSum4(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum5()");
//			value = getCheckSum5(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum6()");
//			value = getCheckSum6(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum7()");
//			value = getCheckSum7(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum8()");
//			value = getCheckSum8(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum9()");
//			value = getCheckSum9(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum10()");
//			value = getCheckSum10(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//			System.out.println("Using Method getCheckSum11()");
//			value = getCheckSum11(lcStringtohash);
//			System.out.println("CheckSum->"+value);
//			
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) throws Exception
	{
//		String lcPartnerId = "C9E2ABCC-FD78-4FEA-ACF6-5B9DA2ED0772";
//		String lcTranId = "6425";
//		String lcTranDate = "20101127013558";
//		String lcTranType = "137";
//		String lcCurCode = "USD";
//		String lcStringtohash = lcPartnerId + lcTranId + lcTranDate + lcTranType + lcCurCode;
//		lcStringtohash = lcStringtohash + lcPartnerId;
		
		String first = "Patrick";
		String last =  "Cornwallis";
//		String last =  "";
		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId = "4712";
		String lcTranDate = "20020402154524";
		String lcTranType = "140";
		
//		String first = (String)h.get("firstname");
//		String last =  (String)h.get("lastname");
//
//		String lcStringtohash = (String)h.get("partnerid") +
//		(String)h.get("tranid")+
//		(String)h.get("trandate")+
//		(String)h.get("trantype")+
//		first+
//		last+
//		(String)h.get("partnerid");
		
		String lcStringtohash = lcPartnerId+lcTranId+lcTranDate+lcTranType+first+last+lcPartnerId;
		
		System.out.println("Example String \n"+lcStringtohash);
		
		System.out.println("Using Method getCheckSum0()");
		String value = getCheckSum0(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum1()");
		value = getCheckSum1(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum2()");
		value = getCheckSum2(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum3()");
		value = getCheckSum3(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum4()");
		value = getCheckSum4(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum5()");
		value = getCheckSum5(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum6()");
		value = getCheckSum6(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum7()");
		value = getCheckSum7(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum8()");
		value = getCheckSum8(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum9()");
		value = getCheckSum9(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum10()");
		value = getCheckSum10(lcStringtohash);
		System.out.println("CheckSum->"+value);
		
		System.out.println("Using Method getCheckSum11()");
		value = getCheckSum11(lcStringtohash);
		System.out.println("CheckSum->"+value);

		
//		String lcStringtohash = (String)h.get("partnerid") +
//		(String)h.get("tranid")+
//		(String)h.get("trandate")+
//		(String)h.get("trantype")+
//		first+
//		last+
//		(String)h.get("partnerid");
//		hash = getCheckSum11(lcStringtohash);
		
		
	}
	
}
