package trees;

import java.util.Random;

public class RandomTree<E> extends BinaryTree<E> {

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

	@Override
	public boolean contains(E value) {
		return contains(value, getRoot());
	}

	// is value in the subtree rooted at cur?
	private boolean contains(E value, Node<E> cur) {
		if (cur == null)
			return false;
		
		// check if the value is in the current node
		// check (recursively) if it's in the left subtree
		// check (recursively) if it's in the right subtree
		return false;
	}

	@Override
	public TreeIterator<E> find(E value) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public TreeIterator<E> iterator() {
		// create an iterator pointing at the first element in the tree.
		return new PreorderTreeIterator<E>(getRoot());
	}

	@Override
	public E remove(E value) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void remove(TreeIterator<E> iter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BinaryTree<E> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinaryTree<E> right() {
		// TODO Auto-generated method stub
		return null;
	}
}
