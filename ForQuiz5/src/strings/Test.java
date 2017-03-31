package strings;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String a = "aaaaaaa";
		String b = "aaaab";
		
		long start = System.nanoTime();
		hashCompare(a, b);
		long end = System.nanoTime();
		
		System.out.println((end - start) + " ms");
		
		a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		start = System.nanoTime();
		hashCompare(a, b);
		end = System.nanoTime();
		
		System.out.println((end - start)  + " ms");
		
		a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		start = System.nanoTime();
		hashCompare(a, b);
		end = System.nanoTime();
		
		System.out.println((end - start)  + " ms");
	}

	static boolean hashCompare(String root, String sub){
		for(int i = 0; i < root.length() - sub.length(); i++){
			if(root.substring(i, i + (sub.length())).hashCode() == sub.hashCode())
				return true;
		}
		
		return false;
	}
}
