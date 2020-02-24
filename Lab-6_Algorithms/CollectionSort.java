import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class CollectionSort {

	public CollectionSort() {
		System.out.println("CollectionSort");
		
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add( new Dog( 42, "Lassie") );
		dogs.add( new Dog( 5, "Rantanplan") );
		dogs.add( new Dog( 32, "Hans-Werner") );
		
		listDogs(dogs);		
		Collections.sort( dogs );
		listDogs(dogs);				
	}

	
	private void listDogs(ArrayList<Dog> dogs) {
		for (Dog dog : dogs) {
			System.out.println( dog );
		}
	}


	public static void main(String[] args) {
		CollectionSort cs = new CollectionSort();
	}

}
