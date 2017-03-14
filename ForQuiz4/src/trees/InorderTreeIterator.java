package trees;

public class InorderTreeIterator<E> extends TreeIterator<E> {
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	InorderTreeIterator(Node<E> node) {
		super(node);
		
		Node<E> cur = getCurrent();
		
		while(cur.left != null)
			cur = cur.left;
		
		setCurrent(cur);
	}

	@Override
	protected void advance() {
		Node<E> cur = getCurrent();
		
		if(cur.right != null){
			cur = cur.right;
			while(cur.left != null)
				cur = cur.left;
		}
		else if(cur.parent != null && cur == cur.parent.right){
			while(cur == cur.parent.right)
				cur = cur.parent;
		}
		else{
			cur = cur.parent;
		}
		
		setCurrent(cur);
	}

}
