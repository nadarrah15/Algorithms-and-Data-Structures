import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.OrderedStructure;
import structure5.Vector;

public class OrderedVector<E extends Comparable<E>> extends AbstractStructure<E> implements OrderedStructure<E> {
	
	protected Vector<E> data;
	
	public OrderedVector()
	// post: constructs an empty, ordered vector
	{
		data = new Vector<E>();
	}
	
	protected int locate(E target) {
		Comparable<E> midValue;
		int low = 0; // lowest possible location
		int high = data.size(); // highest possible location
		int mid = (low + high) / 2; // low <= mid <= high
		// mid == high iff low == high
		while (low < high) {
			// get median value
			midValue = data.get(mid);
			// determine on which side median resides:
			if (midValue.compareTo(target) < 0) {
				low = mid + 1;
			} else {
				high = mid;
			}
			// low <= high
			// recompute median index
			mid = (low + high) / 2;
		}
		return low;
	}

	@Override
	public void add(E value)
	// pre: value is non-null
	// post: inserts value, leaves vector in order
	{
		int position = locate(value);
		data.add(position, value);
	}

	public boolean contains(E value)
	// pre: value is non-null
	// post: returns true if the value is in the vector
	{
		int position = locate(value);
		return (position < size()) && data.get(position).equals(value);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return data.iterator();
	}

	@Override
	public E remove(E value)
	// pre: value is non-null
	// post: removes one instance of value, if found in vector
	{
		if (contains(value)) {
			// we know value is pointed to by location
			int position = locate(value);
			// since vector contains value, position < size()
			// keep track of the value for return
			E target = data.get(position);
			// remove the value from the underlying vector
			data.remove(position);
			return target;
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return data.size();
	}
	
	public boolean isEmpty()
	// post: returns true if the OrderedVector is empty
	{
		return data.size() == 0;
	}

	public void clear()
	// post: vector is emptied
	{
		data.setSize(0);
	}
}