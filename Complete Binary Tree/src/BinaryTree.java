


public abstract class BinaryTree<E> {
	private Node<E> root;
	
	//Homework, knowing the size is a useful for all types of trees. set to private so clients cant access it
	private int size;
	
	public BinaryTree() {
		this.root = null;
	}
	
	// apply some policy to find a spot, create a node, and insert this data.
	public abstract void insert(E value);
	
	// search the tree for this data.  Again, we need a policy for how to search,
	// which will depend on how the tree is organized
	public abstract boolean contains(E value);
	
	// find a particular value in the tree.   Returns an iterator that refers to that spot
	// in the tree.  If the value is not found, then the iterator's next() method will return
	// null.
	public abstract TreeIterator<E> find(E value);

	// return an iterator referring to the "first" node in the tree.
	public abstract TreeIterator<E> iterator();
	
	//Homework, accessor to the size variable.
	public abstract int getSize();
	
	//Homework, mutator to the size variable. Set to protected so that the subclasses are the only classe that can mess with the size variable.
	protected abstract void setSize(int i);
	
	//Homework
	public abstract int getHeight();
	
	// get the root of the tree.
	protected Node<E> getRoot() {
		return root;
	}
	
	protected void setRoot(Node<E> root) {
		this.root = root;
	}
	//HW I had help from http://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/
	public boolean isComplete(){
		return isComplete(getRoot(), 0, nodeCount(getRoot()));
	}

	private boolean isComplete(Node<E> cur, int index, int num){
		if(cur == null)
			return true;
		
		if(index >= num)
			return false;
		
		//Recurse for left and right subtree. Their indices are 2 * index + 1 for left, and 2 * index + 2 for right
		return isComplete(cur.left, 2 * index + 1, num) && isComplete(cur.left, 2 * index + 2, num);
	}
	
	private int nodeCount(Node<E> cur){
		if(cur == null)
			return 0;
		
		return 1 + nodeCount(cur.left) + nodeCount(cur.right);
	}
}

