package edu.lvc.hashtables;

import java.util.ArrayList;

public class BubbaHashMap<K, V> {
	private ArrayList<Pair<K, V>> table;
	private int[] bucketData;
	private int bucketSize;
	private int load;
	
	private static final double maxLoadFactor = 0.5;
	
	// construct a hashtable with the given initial capacity.  
	public BubbaHashMap(int sz, int bucketSize) {
		// find the next prime larger than sz, use that for the table size.
		sz = Primes.nextPrime(sz);
		table = new ArrayList<>(sz);
		bucketData = new int[sz];
		this.bucketSize = bucketSize;
		for (int i=0; i<sz; ++i)
			table.add(null);
		load = 0;
	}
	
	// put the value into the table.  If key is already present, update the associated value.
	// return the value that was already present, or null if the key is new.
	public V put(K key, V value) {
		if (load + 1 > table.size() * maxLoadFactor)
			rehash();
		
		// get the bucket we must insert into
		int bucket = Math.abs(key.hashCode()) % table.size();
		
		// step 1.  Probe forward as usual with linear probing to
		// find a spot for this key

		int index = bucket;
		// probe forward until we find an available spot.
		while (true) {
			Pair<K, V> p = table.get(index);
			if (p == null || p.reserved) {
				// found an empty spot
				break;
			}
			else if (p.key.equals(key)) {
				// found a spot occupied by this key.
				V oldValue = p.value;
				p.value = value;
				return oldValue;
			}
			else // nothing yet
				index = (index+1) % table.size();
		}
		
		// Now index points at a spot we could place our new value in.
		// BUT BUT BUT... is it in our bucket?


		int sample = table.size() + (index - (bucketSize - 1)) % table.size();
		while (dist(bucket, index) > bucketSize) {
			// find a spot we can move backwards toward.
			int slot = 0;
			boolean isSwapped = false;
			
			while(slot < bucketSize - 1){
				int newIndex = (sample + slot) % table.size();
				Pair<K, V> p = table.get(newIndex);
				int newBucket = Math.abs(p.key.hashCode()) % table.size();
				
				if(dist(newBucket, index) < bucketSize){
					table.set(index, p);
					markSlot(newBucket, dist(sample, newIndex));
					
					clearSlot(newBucket, slot);
					index = newIndex;
					isSwapped = true;
					break;
				}
				slot++;
			}
			if(!isSwapped){
				rehash();
				break;
			}
			else
				sample = table.size() + (index - (bucketSize - 1)) % table.size();
		}
		
		// insert key, value into spot index, update bookkeeping
		table.set(index, new Pair<K,V>(key, value));
		markSlot(bucket, bucketSize - 1 - dist(bucket, index));
		load++;
		return value;
	}

	private int dist(int bucket, int index){
		if(bucket > index)
			return table.size() - bucket + index;
		else
			return index - bucket;
			
	}
	
	//find and return the value associated with key.  Return null if the
	// key is not present.
	public V get(K key) {
		if(load == 0)
			return null;
		
		int bucket = Math.abs(key.hashCode()) % table.size();
		
		for (int slot = bucketSize - 1; slot >= 0; --slot) {
			int index = (bucket + bucketSize - 1 - slot) % table.size();
			Pair<K,V> p = table.get(index);
			if (isOccupied(bucket, slot) && p.key.equals(key))
				return p.value;
		}
		return null;
	}
	
	public boolean contains(K key) {
		if(load == 0)
			return false;
		
		int bucket = Math.abs(key.hashCode()) % table.size();
		
		for (int slot = bucketSize - 1; slot >= 0; --slot) {
			int index = (bucket + bucketSize - 1 - slot) % table.size();
			if (isOccupied(bucket, slot) && table.get(index).key.equals(key))
				return true;
		}
		return false;
		
	}
	
	// remove the item with key "key" from the table, returning the value associated with
	// the key.  If the key does not appear in the table, return null.  
	public V remove(K key) {
		if(load == 0)
			return null;
		
		int bucket = Math.abs(key.hashCode()) % table.size();
		
		// probe forward until we find an available spot.
		for (int slot = bucketSize - 1; slot >= 0; --slot) {
			int index = (bucket + bucketSize - 1 - slot) % table.size();
			Pair<K,V> p = table.get(index);
			if (isOccupied(bucket, slot) && p.key.equals(key)){
				p.reserved = true;
				load--;
				clearSlot(bucket, slot);
				return p.value;
			}
		}
		
		return null;
	}
	
	// replace the table with a larger table, moving everything back in.  There's
	// no fast way to do this, sadly.
	private void rehash() {
		// At least double the size of the table.
		BubbaHashMap<K,V> newTable = new BubbaHashMap<>(table.size()*2, bucketSize);
		
		
		// put all the values currently in the table in the new table
		for (int i=0; i<table.size(); ++i) {
			Pair<K, V> p = table.get(i);
			if (p != null && !p.reserved)
				newTable.put(p.key, p.value);
		}
		
		// hijack the data from newTable.  Then let netTable be GC'd.
		this.table = newTable.table;
		this.bucketData = newTable.bucketData;
		this.load = newTable.load;
		this.bucketSize = newTable.bucketSize;
	}
	
	// return true if the given slot in the given bucket is occupied
	private boolean isOccupied(int bucket, int slot) {
		return ((bucketData[bucket] & (1 << slot)) != 0);
	}
	
	// mark this slot in this bucket as unoccupied
	private void clearSlot(int bucket, int slot) {
		// we need a bitwise logical operation that will change the slot'th entry in our int to 0, but
		// leave the rest of them alone.
		bucketData[bucket] &= ~(1 << slot);
	}
	
	// mark this slot in this bucket as occupied
	private void markSlot(int bucket, int slot) {
		// we need a bitwise logical operation that will change the slot'th entry in our int to 1
		bucketData[bucket] |= (1 << slot);
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
}
