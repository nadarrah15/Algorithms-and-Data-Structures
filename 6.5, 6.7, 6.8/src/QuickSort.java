
public class QuickSort {
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length-1);
	}
	
	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) 
			return;
		
		int pivot = partition(array, left, right);
		quickSort(array, left, pivot-1);
		quickSort(array, pivot+1, right);
	}
	
	
	private static int partition(int[] array, int left, int right) {
		// choose a pivot value, swap it into the leftmost position
		// for now, just choose the leftmost value
		// median of three
		int p = pivot(array, left, right);
		swap(array, left, p);
		int below = left;
		int above = right+1;
		while (true) {
			// pre: all elements from below to the left (and bigger than the pivot) are
			// <= the pivot.  All entries from above to the right are >= the pivot
			
			// step 1: move below up as far as possible.  Why do we have to test for below < above?
			do {
				below++;
			} while (below < above && array[below] < array[left]);
				
			
			// step 2: move above down as far as possible.  Why DON'T we test for above > below?
			do {
				above--;
			} while (array[above] > array[left]);
				
			// if there is space between above and below, we have found two elements that
			// we should swap.  If above and below have met, then we are done, there are 
			// no more items out of position.
			if (below < above)
				swap(array, below, above);
			else
				break;
			
			// post: above and below are closer together.  Halt when above >= below			
		}
		
		// finally, put the pivot into position.  Note that above always points at a
		// valid spot for the pivot, since it went down past all larger elements.
		swap(array, left, above);
		return above;
	}
		
	static private int pivot(int[] array, int left, int right) {
		int mid = (left + right)/2;
		if (array[left] <= array[mid] && array[mid] <= array[right])
			return mid;
		else if (array[right] <= array[mid] && array[mid] <= array[left])
			return mid;
		else if (array[left] <= array[right] && array[right] <= array[mid])
			return right;
		else if (array[mid] <= array[right] && array[right] <= array[left])
			return right;
		else
			return left;
	}
}
