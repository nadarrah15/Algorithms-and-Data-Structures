

class Node<E> {
	// no access modifier -- access is "package" -- only methods of classes
	// in this package have access
	Node<E> parent;
	Node<E> left;
	Node<E> right;
	E data;
	
	Node(E data) {
		this(data, null, null, null);
	}
	
	Node(E data, Node<E> parent) {
		this(data, parent, null, null);
	}
	
	Node(E data, Node<E> parent, Node<E> left, Node<E> right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public int height(){
		int l, r;
		
		if(left == null)
			l = 0;
		else
			l = left.height();
		
		if(right == null)
			r = 0;
		else
			r = right.height();
		
		return 1 + Math.max(l, r);
	}
	
	public int depth(){
		if(parent == null)
			return 0;
		else
			return 1 + parent.depth();
	}
	// nothing else for now.  We will keep this structure private
}