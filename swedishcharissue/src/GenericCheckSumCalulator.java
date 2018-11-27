import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;


public class GenericCheckSumCalulator 
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

	public static String getCheckSum12( String lcStringtohash ) throws Exception
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] data = lcStringtohash.getBytes("ISO-8859-1");
		digest.update(data);
		String hash = toHexString(digest.digest(data));
		//System.out.println("SHA-1 checksum: " + hash);
		return hash;
	}

	public static String getCRC32ChecksumValue(Checksum checksum, String fname) 
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(baos);
			out.write(fname);
			out.flush();
			out.close();
			byte[] dataBytes = baos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(dataBytes);
			BufferedInputStream is = new BufferedInputStream( bis );
			byte[] bytes = new byte[dataBytes.length];
			int len = 0;
			while ((len = is.read(bytes)) >= 0) 
			{
				checksum.update(bytes, 0, len);
			}
			is.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return new Long(checksum.getValue()).toHexString(checksum.getValue());
	}
	
	public static String getAdler32ChecksumValue( String fname ) 
	{
		String adler32CheckSumValue = null;
		try
		{
			Checksum checksum = new Adler32();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(baos,"ISO-8859-1");
			out.write(fname);
			out.flush();
			out.close();
			byte[] dataBytes = baos.toByteArray();
			
			
			ByteArrayInputStream bis = new ByteArrayInputStream(dataBytes);
			BufferedInputStream is = new BufferedInputStream( bis );
			
			byte[] bytes = new byte[dataBytes.length];
			int len = 0;
			while ((len = is.read(bytes)) >= 0) 
			{
				checksum.update(bytes, 0, len);
			}
			is.close();
			System.out.println(checksum.getValue());
			adler32CheckSumValue = Long.toHexString(checksum.getValue());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return adler32CheckSumValue;
	}

	//	public static String getCRC32CheckSum( String lcStringtohash ) throws Exception
	//	{
	//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	//		Writer out = new OutputStreamWriter(baos);
	//		out.write(lcStringtohash);
	//		out.flush();
	//		out.close();
	//		byte[] dataBytes = baos.toByteArray();
	//
	//
	//		CheckedOutputStream cout = new CheckedOutputStream(baos, new CRC32());
	//		System.out.println(cout.getChecksum().getValue());
	//		Checksum checksum = new CRC32();
	//		checksum.update(dataBytes,0,dataBytes.length);
	//
	//		String hash = toHexString(dataBytes);
	//		System.out.println(hash);
	//		long lngChecksum = checksum.getValue();
	//		return new Long(lngChecksum).toHexString(lngChecksum);
	//
	//		//		MessageDigest digest = MessageDigest.getInstance("SHA-1");
	//		//		digest.update(dataBytes);
	//		//		String hash = toHexString(digest.digest());
	//		//		return hash;
	//	}


	public static void main(String[] args) throws Exception
	{
		//		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F33";
		//		String lcTranId = "1";
		//		String lcTranDate = "20020325154526";
		//		String lcTranType = "140";
		//		String lcCurCode = "USD";
		//		String lcStringtohash = lcPartnerId + lcTranId + lcTranDate + lcTranType + lcCurCode+lcPartnerId;

		String first = "Förändringar";
		String last =  "söker på";
		//		String first = "Patrick";
		//		String last =  "Cornwallis";
		String lcPartnerId = "B0C56B98-47A7-470D-B74D-D90D58B16F47";
		String lcTranId = "4712";
		String lcTranDate = "20020402154524";
		String lcTranType = "140";
		String lcStringtohash = lcPartnerId+lcTranId+lcTranDate+lcTranType+first+last+lcPartnerId;;
		System.out.println("Actual String->"+lcStringtohash);
		String value = getCheckSum11(lcStringtohash);
		System.out.println("SHA-1 CheckSum->"+value);
		System.out.println("CRC32 CheckSum->"+getCRC32ChecksumValue( new CRC32(), lcStringtohash));
		System.out.println("Adler32 CheckSum->"+getAdler32ChecksumValue( lcStringtohash));
		
		//		System.out.println("Adler32 CheckSum->"+getChecksumValue( new Adler32(), lcStringtohash));

		//		System.out.println("CheckSum->"+getCheckSum0(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum1(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum2(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum3(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum4(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum5(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum6(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum7(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum8(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum9(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum10(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum11(lcStringtohash));
		//		System.out.println("CheckSum->"+getCheckSum12(lcStringtohash));

	}

}
