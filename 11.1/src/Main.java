import java.util.Random;

public class Main {

	public static void main(String[] args) {
		HomeworkVector<Integer> vec = new HomeworkVector<Integer>();
		Random r = new Random();
		long start, end, time1, time2;
		int target;
		
		System.out.printf("%-15s%-15s%-15s%n", "Size", "Time1", "Time2");
		
		for(int i = 0; i < 10; i++)
			vec.add(r.nextInt(100));
		
		target = vec.get(vec.size() - 1);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time1 = (end - start);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time2 = (end - start);
		
		System.out.printf("%-15s%-15s%-15s%n", vec.size(), time1, time2);
		
		for(int i = 0; i < 90; i++)
			vec.add(r.nextInt(100));
		
		target = vec.get(vec.size() - 1);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time1 = (end - start);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time2 = (end - start);
		
		System.out.printf("%-15s%-15s%-15s%n", vec.size(), time1, time2);
		
		for(int i = 0; i < 900; i++)
			vec.add(r.nextInt(100));
		
		target = vec.get(vec.size() - 1);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time1 = (end - start);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time2 = (end - start);
		
		System.out.printf("%-15s%-15s%-15s%n", vec.size(), time1, time2);
		
		for(int i = 0; i < 9000; i++)
			vec.add(r.nextInt(100));
		
		target = vec.get(vec.size() - 1);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time1 = (end - start);
		
		start = System.nanoTime();
		vec.contains(target);
		end = System.nanoTime();
		time2 = (end - start);
		
		System.out.printf("%-15s%-15s%-15s%n", vec.size(), time1, time2);
	}

}
