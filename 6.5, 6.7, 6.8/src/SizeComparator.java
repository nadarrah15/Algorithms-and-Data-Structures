import java.util.Comparator;

public class SizeComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return ((Television)o1).getSize() - ((Television)o2).getSize();
	}

}
