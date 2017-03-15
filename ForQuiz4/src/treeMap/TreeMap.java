package treeMap;

import trees.SearchTree;

public class TreeMap<K extends Comparable<K>, V> {

	private class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K,V>>{

		K key;
		V val;
		
		public Pair(K key, V val){
			this.key = key;
			this.val = val;
		}
		
		@Override
		public int compareTo(Pair<K, V> o) {
			return this.key.compareTo(o.key);
		}
	}
	
	private SearchTree<Pair<K,V>> tree = new SearchTree<Pair<K,V>>();

	
}
