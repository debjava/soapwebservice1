import javax.management.ReflectionException;



public class Test 
{
	public void execute()
	{
		A a1 = new A();
		
		AAnnot1 ano = a1.getClass().getAnnotation(AAnnot1.class);
		a1.doIt();
//		Throwable t = new Throwable(); 
		StackTraceElement[] elmts = Thread.currentThread().getStackTrace();
		
//		Class<?> clazz = Reflection.getCallerClass(depth);

		
		System.out.println("Total Length ::: "+elmts.length);
		for( int i = 0 ; i < elmts.length ; i++ )
		{
			System.out.println( "Class Name ::: "+elmts[i].getClassName());
			System.out.println( "File Name ::: "+elmts[i].getFileName());
			System.out.println( "Method Name ::: "+elmts[i].getMethodName());
		}
		
		String callerInfoString = CallerInfo.getCallerInfo(); 
		System.out.println(callerInfoString);
		
	}
	public static void main(String[] args) throws Exception
	{
		new Test().execute();
		String callerInfoString = CallerInfo.getCallerInfo(); 
		System.out.println(callerInfoString);
		
//		sun.reflect.Reflection.getCallerClass(2).getName()); 
		
		
	}

}
