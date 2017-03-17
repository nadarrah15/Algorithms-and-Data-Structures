package avlPriorityQueue;
import java.util.NoSuchElementException;
import pQueue.PriorityQueue;
import trees.SearchTree;
import trees.TreeIterator;

public class PriorityTreeQueue<E extends Comparable<E>> implements PriorityQueue<E>{

	private SearchTree<E> tree;
	private int size;
	private TreeIterator<E> it;
	
	public PriorityTreeQueue(){
		tree = new SearchTree<E>();
		size = 0;
	}
	
	@Override
	public E getFirst() {
		if(size == 0)
			throw new NoSuchElementException();
		
		else 
			return it.next();
	}

	@Override
	public E remove() {
		E value = it.next();
		tree.remove(value);
		return value;
	}

	@Override
	public boolean isEmpty() {
		if(it.hasNext())
			return true;
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		tree = new SearchTree<E>();
		size = 0;
		it = tree.iterator();
	}

	@Override
	public void add(E value) {
		tree.insert(value);
		it = tree.iterator();
		size++;
	}
}
