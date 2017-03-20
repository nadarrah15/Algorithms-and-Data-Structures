package bits;

public class Test {

	public static void main(String[] args){
		printBits(256);
	}
	
	static int setTenBitsToTheRight(int i){
		i |= (1 << 10); //1 << 10 == 0010 0000 0000 
		return i;
	}
	
	static boolean isNinthBitZero(int i){
		return ((i & (1 << 9)) == 0); //1 << 9 == 0001 0000 0000
	}
	
	static void printBits(int i){
		for(int j = 31; j >= 0; j--){
			int bitmask = i & (1 << j); //check for each index to see if it is 1 or 0
			if(bitmask == 0)
				System.out.print(0);
			else
				System.out.print(1);
		}
	}
}
