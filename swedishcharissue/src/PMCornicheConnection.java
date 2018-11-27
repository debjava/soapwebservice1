
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.commons.collections.MapUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PMCornicheConnection {
	private HashMap retMap = new LinkedHashMap();
	private String exceptionMessage = null;
	String c = null;
	public HashMap getResponse(HashMap hm) {
		try {
			//URL url = new URL("http://192.168.100.104/dotransaction.mwsapi");
			URL url = new URL("http://idealweb01:8080/dotransaction.mwsapi");//idealweb01
			//URL url = new URL("http://banking.megasol.se/dotransaction.mwsapi");
			//URL url = new URL("https://test.salesforce.com/servlet/servlet.Api");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream());
			
			//102
			/*out.write("<?xml version=�1.0� encoding=�utf-8�?><request>" +
					"<partnerid>F76951A3-6256-4774-9E06-FD5867BB9B20</partnerid>" +
					"<tranid>4941</tranid><trandate>20020402154524</trandate><trantype>102</trantype>" +
					"<linkkey>B0C56B98-47A7-470D-B74D-D90D58B16F47</linkkey><accounttype>701</accounttype>" +
					"<curcode>XAU</curcode><checksum>488A1F0F178FE18CB7A4CA5A7206C6D4E5E0F668</checksum>" +
					"</request>");*/
//			out.write("<?xml version=�1.0� encoding='UTF-8'?>" 
//			+"<CFE>"
//			+"<EXCH_RATE_UPLOAD>"
//			+"<RSCODE>DM</RSCODE>"
//			+" <SREF>1194</SREF>"
//			+" <ENTP>ENTP001</ENTP>"
//			+" <UNIT>1</UNIT>"
//			+"<USERID>ADMIN</USERID>"
//			+"<DATE>2010-05-25</DATE>"
//			+"<EXCH_RATE_DETAILS>"
//			+" <CCY1>EUR</CCY1>"
//			+" <CCY2>INR</CCY2>"
//			+"<RATE_TYPE>STANDARD</RATE_TYPE>"
//			+"<METHOD>D</METHOD>"
//			+"<MID_RATE>63</MID_RATE>"
//			+" <BUY_RATE>66</BUY_RATE>"
//			+"<SELL_RATE>60</SELL_RATE>"
//			+"<BUY_SPREAD></BUY_SPREAD>"
//			+"<SELL_SPREAD></SELL_SPREAD>"
//			+" </EXCH_RATE_DETAILS>"
//			+"</EXCH_RATE_UPLOAD>"
//			+"</CFE>");
//			out.close();
			
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
			//parsing xml
//			DocumentBuilderFactory dbf =
//	             DocumentBuilderFactory.newInstance();
//	         DocumentBuilder db = dbf.newDocumentBuilder();
//	         InputSource is = new InputSource();
//	         is.setCharacterStream(new StringReader(buf.toString()));
//	         in.close();
//	         Document doc = db.parse(is);
//	         NodeList nodes = doc.getElementsByTagName("*");
//	         for (int i = 0; i < nodes.getLength(); i++) {
//	             Element element = (Element) nodes.item(i);
//	             retMap.put(element.getNodeName(), element.getTextContent());
//	         }
	       //  System.out.println(retMap.get("extrainfo"));
	         //is = null;
		}catch(IOException ioe){
			ioe.printStackTrace();
			exceptionMessage = ioe.getMessage();
		}catch (Exception e) {
			e.printStackTrace();
			exceptionMessage = e.getMessage();
		} finally {
			if(retMap.size() == 0)
				retMap.put("status", exceptionMessage);
		}
		return retMap;
	}

	public static void main(String[] args) throws Exception {
		try {
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
//			Telephone2
			HashMap telPhone2 = new LinkedHashMap();
			telPhone2.put("phonetype","");
			telPhone2.put("phoneno","");
			telPhone2.put("extension","");
			telPhone2.put("sms", "");
			telPhone2.put("notes", "");
//			Fax1
			HashMap fax1 = new LinkedHashMap();
			fax1.put("phonetype","");
			fax1.put("phoneno","");
			fax1.put("extension","");
			fax1.put("sms", "");
			fax1.put("notes", "");
//			Fax2
			HashMap fax2 = new LinkedHashMap();
			fax2.put("phonetype","");
			fax2.put("phoneno","");
			fax2.put("extension","");
			fax2.put("sms", "");
			fax2.put("notes", "");
//			Cellular1
			HashMap cellular1 = new LinkedHashMap();
			cellular1.put("phonetype","C1");
			cellular1.put("phoneno","123");
			cellular1.put("extension","");
			cellular1.put("sms", "");
			cellular1.put("notes", "");
			
			
			//100
//			140
			HashMap hm = new LinkedHashMap();
			hm.put("partnerid", "82219FCB-9A4E-45BD-BA33-3787EC5FAB46");//sweden server
			hm.put("tranid", "103"); //
			hm.put("trandate", "20101125123456");
			hm.put("trantype", "140");
			//hm.put("firstname", "Lars�ke");	
			hm.put("firstname", "LarsÅke ÅÄÖ åäö");	
			hm.put("lastname", "invent");
			hm.put("ssn", "197202053136");
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
			String checkSum = calculateCheckSum(hm);
			System.out.println("c:"+checkSum);
			hm.put("checksum", checkSum);
			//retInfoMap = getResponse(hm, url);
									
			PMCornicheConnection pm = new PMCornicheConnection();
			HashMap retInfoMap = pm.getResponse(hm);
			
			//Iterator retIterator = retInfoMap.entrySet().iterator();
			//while (retIterator.hasNext()){
			//	Map.Entry keyValue = (Map.Entry)retIterator.next();
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method used to calculate checksum
	private static String calculateCheckSum(HashMap h)throws
			UnsupportedEncodingException,
			NoSuchAlgorithmException{
		String hash = "";
		try{
			//logger.debug(h.get("partnerid").toString());
			//logger.debug(h.get("tranid").toString());
			//logger.debug(h.get("trandate").toString());
			//logger.debug(h.get("trantype").toString());
			//logger.debug(h.get("firstname").toString());
			//logger.debug(h.get("lastname").toString());
			
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
		//System.out.println("SHA-1 checksum: " + hash);
		}catch(UnsupportedEncodingException unse){
			unse.printStackTrace();
		}catch(NoSuchAlgorithmException nae){
			nae.printStackTrace();
		}		
		return hash.trim();
	}//calculateCheckSum()
	
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

}
