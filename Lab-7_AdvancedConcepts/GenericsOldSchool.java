import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An example showing the benefit of generics. 
 * 
 * @author RPL <>[]{}
 */
public class GenericsOldSchool {
	public static void main(String[] args) {
	List<ComparabelOS> numbers = new ArrayList<ComparabelOS>();
	numbers.add( new MyIntOS(3) );
	numbers.add( new MyStringOS("hi") );
	numbers.add( new MyIntOS(7) );
	System.out.println( max(numbers) );
	}

	public static ComparabelOS max(List<ComparabelOS> list) {
		Iterator<ComparabelOS> i = list.iterator();
		ComparabelOS result = i.next();
		while (i.hasNext()) {
			ComparabelOS t = i.next();
			if (t.compare(result) > 0)
			result = t;
		}
		return result;
	}
}

class MyIntOS implements ComparabelOS {
	private Integer i;
	
	public MyIntOS(Integer i) {
		this.i = i;
	}

	public int compare(ComparabelOS o) {
		if ( this.i > ((MyIntOS)o).i ) {
			return 1;			
		} else {
			return -1;			
		}
	}
	
	public String toString() {
		return "" + this.i;
	}
}

class MyStringOS implements ComparabelOS {
	private String s;
	
	public MyStringOS(String s) {
		this.s = s;
	}
	
	public int compare(ComparabelOS o) {
		if ( this.s.charAt(0) > ((MyStringOS)o).s.charAt(0) ) {
			return 1;			
		} else {
			return -1;			
		}
	}

	public String toString() {
		return "" + this.s;
	}
}


interface ComparabelOS {
	int compare(ComparabelOS o);
}