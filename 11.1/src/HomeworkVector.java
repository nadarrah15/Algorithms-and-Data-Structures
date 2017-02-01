import java.util.Iterator;

import structure5.AbstractStructure;
import structure5.OrderedStructure;
import structure5.Vector;

public class HomeworkVector<E extends Comparable<E>> extends AbstractStructure<E> implements OrderedStructure<E> {

	protected Vector<E> data;

	public HomeworkVector()
	// post: constructs an empty, ordered vector
	{
		data = new Vector<E>();
	}

	public boolean contains(E value)
	// pre: value is non-null
	// post: returns true if the value is in the vector
	{
		int i = data.indexOf(value);
		if(i != -1){
			data.addFirst(data.remove(i));
			return true;
		}
		
		return false;
	}
	
	@Override
	public void add(E arg0) {
		data.addLast(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return data.size();
	}

	public E get(int i) {
		return data.get(i);
	}
}