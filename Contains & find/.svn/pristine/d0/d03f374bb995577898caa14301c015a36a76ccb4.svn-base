package lvc.cds.trees;

class PreorderTreeIterator<E> extends TreeIterator<E> {
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	PreorderTreeIterator(Node<E> node) {
		super(node);
	}

	@Override
	protected void advance() {
		Node<E> current = getCurrent();
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
		setCurrent(current);
	}

}
