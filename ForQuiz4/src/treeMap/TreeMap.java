package treeMap;

import structure5.Association;
import structure5.Map;
import structure5.Set;
import structure5.Structure;
import trees.SearchTree;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V>{

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

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(K arg0) {
		// we dont care what the value is since we only use the key in comparision
		return tree.contains(new Pair<K,V>(arg0, null));
	}

	@Override
	public boolean containsValue(V arg0) {
		return false;
	}

	@Override
	public Set<Association<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) {
		tree.insert(new Pair<K, V>(arg0, arg1));
		return arg1;
	}

	@Override
	public void putAll(Map<K, V> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(K arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Structure<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
