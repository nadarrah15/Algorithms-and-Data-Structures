package hashMap;

import java.util.ArrayList;

public class HashMap<K, V> {
	private ArrayList<Pair<K, V>> table;
	private int load;
	
	private static final double maxLoadFactor = 0.5;
	
	// construct a hashtable with the given initial capacity.  
	public HashMap(int sz) {
		table = new ArrayList<>(sz);
		for (int i=0; i<sz; ++i)
			table.add(null);
		load = 0;
	}
	
	// put the value into the table.  If key is already present, update the associated value.
	// return the value that was already present, or null if the key is new.
	public V put(K key, V value) {
		if (load + 1 > table.size() * maxLoadFactor)
			rehash();
		
		// get the preferred spot
		int spot = Math.abs(key.hashCode()) % table.size();
		
		// probe forward until we find an available spot.
		while (true) {
			Pair<K, V> p = table.get(spot);
			if (p == null || p.reserved) {
				// found an empty spot
				table.set(spot, new Pair<K,V>(key, value));
				load++;
				return null;
			}
			else if (p.key.equals(key)) {
				// found a spot occupied by this key.
				V oldValue = p.value;
				p.value = value;
				return oldValue;
			}
			else // nothing yet
				spot = (spot + 1) % table.size();
		}
	}

	//find and return the value associated with key.  Return null if the
	// key is not present.
	public V get(K key) {
		int spot = Math.abs(key.hashCode()) % table.size();
		
		while (true) {
			Pair<K, V> p = table.get(spot);
			if (p == null)
				return null;
			else if (p.key.equals(key))
				return p.value;
			else
				spot = (spot + 1) % table.size();
		}
	}
	
	// remove the item with key "key" from the table, returning the value associated with
	// the key.  If the key does not appear in the table, return null.  
	public V remove(K key) {
		int spot = Math.abs(key.hashCode()) % table.size();
		
		while (true) {
			Pair<K, V> p = table.get(spot);
			if (p == null)
				return null;
			else if (p.key.equals(key)) {
				p.reserved = true;
				load--;  // shrink actual load
				return p.value;
			}
			else
				spot = (spot + 1) % table.size();
		}
	}
	
	// replace the table with a larger table, moving everything back in.  There's
	// no fast way to do this, sadly.
	private void rehash() {
		// first, pick a new size.  Should be prime, and at least double the current size.
		ArrayList<Pair<K,V>> newTable = new ArrayList<>(table.size() * 2);
		
		// put all the values currently in the table in the new table
		
		// TODO impement this step.
		
		// put the new table in place
		table = newTable;
	}
	
	public void print() {
		for (int i=0; i < table.size(); ++i) {
			Pair<K,V> p = table.get(i);
			if (p == null) 
				System.out.println(i + ": <EMPTY>");
			else {
				System.out.println(i + ": key = " + p.key 
						+ " (" + p.key.hashCode() % table.size() + "), value =  "
						+ p.value);
			}
		}
	}
}
