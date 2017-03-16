package treeMap;

import structure5.Association;
import structure5.Map;
import structure5.Set;
import structure5.SetList;
import structure5.Structure;
import structure5.Vector;
import trees.SearchTree;
import trees.TreeIterator;

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
		tree.clear();
	}

	@Override
	public boolean containsKey(K key) {
		Pair<K, V> p = locate(key);
		if(p == null)
			return false;
		else 
			return true;
	}

	@Override
	public boolean containsValue(V target) {
		TreeIterator<Pair<K,V>> it = tree.iterator();
		
		while(it.hasNext()){
			if(it.next().val.equals(target))
				return true;
		}
		
		return false;
	}

	@Override
	public Set<Association<K, V>> entrySet() {
		TreeIterator<Pair<K, V>> it = tree.iterator();
		Set<Association<K, V>> set = new SetList<Association<K, V>>();
		
		while(it.hasNext()){
			Pair<K,V> p = it.next();
			set.add(new Association<K, V>(p.key, p.val));
		}
		
		return set;
	}

	@Override
	public V get(K target) {
		Pair<K,V> p = locate(target);
		
		return p.val;
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		TreeIterator<Pair<K,V>> it = tree.iterator();
		Set<K> set = new SetList<K>();
		
		while(it.hasNext())
			set.add(it.next().key);
		
		return set;
	}

	@Override
	public V put(K key, V val) {
		//if the key already exists, we just want to change its value
		Pair<K,V> p = locate(key);
		
		if(p == null)
			tree.insert(new Pair<K,V>(key, val));
		else
			p.val = val;
		
		return val;
	}
	
	protected Pair<K, V> locate(K key){
		return locate(tree, key);
	}
	
	private Pair<K, V> locate(SearchTree<Pair<K,V>> tree, K key){
		if(tree == null)
			return null;
		else if(key.compareTo(tree.getRootValue().key) == 0)
			return tree.getRootValue();
		else if(key.compareTo(tree.getRootValue().key) > 0)
			return locate((SearchTree<TreeMap<K, V>.Pair<K, V>>) tree.right(), key);
		else
			return locate((SearchTree<TreeMap<K, V>.Pair<K, V>>) tree.left(), key);
	}

	@Override
	public void putAll(Map<K, V> map) {
		// TODO Auto-generated method stub
		Set<K> set = map.keySet();
		for(K key: set){
			put(key, map.get(key));
		}
	}

	@Override
	public V remove(K key) {
		Pair<K, V> p = locate(key);
		
		if(p == null)
			return null;
		
		tree.remove(p);
		return p.val;
	}

	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public Structure<V> values() {
		Vector<V> vec = new Vector<V>();
		TreeIterator<Pair<K,V>> it = tree.iterator();
		
		while(it.hasNext()){
			vec.add(it.next().val);
		}
		
		return vec;
	}
}
