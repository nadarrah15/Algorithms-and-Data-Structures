package heapQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityHeapQueue<E extends Comparable<E>> {
	private ArrayList<E> data;
	Comparator<E> comp;
	int size;
	
	public PriorityHeapQueue() {
		data = new ArrayList<>();

		// use the "standard" comparison
		comp = new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return o1.compareTo(o2);
			}
		};
		
		size = 0;
	}
	
	public PriorityHeapQueue(Comparator<E> c) {
		data = new ArrayList<>();
		comp = c;
		size = 0;
	}
	
	public PriorityHeapQueue(Collection<E> items, Comparator<E> c) {
		data = new ArrayList<>(items);
		comp = c;
		size = data.size();
		
		heapify();
	}
	
	public void add(E item) {
		// put the item at the end of the tree, making room if necessary.
		if (size < data.size())
			data.set(size-1, item);
		else
			data.add(item);
		
		size++;
		
		// move the item to the correct position
		percolateUp(size-1);
	}
	
	public E removeMin() {
		E temp = data.get(0);
		
		// put the last element in the tree in the root
		data.set(0,  data.get(size - 1));
		
		// ...and percolate it down to restore the heap property.
		percolateDown(0);
		
		size--;	
		return temp;
	}
	
	public E remove(int index){
		//Throws an error since throwing a null value would bad since we may want to store null values in the queue
		if(index >= size)
			throw new NoSuchElementException();
		
		//takes the last value of the heap and places it at the index being removed,
		//then the last index is removed and we percolateDown or percolateUp to restore heap property
		E temp = data.get(index);
		data.set(index, data.get(size - 1));
		data.remove(size - 1);
		percolateDown(index);
		percolateUp(index);
		
		size--;
		return temp;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	private static int left(int pos) {
		return 2*pos+1;
	}
	
	private static int right(int pos) {
		return 2*pos+2;
	}
	
	private static int parent(int pos) {
		return (pos-1)/2;
	}
	
	// make the data list into a heap
	private void heapify() {
		for(int i = 1; i <= size; i++){
			percolateDown(size - i);
		}
	}
	
	// move the value at pos up as far as necessary to restore the heap condition
	private void percolateUp(int pos) {
		int parent = parent(pos);
		E value = data.get(pos);
		while (pos > 0 && comp.compare(value,  data.get(parent)) < 0) {
			// move parent value down to this node
			data.set(pos,  data.get(parent)); 
			// update pointers
			pos = parent;
			parent = parent(pos);
		}
		// place the value in the spot where we stop.
		data.set(pos, value);
	}
	
	// push item at pos down to the correct place
	private void percolateDown(int pos) {
		E value = data.get(pos);
		
		// move down the tree until find a spot or reach a leaf
		while (left(pos) < size) {
			int child = left(pos);
			// if we have a right child as well, see which is smaller
			if (child < size - 1 && comp.compare(data.get(child+1), data.get(child)) < 0)
				child++;
			
			// see if we need to push the value down to the child
			if (comp.compare(data.get(child),  value) < 0)
				data.set(pos, data.get(child));
			else {
				// if not, we're done
				break;
			}

			// move to the child and do it again
			pos = child;
		}
		data.set(pos, value);
	}
}

