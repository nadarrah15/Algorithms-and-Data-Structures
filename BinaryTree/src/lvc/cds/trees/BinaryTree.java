package lvc.cds.trees;

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
	public  int size(){
		return size;
	}
	
	//Homework, mutator to the size variable. Set to protected so that the subclasses are the only classes that can mess with the size variable.
	protected void setSize(int i){
		size = i;
	}
	
	//Homework
	public abstract int getHeight();
	
	// get the root of the tree.
	protected Node<E> getRoot() {
		return root;
	}
	
	protected void setRoot(Node<E> root) {
		this.root = root;
	}
}

