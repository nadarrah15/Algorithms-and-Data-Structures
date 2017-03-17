package avlPriorityQueue;

public class Test {

	public static void main(String[] args) {
		PriorityTreeQueue<Integer> q = new PriorityTreeQueue<Integer>();
		
		q.add(10);
		q.add(9);
		q.add(8);
		System.out.println(q.remove());
		q.add(7);
		System.out.println(q.remove());
		q.add(11);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
	}

}
