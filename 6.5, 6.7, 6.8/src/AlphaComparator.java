import java.util.Comparator;

public class AlphaComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return ((Television)o1).getManufacturer().compareTo(((Television)o2).getManufacturer());
	}

}
