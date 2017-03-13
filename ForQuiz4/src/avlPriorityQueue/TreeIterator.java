package avlPriorityQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class TreeIterator<E> implements Iterator<E> {
	// track our current position
	private Node<E> current;
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	TreeIterator(Node<E> node) {
		current = node;
	}
	
	// have we walked off the end of the tree?  
	@Override
	public boolean hasNext() {
		return (current != null);
	}
	
	// Return the next value in the tree.
	@Override
	public E next() {
		// return the value at this location, and advance current to the next location. 
		
		// if we've walked off the end, throw an exception
		if (current == null)
			throw new NoSuchElementException();
		
		// grab the value from current
		E temp = current.data;
		
		// advance current to the next node in the traversal
		advance();
		
		// return temp
		return temp;
	}

	// This is the only step subclasses need to override -- advance to the next 
	// node in the tree, according to whatever traversal we are implementing.  Set 
	// current to null to indicate we've walked off the tree.
	protected abstract void advance();
	
	// accessor methods for our subclasses to modify current.
	protected Node<E> getCurrent() {
		return current;
	}
	
	protected void setCurrent(Node<E> newCur) {
		current = newCur;
	}
}
