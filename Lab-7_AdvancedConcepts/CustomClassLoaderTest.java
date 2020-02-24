import java.lang.reflect.*;

public class CustomClassLoaderTest {

	public static void main(String [] args) throws Exception{
        CustomClassLoader test = new CustomClassLoader();
        Class c = test.loadClass("CallSecretClass");
        Object o = c.newInstance();
     }
}