import java.util.Comparator;

public class TelevisionComparator<E extends Television> implements Comparator<E>{

	@Override
	public int compare(E o1, E o2) {
		// TODO Auto-generated method stub
		int i = o1.getManufacturer().compareTo(o2.getManufacturer());
		if(i == 0)
			i = o1.getModel().compareTo(o2.getModel());
		
		return i;
	}

}
