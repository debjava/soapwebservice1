import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;


public class Test 
{

	public static void main(String[] args) throws Exception
	{
//		String ss = "V�lkommen p� frukostm�te i molnet Anders �stberg";//"Lars�ke ��� ���";
//		String ss = "frukost i molnet ";//"Lars�ke ��� ���";
		
		String ss = "V�lkommen p� frukostm�te";
		CharsetDetector cd = new CharsetDetector();
		cd.setText(ss.getBytes());
		CharsetMatch cm = cd.detect();
		String language = cm.getLanguage();
		String name = cm.getName();
		System.out.println(name+"---"+language);
//		Charset cs = CharsetICU.forNameICU("ISO-8859-9");
		
		String s1 = cd.getString(ss.getBytes(), name);
		System.out.println(s1);
		
		
//		Transliterator
		
		
		
	}

}
