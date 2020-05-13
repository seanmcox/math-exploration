package com.shtick.math.collatz;
/**
 * 
 */

/**
 * @author scox
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Search for solutions to m*2^a - 2^b = 3*(3*m + 1)
		for(int m=1;m<1000000;m+=2) {
			long mm = (3*m+1)*3;
			int b = 1;
//			System.out.println("m = "+m);
//			System.out.println("mm = "+mm);
			for(long b2=2;(mm+b2)/m>=b2;b2*=2) {
//				System.out.println("b = "+b);
//				System.out.println("b2 = "+b2);
//				System.out.println("? = "+(mm+b2)/m);
				if((mm+b2)%m == 0) {
					// Possibility
					long a2 = (mm+b2)/m;
					if(isPowerOfTwo(a2)) {
						System.out.println("Success at m = "+m+" (9m+3="+mm+"), b = "+b+", and 2^a = "+a2);
						break;
					}
				}
				b++;
			}
//			System.out.println("b = "+b);
//			if((m+1)%1000 == 0)
//				System.out.println("m = "+m);
		}
	}
	
	private static boolean isPowerOfTwo(long n) {
		if(n<=1)
			return false;
		do {
			n/=2;
		} while(n%2 == 0);
		return n == 1;
	}
}
