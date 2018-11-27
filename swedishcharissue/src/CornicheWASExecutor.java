import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class CornicheWASExecutor 
{
	private static HashMap retMap = new LinkedHashMap();
	private static String exceptionMessage = null;
	
	public static Map getDataMap() throws Exception
	{
		//Validated Addr
		HashMap validatedAddr = new LinkedHashMap();
		validatedAddr.put("addresstype", "V");
		validatedAddr.put("address1", "test");
		validatedAddr.put("address2", "test");
		validatedAddr.put("city", "test");
		validatedAddr.put("state", "");
		validatedAddr.put("zipcode", "33182");
		validatedAddr.put("country", "SE");
		validatedAddr.put("notes", "");
		//Postal Addr
		HashMap postalAddr = new LinkedHashMap();
		postalAddr.put("addresstype", "P");
		postalAddr.put("address1", "test");
		postalAddr.put("address2", "test");
		postalAddr.put("city", "test");
		postalAddr.put("state", "");
		postalAddr.put("zipcode", "33182");
		postalAddr.put("country", "SE");
		postalAddr.put("notes", "");
		//Street Addr
		HashMap streetAddr = new LinkedHashMap();
		streetAddr.put("addresstype", "");
		streetAddr.put("address1", "");
		streetAddr.put("address2", "");
		streetAddr.put("city", "");
		streetAddr.put("state", "");
		streetAddr.put("zipcode", "");
		streetAddr.put("country", "");
		streetAddr.put("notes", "");
		//Telephone1
		HashMap telPhone1 = new LinkedHashMap();
		telPhone1.put("phonetype","");
		telPhone1.put("phoneno","");
		telPhone1.put("extension","");
		telPhone1.put("sms", "");
		telPhone1.put("notes", "");
//		Telephone2
		HashMap telPhone2 = new LinkedHashMap();
		telPhone2.put("phonetype","");
		telPhone2.put("phoneno","");
		telPhone2.put("extension","");
		telPhone2.put("sms", "");
		telPhone2.put("notes", "");
//		Fax1
		HashMap fax1 = new LinkedHashMap();
		fax1.put("phonetype","");
		fax1.put("phoneno","");
		fax1.put("extension","");
		fax1.put("sms", "");
		fax1.put("notes", "");
//		Fax2
		HashMap fax2 = new LinkedHashMap();
		fax2.put("phonetype","");
		fax2.put("phoneno","");
		fax2.put("extension","");
		fax2.put("sms", "");
		fax2.put("notes", "");
//		Cellular1
		HashMap cellular1 = new LinkedHashMap();
		cellular1.put("phonetype","C1");
		cellular1.put("phoneno","123");
		cellular1.put("extension","");
		cellular1.put("sms", "");
		cellular1.put("notes", "");
		
//		140
		HashMap hm = new LinkedHashMap();
		hm.put("partnerid", "B0C56B98-47A7-470D-B74D-D90D58B16F47");//sweden server
		hm.put("tranid", "4712"); //
		hm.put("trandate", "20020402154524");//20101125123456
		hm.put("trantype", "140");
		//hm.put("firstname", "Larsï¿½ke");	
		hm.put("firstname", "Patrick");//På Se möjligheter 	//LarsÃ…ke Ã…Ã„Ã– Ã¥Ã¤Ã¶ //LarsÃ Verdandi Gränd //Verdandi Gränd
//		hm.put("firstname", "Deba Testing");
		hm.put("lastname", "Cornwallis");
		hm.put("ssn", "196001014504");//197202053136
		hm.put("citizen", "SE");
		hm.put("domicile", "SE");
		hm.put("taxcountry", "SE");
		hm.put("address1", validatedAddr);
		hm.put("address2", postalAddr);
		hm.put("phone1", cellular1);
		//hm.put("phone2", workNum2);
		//hm.put("phone3", home1);
		hm.put("email", "123@c.com");
		hm.put("passphrase", "");
		
		
//		String checkSum = CFEUtil.calculateCheckSum1(hm);
		String checkSum = CFECheckSumCalculator.calculateCheckSumDeba1(hm);
		
		
		System.out.println("c:"+checkSum);
		hm.put("checksum", checkSum);
		
		return hm;
	}
	
	public static HashMap getResponse( HashMap hm )
	{

		try {
			URL url = new URL("http://idealweb01:8080/dotransaction.mwsapi");//idealweb01
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream());
			
			StringBuffer sb = new StringBuffer();
			//Making xml format using hash map values
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("\n");
			sb.append('<').append("request").append('>');
			sb.append("\n");
			//iterating has map values
			Iterator valueIterator = hm.entrySet().iterator();
			while (valueIterator.hasNext()){
				Map.Entry cellId = (Map.Entry)valueIterator.next();
				
				if(cellId.getKey().toString().equalsIgnoreCase("address1")){
					sb.append("<");
					sb.append("address");
					sb.append(">");
					sb.append("\n");
					LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("address1");
					Iterator lhmAddrIterator = lhmAddr1.entrySet().iterator();
					while(lhmAddrIterator.hasNext()){
						Map.Entry cellIdAddr1 = (Map.Entry)lhmAddrIterator.next();
						sb.append("<");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						if( cellIdAddr1.getValue() == null || cellIdAddr1.getValue() == "")
							sb.append("");
						else
							sb.append(cellIdAddr1.getValue().toString().trim());
						sb.append("</");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						sb.append("\n");
					}
					sb.append("</");
					sb.append("address");
					sb.append(">");
					sb.append("\n");
				}else if(cellId.getKey().toString().equalsIgnoreCase("address2")){
					sb.append('<');
					sb.append("address");
					sb.append(">");
					sb.append("\n");
					LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("address2");
					Iterator lhmAddrIterator = lhmAddr1.entrySet().iterator();
					while(lhmAddrIterator.hasNext()){
						Map.Entry cellIdAddr1 = (Map.Entry)lhmAddrIterator.next();
						sb.append("<");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						if( cellIdAddr1.getValue() == null || cellIdAddr1.getValue() == "")
							sb.append("");
						else
							sb.append(cellIdAddr1.getValue().toString().trim());
						sb.append("</");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						sb.append("\n");
					}
					sb.append("</");
					sb.append("address");
					sb.append(">");
					sb.append("\n");
				}else if(cellId.getKey().toString().equalsIgnoreCase("phone1")){
					LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("phone1");
					System.out.println("lhmAddr1.size()----1---:"+lhmAddr1.size());
					if(lhmAddr1.size() !=0){
					sb.append('<');
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
					Iterator lhmAddrIterator = lhmAddr1.entrySet().iterator();
					while(lhmAddrIterator.hasNext()){
						Map.Entry cellIdAddr1 = (Map.Entry)lhmAddrIterator.next();
						sb.append("<");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						if( cellIdAddr1.getValue() == null || cellIdAddr1.getValue() == "")
							sb.append("");
						else
							sb.append(cellIdAddr1.getValue().toString().trim());
						sb.append("</");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						sb.append("\n");
					}
					sb.append("</");
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
				}
				}else if(cellId.getKey().toString().equalsIgnoreCase("phone2")){
					LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("phone2");
					System.out.println("lhmAddr2.size()----2---:"+lhmAddr1.size());
					if(lhmAddr1.size() !=0){
					sb.append('<');
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
					//LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("phone2");
					Iterator lhmAddrIterator = lhmAddr1.entrySet().iterator();
					while(lhmAddrIterator.hasNext()){
						Map.Entry cellIdAddr1 = (Map.Entry)lhmAddrIterator.next();
						sb.append("<");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						if( cellIdAddr1.getValue() == null || cellIdAddr1.getValue() == "")
							sb.append("");
						else
							sb.append(cellIdAddr1.getValue().toString().trim());
						sb.append("</");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						sb.append("\n");
					}
					sb.append("</");
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
					}
				}else if(cellId.getKey().toString().equalsIgnoreCase("phone3")){
					LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("phone3");
					System.out.println("lhmAddr1.size()----3---:"+lhmAddr1.size());
					if(lhmAddr1.size() !=0){
					sb.append('<');
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
					//LinkedHashMap lhmAddr1 = (LinkedHashMap) hm.get("phone3");
					Iterator lhmAddrIterator = lhmAddr1.entrySet().iterator();
					while(lhmAddrIterator.hasNext()){
						Map.Entry cellIdAddr1 = (Map.Entry)lhmAddrIterator.next();
						sb.append("<");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						if( cellIdAddr1.getValue() == null || cellIdAddr1.getValue() == "")
							sb.append("");
						else
							sb.append(cellIdAddr1.getValue().toString().trim());
						sb.append("</");
						sb.append(cellIdAddr1.getKey().toString().trim());
						sb.append('>');
						sb.append("\n");
					}
					sb.append("</");
					sb.append("phone");
					sb.append(">");
					sb.append("\n");
					}
				}else{		
				sb.append('<');
				sb.append(cellId.getKey().toString().trim());
				sb.append('>');
				if( cellId.getValue() == null )
					sb.append("");
				else
					sb.append(cellId.getValue().toString().trim());
				sb.append("</");
				sb.append(cellId.getKey().toString().trim());
				sb.append('>');
				sb.append("\n");
				}
			}// end while
			sb.append("</").append("request").append('>');
			System.out.println(sb.toString());
			out.write(sb.toString().trim());
			out.close();
			System.out.println("===============================");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuffer buf = new StringBuffer();
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				buf.append(decodedString);
				buf.append("\n");
			}
			System.out.println(buf.toString());
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			exceptionMessage = ioe.getMessage();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			exceptionMessage = e.getMessage();
		} 
		finally 
		{
			if(retMap.size() == 0)
				retMap.put("status", exceptionMessage);
		}
		return retMap;
	
	}
	
	public static void main(String[] args) 
	{
		try 
		{
			HashMap hm = (HashMap)getDataMap();
			HashMap retInfoMap = getResponse(hm);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
