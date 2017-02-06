import java.util.Iterator;

public class Main {

	public static void main(String[] args){
		
		OrderedVector<Television> arr = new OrderedVector<Television>();
		
		arr.add(new Television("Insignia", "Px45", 45, 39.45, 3, 1080, 720, 1000));
		arr.add(new Television("Sharp", "Px45", 50, 39.45, 1, 1080, 720, 1000));
		arr.add(new Television("Sony", "Px45", 40, 39.45, 2, 1080, 720, 1000));
		
		Iterator<Television> it = arr.iterator();
		TelevisionComparator<Television> c = new TelevisionComparator<Television>();
		
		while(it.hasNext()){
			//fix System.out.println(c.compare(o1, o2));
		}
	}
}
