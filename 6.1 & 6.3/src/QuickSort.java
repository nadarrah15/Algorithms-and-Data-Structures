import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		System.out.printf("%-20s%-20s%-20s%-20s%n", "Sort", "Size", "Comparison", "Time");
		
		String[] s = new String[10];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		long start = System.nanoTime();
		int count = bubbleSort(s, s.length);
		long end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","bubbleSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[100];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = bubbleSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","bubbleSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[1000];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = bubbleSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","bubbleSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[10000];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = bubbleSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","bubbleSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[10];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = insertionSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","insertionSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[100];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = insertionSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","insertionSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[1000];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = insertionSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","insertionSort", s.length, count,(end - start) / 1000000 + "ms");
		
		s = new String[10000];
		for(int i = 0; i < s.length; i++){
			s[i] = randomString();
		}
		start = System.nanoTime();
		count = insertionSort(s, s.length);
		end = System.nanoTime();
		System.out.printf("%-20s%-20s%-20s%-20s%n","insertionSort", s.length, count,(end - start) / 1000000 + "ms");
	}
	
	static String randomString(){
		Random gen = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("aaaaa");
		
		for(int j = 0; j < 95; j++){
			
			sb.append(alphabet.charAt(gen.nextInt(alphabet.length())));
		}
		
		return sb.toString();
	}
	
	static void swap(String data[], int i, int j){
		String temp;
		temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	static int bubbleSort(String s[], int n){
		
		int numSorted = 0;
		int index;
		int count = 0;
		
		while(numSorted < n){
			for(index = 1; index < n - numSorted; index++){

				count++;
				if(s[index - 1].compareToIgnoreCase(s[index]) > 0){
					swap(s, index - 1, index);
				}
			}
			
			numSorted++;
		}
		
		return count;
	}
	
	static int insertionSort(String[] s, int n){
		
		int numSorted = 1;
		int index;
		int count = 0;
		
		while(numSorted < n){
			
			String temp = s[numSorted];
			
			for(index = numSorted; index > 0; index--){
				count++;
				
				if(temp.compareTo(s[index - 1]) < 0){
					s[index] = s[index - 1];
				}
				else {
					break;
				}
			}
			
			s[index] = temp;
			numSorted++;
		}
		
		return count;
	}
}
