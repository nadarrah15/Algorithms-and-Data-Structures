import java.util.Random;

public class MiddlePartition {
	
	static int parCount = 0;

	public static void main(String[] args) {
		System.out.printf("%-20s%-20s%-20s%-20s%n", "Sort", "Size", "Comparison", "Time");

		String[] s = new String[10];
		for (int i = 0; i < s.length; i++) {
			s[i] = randomString();
		}
		long start = System.nanoTime();
		int count = quickSort(s, s.length);
		long end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n", "quickSort", s.length, count, (end - start) / 1000000 + "ms");

		s = new String[100];
		for (int i = 0; i < s.length; i++) {
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = quickSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n", "quickSort", s.length, count, (end - start) / 1000000 + "ms");

		s = new String[1000];
		for (int i = 0; i < s.length; i++) {
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = quickSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n", "quickSort", s.length, count, (end - start) / 1000000 + "ms");

		s = new String[10000];
		for (int i = 0; i < s.length; i++) {
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = quickSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n", "quickSort", s.length, count, (end - start) / 1000000 + "ms");
	}

	static String randomString() {
		Random gen = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder sb = new StringBuilder();
		sb.append("aaaaa");

		for (int j = 0; j < 95; j++) {

			sb.append(alphabet.charAt(gen.nextInt(alphabet.length())));
		}

		return sb.toString();
	}

	static void swap(String data[], int i, int j) {
		String temp;
		temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	static int partition(String data[], int left, int right) {

		while (true) {

			// move right "pointer" toward left
			parCount++;
			while (left < right && data[left].compareTo(data[right]) < 0) {
				right--;
				parCount++;
			}
			parCount++;
			if (left < right)
				swap(data, left++, right);
			else
				return left;
			// move left pointer toward right
			parCount++;
			while (left < right && data[left].compareTo(data[right]) < 0) {
				parCount++;
				left++;
			}
			parCount++;
			if (left < right)
				swap(data, left, right--);
			else
				return right;
		}
	}

	static int quickSort(String data[], int n) {
		parCount = 0;
		//Change left to middle
		quickSortRecursive(data, data.length / 2, n - 1);
		return parCount;
	}

	static void quickSortRecursive(String data[], int left, int right) {

		int pivot;

		// the final location of the leftmost value
		parCount++;
		if (left >= right)
			return;
		pivot = partition(data, left, right);

		quickSortRecursive(data, left, pivot - 1);
		quickSortRecursive(data, pivot + 1, right);
	}
}
