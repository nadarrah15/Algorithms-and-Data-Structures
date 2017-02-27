import java.util.Random;

public class SearchTree<E> extends BinaryTree<E> {

	@Override
	public void insert(E value) {
		// randomly walk down the tree until we find a leaf position, and put the value there.
		Random r = new Random();
		
		Node<E> root = getRoot();
		
		if (root == null) {
			setRoot(new Node<E>(value));
			return;
		}
		
		while (true) {
			boolean goLeft = r.nextBoolean();
		
			if (goLeft) {
				if (root.left == null) {
					root.left = new Node<E>(value);
					root.left.parent = root;
					break;
				}
				else 
					root = root.left;
			}
			else {
				if (root.right == null) {
					root.right = new Node<E>(value);
					root.right.parent = root;
					break;
				}
				else 
					root = root.right;				
			}
		}
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
		// TODO implement me!!
		InorderTreeIterator<E> it = new InorderTreeIterator<E>(getRoot());
		E target = it.next();
		while(it.hasNext() && !target.equals(value))
			target = it.next();
		
		return it;
	}

	@Override
	public TreeIterator iterator() {
		// create an iterator pointing at the first element in the tree.
		return new PreorderTreeIterator<E>(getRoot());
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setSize(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
