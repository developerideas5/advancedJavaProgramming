import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A simple example showing demonstrating reflection. 
 * - it lists all methods and fields, except private ones
 * 
 * @author RPL <>[]{}
 */
public class Reflection {
	public String name = "null";
	public String number = "-1";
	
	public static void main(String[] args) throws Exception {
		String classToLoad = readLine("Enter class name (e.g. Reflection): ");

		// get the class and list all methods and fields:
		Class c = Class.forName( classToLoad );		
		listAllMethods( c );		
		listAllFields( c );
		
		
		// create an instance by calling the default constructor
		Object o = c.newInstance();
		
		// set a field of the object
		String fieldToSet = readLine("Enter field name to set (e.g. name): ");
		String valueToSet = readLine("Enter the value the field should be set to: ");
		Field f = c.getField(fieldToSet);
		f.set(o, valueToSet);
		System.out.println("o.toString(): " + o.toString());
		
		// call a method of the object
		String methodToCall = readLine("Enter method name call (e.g. getName or toString): ");
		Method m = c.getMethod(methodToCall, null);	// use Class[] instead of null to indicate which method signature you want
		Object retValue = m.invoke(o, null);		// use Object[] instead of null for parameters
		System.out.println( "Method returned: " + (String)retValue );
	}

	public static void listAllFields(Class c) {
		System.out.println( "Fields of class " + c.getName() +  ":" );
		//Field[] fields = c.getFields();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields ) {
			field.setAccessible(true);
			System.out.println( field.getName() );
		}
	}

	private static void listAllMethods(Class c) {
		System.out.println( "Methods of class " + c.getName() +  ":" );
		Method[] meths = c.getMethods();
		for (Method meth : meths ) {
			System.out.println( meth.getName() );
		}
	}

	private static String readLine(String msg) throws IOException {
		System.out.print(msg);
		String classToLoad;
		classToLoad = new BufferedReader(
				new InputStreamReader(System.in)).readLine();
		return classToLoad;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "name: " + name + ", number: " + number;
	}
}
