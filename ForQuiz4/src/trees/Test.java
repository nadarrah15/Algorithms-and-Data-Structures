package trees;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		SearchTree<Integer> tree = new SearchTree<Integer>();
		
		tree.insert(5);
		tree.insert(6);
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		
		System.out.println(tree.getRoot().data);
		
//		Iterator<Integer> it = tree.iterator();
//		
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
	}

}
