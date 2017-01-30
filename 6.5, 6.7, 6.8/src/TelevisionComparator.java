import java.util.Comparator;

public class TelevisionComparator<T> implements Comparator<T>{

	//0 = size, 1 = price, 2 = alphabetically
	//defaults to price
	private int type = 0;
	
	@Override
	public int compare(T o1, T o2) {
		if(type == 0){
			return ((Television)o1).getSize() - ((Television)o2).getSize();
		}
		else if(type == 1){
			return (int) (((Television)o1).getPrice() - ((Television)o2).getPrice());
		}
		else if(type == 2){
			return ((Television)o1).getManufacturer().compareToIgnoreCase(((Television)o2).getManufacturer());
		}
		return 0;
	}

	public void setComparator(String s) throws TypeNotFoundException{
		if(s.equalsIgnoreCase("size")){
			type = 0;
		}
		else if(s.equalsIgnoreCase("price")){
			type = 1;
		}
		else if(s.equalsIgnoreCase("alphabetically")){
			type = 2;
		}
		else
			throw new TypeNotFoundException();
	}

}
