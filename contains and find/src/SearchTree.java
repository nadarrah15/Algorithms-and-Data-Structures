
public class SearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	@Override
	public void insert(E value) {
		Node<E> cur = getRoot();
		
		if (cur == null) {
			setRoot(new Node<E>(value));
			return;
		}
		
		// going into this call, our precondition is that the tree is a search
		// tree that is AVL balanced.  We do the work required in insert() to 
		// maintain those properties.
		insert(cur, value);
		
		
	}

	private void insert(Node<E> cur, E value) {
		// NB: We assume here that cur is not null!!
		
		int comp = value.compareTo(cur.data);
		
		if (comp <= 0) { // go left
			if (cur.left == null) {
				cur.left = new Node<E>(value);
				return;
			}
			else {
				insert(cur.left, value);
				// adjust the height down below
			}
		}
		else { // go right 
			if (cur.right == null) { 
				cur.right = new Node<E>(value);
				return;
			}
			else {
				insert(cur.right, value);
			}				
		}
		
		// When we get here, all of the nodes below us are in balance (Think induction).  We examine 
		// the balance of this node and restore it if necessary (without mucking up the subtrees), so
		// that when we return, the assumption will be true at the next level up.
		int heightLeft = (cur.left == null) ? -1 : cur.left.height;
		int heightRight = (cur.right == null) ? -1 : cur.right.height;

		if (heightLeft > heightRight + 1) { // left side too heavy 
			// figure out which rotation we need
			int heightLL = (cur.left.left == null) ? -1 : cur.left.left.height; 
			int heightLR = (cur.left.right == null) ? -1 : cur.left.right.height;
			
			if (heightLL >= heightLR)
				rotateWithLeftChild(cur);
			else
				rotateWithRightChildOfLeft(cur);
		}
		else {
			// figure out which rotation we need
			int heightRR = (cur.right.right == null) ? -1 : cur.right.right.height; 
			int heightRL = (cur.right.left == null) ? -1 : cur.right.left.height;
			
			if (heightRR >= heightRL)
				rotateWithRightChild(cur);
			else
				rotateWithLeftChildOfRight(cur);

		}
		
		// adjust height -- things might have changed.
		heightLeft = (cur.left == null) ? -1 : cur.left.height;
		heightRight = (cur.right == null) ? -1 : cur.right.height;
		
		cur.height = Math.max(heightLeft, heightRight) + 1;

		// return to the recursive call that processed the parent of this node. 
		// every node in both subtrees of our parent is now in AVL balance.
		
	}
	
	// Four node rotation methods. See the reading on canvas.
	private void rotateWithLeftChild(Node<E> cur) {
		Node<E> parent = cur.parent;
		if (parent != null) {
			if (parent.left == cur) {
				parent.left = cur.left;
			}
			else {
				parent.right = cur.left;
			}
		}
		cur.left.parent = parent;
		
		Node<E> L = cur.left;
		
		// attach LR to N as left child
		cur.left = L.right;
		if (cur.left != null)
			cur.left.parent = cur;
		
		// attach N to L as right child
		L.right = cur;
		cur.parent = L;
		
		// adjust heights?
		
	}

	private void rotateWithRightChildOfLeft(Node<E> cur) {
		
	}

	private void rotateWithRightChild(Node<E> cur) {
		
	}

	private void rotateWithLeftChildOfRight(Node<E> cur) {
		
	}

	
	@Override
	public boolean contains(E value) {
		Node<E> root = getRoot();
		
		return contains(root, value);
	
	}
	
	private boolean contains(Node<E> cur, E value) {
		if (cur == null)
			return false;
		else if(cur.data == value)
			return true;
		
		return contains(cur.left, value) || contains(cur.right, value);
	}

	@Override
	public TreeIterator<E> find(E value) {
		// TODO implement me!!
		InorderTreeIterator<E> it = new InorderTreeIterator<E>(getRoot());
		E target = it.next();
		while(it.hasNext() && !target.equals(value))
			target = it.next();
		
		return it;
	}

	@Override
	public TreeIterator<E> iterator() {
		Node<E> start = getRoot();

		if (start == null)
			return new InorderTreeIterator<>(null);

		while (start.left != null)
			start = start.left;
		
		return new InorderTreeIterator<>(start);
	}

	@Override
	public E remove(E value) {
		// step 1: find the value.  If not found, return null.
		Node<E> removeMe = getRoot();
		// adjust removeMe so that it refers to the node containing value,
		// or is null if value is not present.
		
		// step 2: start a recursive call that removes the node discovered.
		if (removeMe == null)
			return null;
		else {
			E temp = removeMe.data;
			// call a private helper method that removes a node.
			remove(removeMe);
			return temp;
		}
	}
	
	@Override
	public void remove(TreeIterator<E> iter) {
		remove(iter.getCurrent());
		
		// invalidate the iterator
		iter.setCurrent(null);		
	}

	private void remove(Node<E> node) {
		// remove this node from the tree.  Recursive, algorithm described in class.
		// we would need to restore balance along the impacted path -- how would that
		// work?
		
	}
}
