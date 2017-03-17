package hashTable;

import java.util.ArrayList;
import java.util.Iterator;
import structure5.Association;
import structure5.Map;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.Structure;

public class HashMap<K, V> implements Map<K,V> {
	private ArrayList<Pair<K, V>> table;
	private int load;
	
	private static final double maxLoadFactor = 1.0;
	
	// construct a hashtable with the given initial capacity.  
	public HashMap(int sz) {
		table = new ArrayList<>(sz);
		for (int i=0; i<sz; ++i)
			table.add(null);
		load = 0;
	}
	
	public HashMap(){
		this(997);
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
				spot = (spot+1) % table.size();
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
			else if (p.key.equals(key)) {
				if (p.reserved)
					return null;
				else 
					return p.value;
			}
			else
				spot = (spot+1) % table.size();
		}
	}
	
	public boolean containsKey(K key) {
		int spot = Math.abs(key.hashCode()) % table.size();
		
		while (true) {
			Pair<K, V> p = table.get(spot);
			if (p == null)
				return false;
			else if (p.key.equals(key)) {
				if (p.reserved)
					return false;
				else 
					return true;
			}
			else
				spot = (spot+1) % table.size();
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
				if (p.reserved)
					return null;
				else {
					p.reserved = true;
					load--;  // shrink actual load
					return p.value;
				}
			}
			else
				spot = (spot+1) % table.size();
		}
	}
	
	// replace the table with a larger table, moving everything back in.  There's
	// no fast way to do this, sadly.
	private void rehash() {
		// first, pick a new size.  Should be prime, and at least double the current size.
		ArrayList<Pair<K,V>> newTable = new ArrayList<>(table.size() * 2);
		
		// put all the values currently in the table in the new table.
		for(int i = 0; i < table.size(); i++){
			if(table.get(i) != null){
				K key = table.get(i).key;
				V value = table.get(i).value;
				int spot = Math.abs(key.hashCode()) % newTable.size();
				
				while (true) {
					Pair<K, V> p = newTable.get(spot);
					if (p == null || p.reserved) {
						// found an empty spot
						newTable.set(spot, new Pair<K,V>(key, value));
						load++;
						break;
					}
					else if (p.key.equals(key)) {
						// found a spot occupied by this key.
						V oldValue = p.value;
						p.value = value;
						break;
					}
					else // nothing yet
						spot = (spot+1) % newTable.size();
				}
			}
		}
				
		// put the new table in place
		table = newTable;
	}
	
	
	public void print() {
		System.out.println();
		System.out.println();
		
		for (int i=0; i < table.size(); ++i) {
			Pair<K,V> p = table.get(i);
			if (p == null) 
				System.out.println(i + ": <EMPTY>");
			else {
				System.out.print(i + ": key = " + p.key 
						+ " (" + (Math.abs(p.key.hashCode()) % table.size()) + "), value =  "
						+ p.value);
				if (p.reserved)
					System.out.print(" DEAD");
				System.out.println();
			}
		}
	}

	@Override
	public void clear() {
		table.clear();
	}

	@Override
	public boolean containsValue(V val) {
		Iterator<Pair<K,V>> it = table.iterator();
		while(it.hasNext()){
			Pair<K,V> p = it.next();
			if(p != null && !p.reserved && p.value.equals(val))
				return true;
		}
		
		return false;
	}

	@Override
	public Set<Association<K, V>> entrySet() {
		Iterator<Pair<K,V>> it = table.iterator();
		Set<Association<K,V>> set = new SetList<Association<K,V>>();
		while(it.hasNext()){
			Pair<K,V> p = it.next();
			if(p != null && !p.reserved)
				set.add(new Association<K,V>(p.key, p.value));
		}
		
		return set;
	}

	@Override
	public boolean isEmpty() {
		if(table.isEmpty())
			return true;
		
		//we could have reserved values that make table.isEmpty() return false
		for(int i = 0; i < table.size(); i++){
			Pair<K,V> p = table.get(i);
			if(p != null && !p.reserved)
				return false;
		}
		
		//we have checked the conditions that would make isEmpty() return false, all pairs in the table are reserved or empty
		return true;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new SetList<K>();
		
		Iterator<Pair<K,V>> it = table.iterator();
		while(it.hasNext()){
			Pair<K,V> p = it.next();
			
			if(p != null && !p.reserved)
				set.add(p.key);
		}
		
		return set;
	}

	@Override
	public void putAll(Map<K, V> map) {
		for(K key: map.keySet()){
			put(key, map.get(key));
		}
	}

	@Override
	public int size() {
		return load;
	}

	@Override
	public Structure<V> values() {
		SinglyLinkedList<V> list = new SinglyLinkedList<V>();
		Iterator<Pair<K,V>> it = table.iterator();
		
		while(it.hasNext()){
			Pair<K,V> p = it.next();
			
			if(p != null && !p.reserved)
				list.addFirst(p.value);
		}
		
		return list;
	}
}
