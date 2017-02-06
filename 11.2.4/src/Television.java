
public class Television implements Comparable<Television>{
	private String manufacturer;
	private String model;
	private int size; 	// diagonal inches
	private double weight;
	private double price;
	private int x_res; 	// horizontal resolution
	private int y_res; 	// vertical resolution
	private int brightness;	// measured in "nits" -- bigger is better?

	public Television(String man, String mod, int s, double w, double p, int x, int y, int b){
		manufacturer = man;
		model = mod;
		size = s;
		weight = w;
		price = p;
		x_res = x;
		y_res = y;
		brightness = b;
	}
	
	public String getManufacturer(){
		return manufacturer;
	}
	
	public String getModel(){
		return model;
	}
	
	public int getSize(){
		return size;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getXRes(){
		return x_res;
	}
	
	public int getYRes(){
		return y_res;
	}
	
	public int getBrightness(){
		return brightness;
	}

	@Override
	public int compareTo(Television o) {
		// TODO Auto-generated method stub
		int i = getManufacturer().compareTo(o.getManufacturer());
		if(i == 0)
			i = getModel().compareTo(o.getModel());
		
		return i;
	}
	
	// c'tors, methods, etc
}
