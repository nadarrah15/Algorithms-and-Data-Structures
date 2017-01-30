import java.util.Comparator;

public class PriceComparator<T> implements Comparator<T>{

	@Override
	public int compare(T o1, T o2) {
		return (int) (((Television)o1).getPrice() - ((Television)o2).getPrice());
	}

}
