
public class Test {

	public static void main(String[] args) {
		SearchTree<Integer> tree= new SearchTree<Integer>();
		tree.insert(6);
		tree.insert(7);
		tree.insert(3);
		tree.insert(4);
		tree.insert(2);
		//this tree should be avl balanced but the left side will be bigger
		tree.makePic("tree.png");
		
		tree.insert(5);
		//this will cause a rotation
		tree.makePic("tree2.png");
		
	}

}
