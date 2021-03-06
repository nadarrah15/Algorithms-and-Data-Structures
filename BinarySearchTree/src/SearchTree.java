
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
				cur.left.parent = cur;
				if (cur.height == 0)
					cur.height = 1;
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
				cur.right.parent = cur;
				if (cur.height == 0)
					cur.height = 1;
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
		else if (heightRight > heightLeft + 1) { // right side too heavy
			// figure out which rotation we need
			int heightRR = (cur.right.right == null) ? -1 : cur.right.right.height; 
			int heightRL = (cur.right.left == null) ? -1 : cur.right.left.height;
			
			if (heightRR >= heightRL)
				rotateWithRightChild(cur);
			else
				rotateWithLeftChildOfRight(cur);

		}
		else {
			// no rotations, so just update the height
			cur.height = Math.max(heightLeft, heightRight) + 1;
		}

		// Note that the rotate methods should have re-computed the heights of all nodes
		// whose heights may have changed.
		
		// return to the recursive call that processed the parent of this node. Note that,
		// even though the node that is at the top of this subtree may have changed, our
		// recursive path will take us up to the parent of the subtree as desired.  Since
		// every node in both subtrees of our parent is now in AVL balance, we have an 
		// inductive proof that our insertion method maintains AVL balance.
		
	}
	
	//Homework, runs in O(n) because it visits each node once
	public int slowSize(){
		return slowSize(getRoot());
	}
	
	private int slowSize(Node<E> node){
		//base case
		if(node == null)
			return 0;
		else
			return slowSize(node.left) + slowSize(node.right) + 1;
	}
	
	private void adjustHeight(Node<E> node){
		int heightLeft = (node.left == null) ? -1 : node.left.height;
		int heightRight = (node.right == null) ? -1 : node.right.height;
		
		node.height = Math.max(heightLeft, heightRight) + 1;
	}
	
	// Four node rotation methods. See the reading on canvas.
	private void rotateWithLeftChild(Node<E> cur) {
		Node<E> parent = cur.parent;
		Node<E> L = cur.left;
		Node<E> LR = cur.left.right;
		if (parent != null) {
			if (parent.left == cur) {
				parent.left = cur.left;
			}
			else {
				parent.right = cur.left;
			}
		}
		L.parent = parent;
		
		
		// attach LR to N as left child
		cur.left = LR;
		if (LR != null)
			LR.parent = cur;
		
		// attach N to L as right child
		L.right = cur;
		cur.parent = L;
		
		// adjust heights?
		//N height needs to be adjusted first since L's height relies on N
		adjustHeight(cur);
		adjustHeight(L);
		
		if(cur == getRoot())
			setRoot(L);
	}

	private void rotateWithRightChildOfLeft(Node<E> cur) {
		Node<E> parent = cur.parent;
		Node<E> LR = cur.left.right;
		Node<E> L = cur.left;
		if(parent != null){
			if(parent.left == cur)
				parent.left = LR;
			else
				parent.right = LR;
		}
		LR.parent = parent;
		
		if(LR.left != null){
			L.right = LR.left;
			LR.left.parent = L;
		}
		
		if(LR.right != null){
			cur.left = LR.right;
			LR.right.parent = cur;
		}
		
		LR.left = L;
		L.parent = LR;
		
		LR.right = cur;
		cur.parent = LR;
		
		//adjust L and cur height first because LR height depends on their heights
		adjustHeight(L);
		adjustHeight(cur);
		adjustHeight(LR);
	}

	private void rotateWithRightChild(Node<E> cur) {
		Node<E> parent = cur.parent;
		Node<E> R = cur.right;
		if(parent != null){
			if(parent.left == cur)
				parent.left = R;
			else
				parent.right = R;
		}
		R.parent = parent;
		
		// attach RL to N as right child
		cur.right = R.left;
		if (cur.right != null)
			cur.right.parent = cur;
		
		// attach N to L as right child
		R.left = cur;
		cur.parent = R;
		
		// adjust heights?
		//N height needs to be adjusted first since L's height relies on N
		adjustHeight(cur);
		adjustHeight(R);
	}

	private void rotateWithLeftChildOfRight(Node<E> cur) {
		Node<E> parent = cur.parent;
		Node<E> RL = cur.right.left;
		Node<E> R = cur.right;
		if(parent != null){
			if(parent.right == cur)
				parent.right = RL;
			else
				parent.right = RL;
		}
		RL.parent = parent;
		
		if(RL.left != null){
			R.right = RL.left;
			RL.left.parent = R;
		}
		
		if(RL.left != null){
			cur.right = RL.left;
			RL.left.parent = cur;
		}
		
		RL.right = R;
		R.parent = RL;
		
		RL.left = cur;
		cur.parent = RL;
		
		//adjust R and cur height first because RL height depends on their heights
		adjustHeight(R);
		adjustHeight(cur);
		adjustHeight(RL);
	}

	
	@Override
	public boolean contains(E value) {
		return contains(getRoot(), value);
	
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
		
		if(contains(node.data)){
			TreeIterator<E> ti = find(node.data);
			
			Node<E> cur = ti.getCurrent();
			
			if(cur.left != null && cur.right != null){
				Node<E> pred = cur.left;
				while(pred.right != null)
					pred = pred.right;
				
				if(cur == getRoot())
					setRoot(pred);
				
				//if there was no right subtree of cur.left
				if(pred == cur.left){
					if(cur.parent != null){
						if(cur.parent.left == cur)
							cur.parent.left = pred;
						else
							cur.parent.right = pred;
					}
					
					pred.parent = cur.parent;
					cur = null;
					return;
				}
				
				//else we have traversed down a right subtree
				pred.parent.right = null;
				if(cur.parent != null){
					if(cur.parent.left == cur)
						cur.parent.left = pred;
					else
						cur.parent.right = pred;
					pred.parent = cur.parent;
				}
//				pointer.left = cur.left;
//				cur.left.parent = pointer;
				pred.right = cur.right;
			}
			
			else if(cur.left != null){
				
			}
			else if(cur.right != null){
				
			}
			else{
				if(cur == cur.parent.left)
					cur.parent.left = null;
				else
					cur.parent.right = null;
				
				cur = new Node<E>(null, null, null, null);
			}
		}
		
		
	}
}
