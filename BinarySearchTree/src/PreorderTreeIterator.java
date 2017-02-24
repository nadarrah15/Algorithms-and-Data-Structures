

import java.util.NoSuchElementException;

class PreorderTreeIterator<E> extends TreeIterator<E> {
	// track our current position
	private Node<E> current;
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	PreorderTreeIterator(Node<E> node) {
		current = node;
	}
	
	// have we walked off the end of the tree?  
	@Override
	public boolean hasNext() {
		return (current == null);
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
		
		// rule 1: do we have a left child?
		if (current.left != null) {
			current = current.left;
		}
		else if (current.right != null) { // right child?
			current = current.right;
		}
		else {
			// childless
			Node<E> p = current.parent;
			while (p != null && (current == p.right || p.right == null)) {
				current = p;
				p = current.parent;
			}
			if (p == null) {
				current = null;
			}
			else {
				current = p.right;
			}
		}
		
		// return temp
		return temp;
	}

}
