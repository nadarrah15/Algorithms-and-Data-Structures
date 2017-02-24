

import java.util.NoSuchElementException;

public class InorderTreeIterator<E> extends TreeIterator<E> {
	// track our current position
	private Node<E> current;
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	InorderTreeIterator(Node<E> node) {
		current = node;
		
		//travel completely to the left to start
		while(current.left != null)
			current = current.left;
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
		
		//there is no left child or all the left children have bben visited so we see if there is a right child and then move all the way to the left of the right child
		if(current.right != null){
			current = current.right;
			
			while(current.left != null)
				current = current.left;
		}
		//if this node has no right child, return the parent
		else
			current = current.parent;
		
		// return temp
		return temp;
	}

}
