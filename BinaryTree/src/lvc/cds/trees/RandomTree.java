package lvc.cds.trees;

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
	
	//Homework
	@Override
	public boolean contains(E value) {
		return contains(value, getRoot());
	}

	// is value in the subtree rooted at cur?
	private boolean contains(E value, Node<E> cur) {
		if (cur == null)
			return false;
		
		// check if the value is in the current node
		else if(value == cur.data)
			return true;
		// check (recursively) if it's in the left subtree
		// check (recursively) if it's in the right subtree
		else
			return contains(value, cur.left) || contains(value, cur.right);
	}

	@Override
	public TreeIterator find(E value) {
		// TODO Auto-generated method stub
		
		return null;
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
