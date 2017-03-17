package hashTable;

public class Test {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		
		for(int i = 1; i <= 3; i++)
			System.out.println(map.get(i));
		
		System.out.println(map.containsKey(1));
		System.out.println(map.containsValue("One"));
		map.remove(1);
		System.out.println(map.containsKey(1));
		System.out.println(map.containsValue("One"));
		System.out.println(map.get(1));
	}

}
