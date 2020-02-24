import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;


/**
 * An example showing how to do class laoding. 
 * 
 * @author RPL <>[]{}
 */
public class ClassLoading {
	public static void main(String[] args) throws Exception {
        CustomClassLoader test = new CustomClassLoader();
        Class c = test.loadClass("Cat");
        Object o = c.newInstance();
        System.out.println( o.toString() );
	}
}

class CustomClassLoader extends ClassLoader {

	private Hashtable classes = new Hashtable();

	public CustomClassLoader() {
		super(CustomClassLoader.class.getClassLoader());
	}

	public Class loadClass(String className) throws ClassNotFoundException {
		return findClass(className);
	}

	public Class findClass(String className) {
		byte classByte[];
		Class result = null;
		result = (Class) classes.get(className);
		if (result != null) {
			return result;
		}

		try {
			return findSystemClass(className);
		} catch (Exception e) {
		}
		
		try {
/*			
 			// use this code to convert a class file to a String array representation:
			String classPath = ((String) ClassLoader.getSystemResource(
					className.replace('.', File.separatorChar) + ".enc")
					.getFile()).substring(0);
			System.out.println("Classloader assembles class '"+ classPath +"'.");
			classByte = loadClassData(classPath);
			System.out.println(Arrays.toString(classByte));
			// ends here
*/	
			// use this for the String array representation:
			String byteDataAsString = "[-54, -2, -70, -66, 0, 0, 0, 49, 0, 24, 7, 0, 2, 1, 0, 3, 67, 97, 116, 7, 0, 4, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 1, 0, 4, 110, 97, 109, 101, 1, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 1, 0, 4, 67, 111, 100, 101, 10, 0, 3, 0, 11, 12, 0, 7, 0, 8, 8, 0, 13, 1, 0, 8, 71, 97, 114, 102, 105, 101, 108, 100, 9, 0, 1, 0, 15, 12, 0, 5, 0, 6, 1, 0, 15, 76, 105, 110, 101, 78, 117, 109, 98, 101, 114, 84, 97, 98, 108, 101, 1, 0, 18, 76, 111, 99, 97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 84, 97, 98, 108, 101, 1, 0, 4, 116, 104, 105, 115, 1, 0, 5, 76, 67, 97, 116, 59, 1, 0, 8, 116, 111, 83, 116, 114, 105, 110, 103, 1, 0, 20, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 1, 0, 8, 67, 97, 116, 46, 106, 97, 118, 97, 0, 33, 0, 1, 0, 3, 0, 0, 0, 1, 0, 1, 0, 5, 0, 6, 0, 0, 0, 2, 0, 1, 0, 7, 0, 8, 0, 1, 0, 9, 0, 0, 0, 61, 0, 2, 0, 1, 0, 0, 0, 11, 42, -73, 0, 10, 42, 18, 12, -75, 0, 14, -79, 0, 0, 0, 2, 0, 16, 0, 0, 0, 14, 0, 3, 0, 0, 0, 2, 0, 4, 0, 4, 0, 10, 0, 2, 0, 17, 0, 0, 0, 12, 0, 1, 0, 0, 0, 11, 0, 18, 0, 19, 0, 0, 0, 1, 0, 20, 0, 21, 0, 1, 0, 9, 0, 0, 0, 47, 0, 1, 0, 1, 0, 0, 0, 5, 42, -76, 0, 14, -80, 0, 0, 0, 2, 0, 16, 0, 0, 0, 6, 0, 1, 0, 0, 0, 7, 0, 17, 0, 0, 0, 12, 0, 1, 0, 0, 0, 5, 0, 18, 0, 19, 0, 0, 0, 1, 0, 22, 0, 0, 0, 2, 0, 23]";
			String[] byteValues = byteDataAsString.substring(1, byteDataAsString.length() - 1).split(",");
			classByte = new byte[byteValues.length];
			for (int i=0, len=classByte.length; i<len; i++) {
				classByte[i] = Byte.valueOf(byteValues[i].trim());     
			}			
			// ends here
			
			result = defineClass(className, classByte, 0, classByte.length,
					null);
			classes.put(className, result);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
/*
	private byte[] loadClassData(String className) throws IOException {
		File f;
		f = new File(className);
		int size = (int) f.length();
		byte buff[] = new byte[size];
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		dis.readFully(buff);
		dis.close();
		return buff;
	}
*/
}