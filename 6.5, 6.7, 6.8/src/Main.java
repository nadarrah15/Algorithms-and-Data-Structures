import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TelevisionComparator<Television> c = new TelevisionComparator<Television>();
		Scanner in = new Scanner(System.in);
		/*while (true) {
			try {
				System.out.println("Sort array by size, price, or alphabetically?");
				String input = in.nextLine();
				c.setComparator(input);
				break;
			} catch (Exception e) {
				System.out.println("Sorry, I didnt quite catch that. Please try again.");
			}
		}*/

		ArrayList<Television> arr = new ArrayList<Television>();

		arr.add(new Television("Sharp", "Px45", 50, 39.45, 1, 1080, 720, 1000));
		arr.add(new Television("Sony", "Px45", 40, 39.45, 2, 1080, 720, 1000));
		arr.add(new Television("Insignia", "Px45", 45, 39.45, 3, 1080, 720, 1000));

		for(Television elem: arr){
			System.out.println(x);
		}
	}

	public static void insertionSort(ArrayList<Television> data, Comparator<Television> c)
	{
		int numSorted = 1; // number of values in place
		int index; // general index
		int n = data.size(); // length of the array
		while (numSorted < n) {
			// take the first unsorted value
			Television temp = data.get(numSorted);
			// ...and insert it among the sorted:
			for (index = numSorted; index > 0; index--) {
				if (c.compare(temp, data.get(index - 1)) < 0) {
					data.set(index, data.get(index - 1));
				} else {
					break;
				}
			}
			// reinsert value
			data.set(index, temp);
			numSorted++;
		}
	}
}
