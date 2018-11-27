
public class CallerInfo 
{
	 public static String getCallerInfo() 
	 {
		 String callerInfo = "DefaultName-callerUnknown";
		 Thread thread = Thread.currentThread();
		 StackTraceElement[] framesArray = thread.getStackTrace();
		 for (StackTraceElement stackFrame : framesArray) 
		 {         
			 if (!stackFrame.getClassName().equals("java.lang.Thread") && !stackFrame.getClassName().equals("CallerInfo"))
			 {
				 String fileName = stackFrame.getFileName() != null ? stackFrame.getFileName()    : "Unknown"; 
				 StringBuilder sb = new StringBuilder(stackFrame.getMethodName());
				 sb.append('(');
				 sb.append(fileName); 
				 sb.append(':');   
				 sb.append(stackFrame.getLineNumber()); 
				 sb.append(')');  
				 callerInfo = sb.toString(); 
				 break;
			 }
		 }
		 return callerInfo;
	 }
	      
}
