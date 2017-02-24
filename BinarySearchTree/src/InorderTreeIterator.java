
public class InorderTreeIterator<E> extends TreeIterator<E> {
	
	// this constructor has package-level access; outsiders should never create an 
	// object of this type
	InorderTreeIterator(Node<E> node) {
		super(node);
	}

	@Override
	protected void advance() {
	}

}
