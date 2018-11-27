//import java.util.HashMap;
//
//public class TextNormalize 
//{
//	private ICU4JImpl icu4j = null;
//	private static final HashMap DIACHASH = new HashMap();
//	private String outputEncoding;
//
//	public TextNormalize(String encoding) 
//	{
//		findICU4J();
//		populateDiacHash();
//		this.outputEncoding = encoding;
//	}
//
//	private void findICU4J() 
//	{
//		// see if we can load the icu4j classes from the classpath
//		try 
//		{
//			this.getClass().getClassLoader().loadClass("com.ibm.icu.text.Bidi");
//			this.getClass().getClassLoader().loadClass("com.ibm.icu.text.Normalizer");
//			icu4j = new ICU4JImpl();
//		} 
//		catch (ClassNotFoundException e) 
//		{
//			icu4j = null;
//		}
//	}
//
//	private void populateDiacHash()
//	{
//		DIACHASH.put(new Integer(0x0060), "\u0300");
//		DIACHASH.put(new Integer(0x02CB), "\u0300");
//		DIACHASH.put(new Integer(0x0027), "\u0301");
//		DIACHASH.put(new Integer(0x02B9), "\u0301");
//		DIACHASH.put(new Integer(0x02CA), "\u0301");
//		DIACHASH.put(new Integer(0x005e), "\u0302");
//		DIACHASH.put(new Integer(0x02C6), "\u0302");
//		DIACHASH.put(new Integer(0x007E), "\u0303");
//		DIACHASH.put(new Integer(0x02C9), "\u0304");
//		DIACHASH.put(new Integer(0x00B0), "\u030A");
//		DIACHASH.put(new Integer(0x02BA), "\u030B");
//		DIACHASH.put(new Integer(0x02C7), "\u030C");
//		DIACHASH.put(new Integer(0x02C8), "\u030D");
//		DIACHASH.put(new Integer(0x0022), "\u030E");
//		DIACHASH.put(new Integer(0x02BB), "\u0312");
//		DIACHASH.put(new Integer(0x02BC), "\u0313");
//		DIACHASH.put(new Integer(0x0486), "\u0313");
//		DIACHASH.put(new Integer(0x055A), "\u0313");
//		DIACHASH.put(new Integer(0x02BD), "\u0314");
//		DIACHASH.put(new Integer(0x0485), "\u0314");
//		DIACHASH.put(new Integer(0x0559), "\u0314");
//		DIACHASH.put(new Integer(0x02D4), "\u031D");
//		DIACHASH.put(new Integer(0x02D5), "\u031E");
//		DIACHASH.put(new Integer(0x02D6), "\u031F");
//		DIACHASH.put(new Integer(0x02D7), "\u0320");
//		DIACHASH.put(new Integer(0x02B2), "\u0321");
//		DIACHASH.put(new Integer(0x02CC), "\u0329");
//		DIACHASH.put(new Integer(0x02B7), "\u032B");
//		DIACHASH.put(new Integer(0x02CD), "\u0331");
//		DIACHASH.put(new Integer(0x005F), "\u0332");
//		DIACHASH.put(new Integer(0x204E), "\u0359");
//	}
//
//	public String makeLineLogicalOrder(String str, boolean isRtlDominant) 
//	{
//		if (icu4j != null) 
//		{
//			return icu4j.makeLineLogicalOrder(str, isRtlDominant);
//		}
//		else 
//		{
//			return str;
//		}
//	}
//
//	public String normalizePres(String str) 
//	{
//		if (icu4j != null) 
//		{
//			return icu4j.normalizePres(str);
//		}
//		else 
//		{
//			return str;
//		}
//	}
//
//
//	public String normalizeDiac(String str)
//	{
//
//		if(outputEncoding != null && outputEncoding.toUpperCase().startsWith("UTF"))
//		{
//			Integer c = new Integer(str.charAt(0));
//			// convert the characters not defined in the Unicode spec
//			if(DIACHASH.containsKey(c))
//			{
//				return (String)DIACHASH.get(c);
//			}
//			else if (icu4j != null) 
//			{
//				return icu4j.normalizeDiac(str);
//			}
//			else 
//			{
//				return str;
//			}
//		}
//		else
//		{
//			return str;
//		}
//	}
//}
