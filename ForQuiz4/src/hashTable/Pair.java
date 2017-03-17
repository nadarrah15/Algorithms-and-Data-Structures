package hashTable;

public class Pair<K, V> {

	// leave these at package-level access.  This class is not an abstraction; it's a 
	// data structure detail.
	K key;
	V value;
	boolean reserved;
	
	public Pair(K k, V v) {
		key = k;
		value = v;
		reserved = false;
	}
}
