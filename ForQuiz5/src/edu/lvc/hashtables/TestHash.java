package edu.lvc.hashtables;

public class TestHash {

	public static void main(String[] args) {
		
		BubbaHashMap<String, Integer> table = new BubbaHashMap<>(13, 2);
		
		table.put("Hi", 10);
		table.put("There", 20);
		table.put("This", 30);
		table.put("Is", 40);
		table.put("A", 50);
		table.put("Simple", 60);
		table.put("simple", 70);
		table.put("example", 80);
		table.put("Of", 90);
		table.put("a", 100);
		table.put("A", 110);
		table.put("Hash", 120);
		table.put("Table", 130);
		
		table.print();
		
		if (table.contains("Table"))
			System.out.println("\"Table\" is present");
		
		if (table.contains("Is"))
			System.out.println("\"Is\" is present");
		
		table.remove("Is");
		
		table.print();

		if (table.contains("Table"))
			System.out.println("\"Table\" is present");
		
		if (table.contains("Is"))
			System.out.println("\"Is\" is present");
		
		table.put("Bin", 1313);
		
		table.print();
	}

}
