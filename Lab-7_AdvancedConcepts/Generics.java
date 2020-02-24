import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A little more complex example demonstrating generics. 
 * 
 * @author RPL <>[]{}
 */
public class Generics {
	public static void main(String[] args) {
		List<MyInt> numbers = new ArrayList<MyInt>();
		numbers.add( new MyInt(3) );
		numbers.add( new MyInt(1) );
		numbers.add( new MyInt(5) );
		System.out.println( max(numbers) );
	}

	public static <T extends Comparabel<T>> T max(List<T> list) {
		Iterator<T> i = list.iterator();
		T result = i.next();
		while (i.hasNext()) {
			T t = i.next();
			if (t.compare(result) > 0)
			result = t;
		}
		return result;
	}
}

class MyInt implements Comparabel {
	private Integer i;
	
	public MyInt(Integer i) {
		this.i = i;
	}

	public int compare(Object o) {
		if ( this.i > ((MyInt)o).i ) {
			return 1;			
		} else {
			return -1;			
		}
	}
	
	public String toString() {
		return "" + this.i;
	}
}

class MyString implements Comparabel {
	private String s;
	
	public MyString(String s) {
		this.s = s;
	}
	
	public int compare(Object o) {
		if ( this.s.charAt(0) > ((MyString)o).s.charAt(0) ) {
			return 1;			
		} else {
			return -1;			
		}
	}

	public String toString() {
		return "" + this.s;
	}
}


interface Comparabel<T> {
	int compare(T o);
}