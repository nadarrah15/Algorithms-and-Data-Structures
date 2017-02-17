import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException{
		Reader in = new BufferedReader(new FileReader(new File("file.txt")));
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		int intIn = in.read();
		while(intIn != -1){
			char input = (char) intIn;
			if(!map.containsKey(input)){
				map.put(input, 1);
			}
			else{
				int val = map.get(input) + 1;
				map.replace(input, val);
			}
			
			intIn = in.read();
		}
		
		System.out.printf("%-10s%-10s%n","Letter", "Frequency");
		for(Map.Entry<Character, Integer> entry: map.entrySet()){
			System.out.printf("%-10s%-10s%n",entry.getKey(),entry.getValue());
		}
	}

}
