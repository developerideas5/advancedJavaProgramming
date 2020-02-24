import java.lang.reflect.*;

public class SimpleReflection {

	public static void main(String[] args) throws Exception {
		
		// load class:
		Class c = Class.forName("SecretClass");
		
		// list all fields:
		System.out.println("Public Fields:");
		Field[] fields = c.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println( fields[i].getName() );
		}

		// list all methods:
		System.out.println("Public Methods:");
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println( methods[i].getName() );
		}
		
		// create an instance:
		Object o = c.newInstance();

		// access a field:
		System.out.println( fields[0].get( o ) );

		// call a method:
		methods[0].invoke( o, null );

	}

}
