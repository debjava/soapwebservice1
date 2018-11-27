
/**
 * @author Debadatta Mishra(PIKU)
 *
 */
public class UnicodeConverter 
{
	private static final char[] hexTable = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };

	private static char toHex(int val) 
	{
		return hexTable[(val & 0xF)];
	}
	
	/**This is the recommended method
	 * @param actualString
	 * @return
	 */
	public static String convertFromOriginalToUnicode( String actualString )
	{
		StringBuffer unicodeBuff = new StringBuffer();
		try 
		{
			for(int i = 0; i < actualString.length(); i++) 
			{
				char strChar = actualString.charAt(i);
				if ((strChar < 0x0020) || (strChar > 0x007e))
				{
					//					System.out.println("It is a unicode characteer");
					unicodeBuff.append('\\');
					unicodeBuff.append('u');
					unicodeBuff.append(toHex((strChar >> 12) & 0xF));
					unicodeBuff.append(toHex((strChar >>  8) & 0xF));
					unicodeBuff.append(toHex((strChar >>  4) & 0xF));
					unicodeBuff.append(toHex( strChar        & 0xF));
				}
				else 
				{
					unicodeBuff.append(strChar);
				}
			}    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return unicodeBuff.toString();
	}

	/**In Some situation it works fine
	 * @param input
	 * @return
	 */
	public static String nativeToAscii( String input ) 
	{
		if (input == null) 
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() + 60 );
		for (int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			if (c <= 0x7E) 
			{ 
				buffer.append(c);
			}
			else 
			{
				buffer.append("\\u");
				String hex = Integer.toHexString(c);
				for (int j = hex.length(); j < 4; j++ ) 
				{
					buffer.append( '0' );
				}
				buffer.append( hex );
			}
		}
		return buffer.toString();
	}
	
	public static String asciiToNative( String input )
	{
		return convertFromUnicodeToOriginal(input);
	}

	public static String convertFromUnicodeToOriginal( String input ) 
	{
		if (input == null) 
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer( input.length() );
		boolean precedingBackslash = false;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (precedingBackslash) 
			{
				switch (c) 
				{
				case 'f': c = '\f'; break;
				case 'n': c = '\n'; break;
				case 'r': c = '\r'; break;
				case 't': c = '\t'; break;
				case 'u':
					String hex = input.substring( i + 1, i + 5 );
					c = (char) Integer.parseInt(hex, 16 );
					i += 4;
				}
				precedingBackslash = false;
			}
			else 
			{
				precedingBackslash = (c == '\\');
			}
			if (!precedingBackslash) 
			{
				buffer.append(c);
			}
		}
		return buffer.toString();
	}


	public static void main(String[] args) 
	{
		String actualString = "Дата контакта";
		String unicodeStr = convertFromOriginalToUnicode(actualString);
		System.out.println("Unicode Value---->"+unicodeStr);
		System.out.println("Original Value--->"+convertFromUnicodeToOriginal(unicodeStr));
	}

}
