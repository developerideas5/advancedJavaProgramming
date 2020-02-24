import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A simple example demonstrating serialization. 
 * - run the program as is, check the file dog.ser 
 * - next change the serialVersionUID of the dog class,
 *   comment the "serializeDog(dog1);" in the main() method
 *   and run the program again.  You get an InvalidClassException.
 * 
 * @author RPL <>[]{}
 */
public class Serialization {
	public static void main(String[] args) throws Exception {
		Dog dog1 = new Dog("Lassie", 65);
		serializeDog(dog1);
		Dog dog2 = deserializeDog();
		System.out.println("Name of dog2: " + dog2.getName());
	}

	private static Dog deserializeDog() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("dog.ser"));
		Dog d = (Dog)ois.readObject();
		ois.close();
		return d;
	}

	private static void serializeDog(Dog d) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("dog.ser"));
		oos.writeObject( d );
		oos.close();
	}
}

class Dog implements Serializable {

	private static final long serialVersionUID = 14110006122013L;

	private String name;

	private int age;

	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Dog() {
		super();
		this.name = "null";
		this.age = -1;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name + "," + age;
	}
}
