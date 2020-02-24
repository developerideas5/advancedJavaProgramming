import java.awt.HeadlessException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.swing.JFrame;

/**
 * An example showing the dynamic proxy. 
 * 
 * @author RPL <>[]{}
 */
public class DynamicProxy {
	public static void main(String[] args) {
		// first work with real object:
		RealObject ro = new RealObject();
		ro.setName("real");
		System.out.println( "Real name: " + ro.getName() );
		
		// second use proxy:
		RealObject ro2 = new RealObject();
		RealObjectInterface po = 
			(RealObjectInterface) java.lang.reflect.Proxy.newProxyInstance(
					RealObject.class.getClassLoader(),
				new Class[] { RealObjectInterface.class }, 
				new ProxyObject(ro2)
			);
		po.setName("real");
		System.out.println( "Proxy name: " + po.getName() );
	}
}

class ProxyObject implements InvocationHandler {
	private final RealObject ro;
	
	public ProxyObject(RealObject ro) {
		this.ro = ro;
	}

	public Object invoke(Object target, Method method, Object[] arguments) throws Throwable {
		Object o = null;
		try {

			// here we can add any method we want
			// before executing the actual method:
			//beforeMethodCall(method, arguments);

			// now call the method:
			// comment the next line, if you do not want calls to be forwarded:
			o = method.invoke(ro, arguments);

			// here we can add any method we want
			// after executing the actual method:
			//afterMethodCall(method, arguments);
			if ( method.getName().equals("getName")) {
				o = "hi from proxy";
			}

			// now return as if nothing happened:
			return o;
		} catch (Exception e) {
			// reconvert nested application exceptions
			throw e;
		}
	}
	
}

class RealObject implements RealObjectInterface {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

interface RealObjectInterface {
	public String getName();
	public void setName(String name);
}
