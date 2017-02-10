
public class Main {

	public static void main(String[] args) {
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(2, new BinaryTree<Integer>(3), new BinaryTree<Integer>(2));
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(1, new BinaryTree<Integer>(3), new BinaryTree<Integer>(2));
		
		System.out.println(tree1.equals(tree2));
		
		BinaryTree<Integer> tree = tree2.copy();
		
		System.out.println("Parent: " + tree.value() + " Left: " + tree.left().value() + " Right:" + tree.right().value());
	}
}
