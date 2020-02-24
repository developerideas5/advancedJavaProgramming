import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

public class CustomClassLoader2 extends ClassLoader {

	private Hashtable classes = new Hashtable();

	public CustomClassLoader2() {
		super(CustomClassLoader2.class.getClassLoader());
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
			String classPath = ((String) ClassLoader.getSystemResource(
					className.replace('.', File.separatorChar) + ".enc")
					.getFile()).substring(0);
			System.out.println("Classloader assembles class '"+ classPath +"'.");
			classByte = loadClassData(classPath);
			result = defineClass(className, classByte, 0, classByte.length,
					null);
			classes.put(className, result);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

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

}