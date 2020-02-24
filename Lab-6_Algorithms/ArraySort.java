import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ArraySort {

	public ArraySort() {
		System.out.println("ArraySort");
		
		int[] arrOfInts = {5,2,7,3,1,8,23,12,45,55};
		Arrays.sort( arrOfInts );
		
		printArray(arrOfInts);
	}
	

	private void printArray(int[] arrOfInts) {
		for (int i = 0; i < arrOfInts.length; i++) {
			System.out.print( arrOfInts[i] +"," );
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
		ArraySort as = new ArraySort();
	}

}
