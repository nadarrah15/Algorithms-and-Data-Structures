package bits;

public class Test {

	public static void main(String[] args){
		printBits(256);
	}
	
	static int setTenBitsToTheRight(int i){
		int j = i | (1 << 10); //1 << 10 == 0010 0000 0000 
		return j;
	}
	
	static boolean isNinthBitZero(int i){
		return ((i & (1 << 9)) == 0); //1 << 9 == 0001 0000 0000
	}
	
	static void printBits(int i){ 	//works for negative numbers, but adds a 1 to the beginning of positive number
		for(int j = 31; j >= 0; j--){
			int bitmask = i & (1 << j);
			if(bitmask == 0)
				System.out.print(0);
			else
				System.out.print(1);
		}
	}
}
