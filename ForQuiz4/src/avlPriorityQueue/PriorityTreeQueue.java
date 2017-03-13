package avlPriorityQueue;

public class PriorityTreeQueue<E extends Comparable<E>> implements PriorityQueue<E>{

	private SearchTree<E> tree = new SearchTree<E>();
	private int size = 0;
	
	@Override
	public E getFirst() {
		return tree.getRoot().data;
	}

	@Override
	public E remove() {
		E temp = tree.remove(tree.getRoot().data);
		if(temp != null)
			size--;
		
		return temp;
	}

	@Override
	public boolean isEmpty() {
		if(tree.getRoot() != null)
			return true;
		
		return false;
	}

	@Override
	public int size() {
		return size();
	}

	@Override
	public void clear() {
		size = 0;
		tree = new SearchTree<E>();
	}

	@Override
	public void add(E value) {
		tree.insert(value);
		size++;
	}
}
