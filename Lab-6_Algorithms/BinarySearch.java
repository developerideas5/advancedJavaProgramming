import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class BinarySearch {

	private Vector v = new Vector();
	
	/**
	 * Constructor
	 */
	public BinarySearch() {
		System.out.println("BinarySearch");
		v.add("bill");
		v.add("larry");
		v.add("linus");
		v.add("richard");
		
		long startTime = System.currentTimeMillis();
		System.out.println(binarySearch(v, 0, 3, "linus"));
		long endTime = System.currentTimeMillis();
		
		System.out.println("Searching took "+(endTime-startTime)+" ms");
	}
	
	public int binarySearch(Vector v, int start, int stop, String key) {
		if (start > stop) {
			return -1;
		}

		int mid = (start + stop) / 2;
		if (key.equals(v.get(mid))) {
			return mid;
		} else if (key.compareTo((String) v.get(mid)) < 0) {
			return binarySearch(v, start, mid - 1, key);
		} else {
			return binarySearch(v, mid + 1, stop, key);
		}
	}
	


	/**
	 * This method should load the dictionary from file and store it either in
	 * two Lists or a Map
	 * 
	 * @param fileName
	 */
	private void loadLexiconFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;
				StringTokenizer st = new StringTokenizer(words, "=");
				String en = st.nextToken();
				String de = st.nextToken(); 
				// maybe some of your code goes here
				
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done loading file");
	}
	
	public static void main(String[] args) {	
		BinarySearch bs = new BinarySearch();
	}
	
}
