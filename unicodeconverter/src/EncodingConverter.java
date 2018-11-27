import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public class EncodingConverter 
{
	public static void convert(String infile, String outfile, String from,String to)
	throws IOException, UnsupportedEncodingException 
	{
		InputStream in;
		if (infile != null)
			in = new FileInputStream(infile);
		else
			in = System.in;
		OutputStream out;
		if (outfile != null)
			out = new FileOutputStream(outfile);
		else
			out = System.out;

		// Use default encoding if no encoding is specified.
		if (from == null)
			from = System.getProperty("file.encoding");
		if (to == null)
			to = System.getProperty("file.encoding");

		// Set up character streams.
		Reader r = new BufferedReader(new InputStreamReader(in, from));
		Writer w = new BufferedWriter(new OutputStreamWriter(out, to));

		File file = new File(infile);
		int length = (int)file.length();

		char[] buffer = new char[length];
		int len;
		while ((len = r.read(buffer)) != -1)
			w.write(buffer, 0, len); 
		r.close(); 
		w.close(); 
	}


	public static void main(String[] args) throws Exception
	{
		String filePath = "D:/Test1.txt";//"data/props/Test1.txt";
		String filePath1 = "D:/Test3.txt";
//		convert(filePath, filePath1, "UNICODE", "UTF-8");
//		convert(filePath, "D:/Test_ascii.txt", "UNICODE", "US-ASCII");
//		convert(filePath, "D:/Test_iso8859.txt", "UNICODE", "8859_1");//ISO-8859-1 US-ASCII KOI8-R
//		convert(filePath, "D:/Test_koi8.txt", "UNICODE", "KOI8-R");
		
	}

}
