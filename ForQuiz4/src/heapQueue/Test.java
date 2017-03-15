package heapQueue;

import java.util.Random;

public class Test {
	
	public static void main(String[] args){
		System.out.printf("%-20s%-20s%-20s%n", "Size", "Swaps", "Average Swaps");
		
		Random r = new Random();
		PriorityHeapQueue<Integer> q = new PriorityHeapQueue<Integer>();
		for(int i = 0; i < 100; i++){
			q.add(r.nextInt());
		}
		
		System.out.printf("%-20s%-20s%-20s%n", 100, q.swap, (double) q.swap / 100);
		
		r = new Random();
		q = new PriorityHeapQueue<Integer>();
		for(int i = 0; i < 1000; i++){
			q.add(r.nextInt());
		}
		
		System.out.printf("%-20s%-20s%-20s%n", 1000, q.swap, (double) q.swap / 1000);
		
		r = new Random();
		q = new PriorityHeapQueue<Integer>();
		for(int i = 0; i < 10000; i++){
			q.add(r.nextInt());
		}
		
		System.out.printf("%-20s%-20s%-20s%n", 10000, q.swap, (double) q.swap / 10000);
		
		r = new Random();
		q = new PriorityHeapQueue<Integer>();
		for(int i = 0; i < 100000; i++){
			q.add(r.nextInt());
		}
		
		System.out.printf("%-20s%-20s%-20s%n", 100000, q.swap, (double) q.swap / 100000);
		
		r = new Random();
		q = new PriorityHeapQueue<Integer>();
		for(int i = 0; i < 1000000; i++){
			q.add(r.nextInt());
		}
		
		System.out.printf("%-20s%-20s%-20s%n", 1000000, q.swap, (double) q.swap / 1000000);
	}
}
