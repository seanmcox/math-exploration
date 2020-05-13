package com.shtick.math.lychrel;

import java.util.Arrays;
import java.util.Map;

import com.shtick.math.DivisionResult;

/**
 * 
 */


/**
 * Some known related papers:
 * Hyman Gabai and Daniel Coogan, "On Palindromes and Palindromic Primes", Mathematics Magazine, Vol. 42, No. 5 (Nov., 1969), pp. 252-254 
 * https://www.jstor.org/stable/2688705?seq=1#page_scan_tab_contents
 * 
 * Yutaka Nishiyama, "NUMERICAL PALINDROMES AND THE 196 PROBLEM", International Journal of Pure and Applied Mathematics, Volume 80  No. 3  2012, 375-384
 * http://ijpam.eu/contents/2012-80-3/9/9.pdf
 * 
 * Marius Coman, "Conjecture that there is no a Poulet number to be as well Lychrel number"
 * https://www.researchgate.net/publication/321974237_Conjecture_that_there_is_no_a_Poulet_number_to_be_as_well_Lychrel_number
 * 
 * @author scox
 *
 */
public class Main {
	/**
	 * Includes palindrome seeds.
	 * 
	 * See: https://oeis.org/A088753
	 */
	static final int[] LYCHREL_SEEDS = new int[] {
			196,879,1997,7059,9999 /*9*11*/,10553,10563,10577,10583,10585,10638 /*9*/,10663, 10668, 10697,10715, 10728 /*9*/, 10735, 10746 /*9*/, 10748, 10783, 10785, 10787, 10788, 10877, 10883, 10963, 10965,
			10969, 10977, 10983, 10985, 12797, 12898, 13097, 13197, 13694, 14096, 14698, 15297, 15597 /*9*/,18598, 18798
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Theoretical digital root patterns rely on base-10-style digital root progression being valid in other bases.
		 * 2 - 3 (Theoretical digital root patterns: (1,1,1,1,1,1,1,...})
		 * 3 - 2 (Theoretical digital root patterns: (1,2,2,2,2,2,2,...})
		 * 4 - 1000+ (Theoretical digital root patterns: (1,2,1,2,1,2,...}, {3,3,3,3,3,3,3,3,...})
		 * 5 - 13 (Theoretical digital root patterns: (1,2,4,4,4,4,...}, {3,2,4,4,4,4,...})
		 * 6 - 11 (Theoretical digital root patterns: (1,2,4,3,1,2,4,3,1,...}, {5,5,5,5,5,5,5})
		 * 7 - 39 (Theoretical digital root patterns: (1,2,4,2,4,2,4,2,4,...}, {3,6,6,6,6,6,6,6,6}, {5,4,2,4,2,4,2,4,2})
		 * 8 - 1000+
		 * 9 - 1000+
		 * 10 - 1000+
		 * 11 - 1000+
		 * 12 - 1000+
		 * 13 - 1000+
		 * 14 - 1000+
		 * 15 - 27
		 * 16 - 1000+
		 * 17 - 16
		 * 18 - 1000+
		 * 19 - 17
		 * 20 - 13
		 * 21 - 1000+
		 * 22 - 1000+
		 * 23 - 21
		 * 24 - 25
		 * 25 - 32
		 */
		// 1, b-1, b-3
		// 10, 12, 15, 21, 22
		
		// Search for lychrel stops that are reached with a carry.
//		for(int b=4;b<100;b++) {
//			int i;
//			LychrelNumber n = new LychrelNumber(b, new int[] {1, b-1, b-3});
//			for(i=0;i<1000;i++) {
//				n=n.nextLychrelNumber();
//				if(n.isLychrelStop()) 
//					break;
//			}
//			if(!n.isLychrelStop())
//				System.out.println(""+b+" - ");
//		}
//		for(int b=4;b<1000000000;b*=2) {
//			b++;
//			LychrelNumber n = new LychrelNumber(b, new int[] {b-1,b-1,b-1,b-1});
//			int i;
//			for(i=0;i<1000;i++) {
//				n=n.nextLychrelNumber();
//				if(n.isLychrelStop())
//					break;
//			}
//			System.out.println(""+b+" - "+i);
//			b--;
//		}
		

		{
//			LychrelNumber[] nn = new LychrelNumber[150];
//			for(int i=0;i<nn.length;i++) {
//				int[] ii = new int[8+i];
//				ii[0] = 1;
//				ii[1] = 0;
//				ii[2] = 7;
//				ii[ii.length-3] = 4;
//				ii[ii.length-2] = 7;
//				ii[ii.length-1] = 0;
//				for(int j=0;j<i+2;j++) {
//					ii[3+j]=5;
//				}
//				nn[i] = new LychrelNumber(10,ii);
//			}
//			for(int i=0;i<6;i++) {
//				for(int j=0;j<nn.length;j++) {
////					System.out.print(nn[j]);
//					if(nn[j]!=null) {
//						if(nn[j].isLychrelStop())
//							nn[j] = null;
//						else
//							nn[j] = nn[j].nextLychrelNumber();
//					}
////					System.out.print(" - ");
//				}
////				System.out.println();
//			}
//			for(int j=0;j<nn.length;j++) {
//				System.out.println((j+2)+" - "+((nn[j]==null)?"null":nn[j].toString()));
//			}
		}

		{
			LychrelNumber n = new LychrelNumber(10, new int[] {1,0,7,5,5,4,7,0});
//			LychrelNumber n = new LychrelNumber(10, new int[] {9, 1, 3, 8, 5, 9, 9 , 4, 8, 4, 0, 8});
//			LychrelNumber n = new LychrelNumber(10, new int[] {1, 7, 5, 9, 9, 8, 6, 9, 3, 5, 7, 1, 1, 4, 8, 7, 5, 5, 8, 9, 1, 4, 6, 1, 8, 6, 7, 4, 7, 3, 2, 6, 5, 3, 1, 3, 3, 1, 5, 5, 6, 0, 0, 8, 9, 5, 5, 3, 5, 4, 2, 2, 0, 4, 1, 9, 3, 6, 6, 1, 6, 1, 6, 8, 7, 1, 5, 2, 6, 5, 4, 0, 1, 3, 0, 2, 2, 4, 6, 4, 5, 5, 9, 7, 9, 9, 6, 5, 4, 2, 3, 2, 2, 3, 4, 7, 3, 2, 6, 5, 7, 5, 9, 2, 6, 3, 0, 9, 8, 5, 6, 8, 8, 4, 1, 0, 6, 6, 3, 9, 6, 7, 9, 9, 6, 7});
			int i;
			for(i=0;i<55;i++) {
				System.out.println(i+":"+n.toString());
				if(n.isLychrelStop())
					break;
				n = n.nextLychrelNumber();
			}
			System.out.println("Steps: "+i);
		}
		
//		for(int i=1000000000;i<1000010000;i++) {
//			LychrelNumber n = new LychrelNumber(10, i);
//			for(int j=0;j<1000;j++) {
//				n=n.nextLychrelNumber();
//	//			System.out.println(""+i+": "+n.toString());
//				if(n.isLychrelStop())
//					break;
//			}
//			if(!n.isLychrelStop()) {
//				System.out.print(i+" - ");
//				if(i%9==0) {
//					System.out.print(9);
//				}
//				else if(i%3==0) {
//					System.out.print(3);
//				}
//				else {
//					System.out.print(1);
//				}
//				System.out.println();
//			}
//		}
	}
	
	
	
	/**
	 * Exploring behavior of n/(stable_primes)
	 * 
	 * @param n
	 * @param steps
	 */
	public static void printLychrelNumberReportExaminingUnstablePrimes(LychrelNumber n, int steps) {
		System.out.println("Report:");
		System.out.println("n = "+n);
		int stablePrimes = n.getBase()+1;
		if(n.getDigitalRoot() == n.getBase()-1) {
			stablePrimes *= n.getBase()-1;
		}
		else if((n.getBase() == 10) && (n.getDigitalRoot()%3 == 0)) {
			stablePrimes *= 3;
		}
		System.out.println("stablePrimes = "+stablePrimes);
		for(int i=0;i<steps;i++) {
			DivisionResult dividedByResult = n.dividedBy(stablePrimes);
			if(dividedByResult.getRemainder()!=0) {
				System.out.println((i+1)+": "+n);
			}
			else {
				System.out.println((i+1)+": "+n+" ("+dividedByResult.getNumber()+") "+dividedByResult.getNumber().getPrimefactors());
			}
			if(n.isLychrelStop()&&i>0)
				break;
			n=n.nextLychrelNumber();
		}
	}
	
	/**
	 * Exploring digital roots
	 * @param n
	 * @param steps
	 */
	public static void printLychrelNumberReport(LychrelNumber n, int steps) {
		System.out.println("Report:");
		System.out.println(n);
		{
			LychrelNumber sum = n;
			while(true) {
				long s = sum.getDigitSum();
				System.out.print(s+" ");
				if(s<sum.getBase())
					break;
				sum = new LychrelNumber(sum.getBase(), (int)s);
			}
			System.out.println();
		}
		for(int i = 0;(i < steps)&&(!n.isLychrelStop());i++) {
			n = n.nextLychrelNumber();
			System.out.println(n);
			LychrelNumber sum = n;
			while(true) {
				long s = sum.getDigitSum();
				System.out.print(s+" ");
				if(s<sum.getBase())
					break;
				sum = new LychrelNumber(sum.getBase(), (int)s);
			}
			System.out.println();
		}
		System.out.println();
	}

	/*
	NOTES
	r(x) = the digit reversal of x.
	d(x) = the digital root of x.
	s(x) = x + r(x) (referred to as the next number from x)
	s'(x) = s(s(x)) = s_2(x)
	Lychrel Stop: Palindrome number
	Lychrel number: Number, x, for which no s_n(x) (where n>0) is a Lychrel stop.
	
	Interesting patterns in the infinite base:
	Pa(n,p) = 2^n(, 0)xp, b-2^n, b-1, b-2^n-1(, b-1)xp, 2^n-1 (Never a palindrome.)
	s(Pa(n,p)) = Pa(n+1,p)
	Pa(0,1) Arises naturally from N=b-1, b-1, b-1, b-1 in 3 steps, in bases that can get that far (ie. b>=4).

	Pb(n,p) = 2^n, 2^n(, 0)xp, b-2^n, b-2^n-1, b-2^n-1, b-2^n-1(, b-1)xp, 2^n-1, 2^n (Never a palindrome.)
	s(Pb(n,p)) = Pb(n+1,p)
	Arises naturally in bases of 2^n starting from N=b-1, b-1, b-1, b-1.

	Pc(n,p) = 2^n, 2^n(, 0)xp, b-2^n, b-2^(n+1)-1, b-2^n-1(, b-1)xp, 2^n-1, 2^n (Never a palindrome.)
	s(Pc(n,p)) = Pc(n+1,p)
	Arises naturally in bases of 2^n starting from N=b-1, b-1, b-1, b-1.

    ----------------------
    I investigated how the behavior of 190 through 196 compares and found that 196 seemed to break free of the rest of the pack at the following number:
    N0 = 1,0,7,5,5,4,7,0
    
    I investigated this number and found that if I added digits to the middle, it seemed to have no effect on whether the number would make a palindrome.
    eg.
    1,0,7,5,0,5,4,7,0 continues without palindrome for at least 100,000 steps
    1,0,7,5,0,0,5,4,7,0 continues without palindrome for at least 100,000 steps
    1,0,7,5,9,9,5,4,7,0 continues without palindrome for at least 100,000 steps
    
    Small deviations from the middle 2 numbers cause quick termination. I tested the following, which terminated quickly:
    1,0,7,4,5,4,7,0
    1,0,7,6,5,4,7,0
    
    I ran a test using i = 0 ... 99 creating a number
    N'(i) = 1,0,7,5,floor(i/10),i%10,5,4,7,0
    
    I found that not all of these N' failed to terminate within 1000 steps, though most did fail to terminate.
    Those that did fail to terminate did not appear to take similar routes.
    So, there's something interesting here, but not what I'd hoped.
    (I had hoped that the outer numbers created a stable pattern regardless of the inner numbers.)
    
    I ran a test using i = 0 ... 99 creating a number
    N'(i) = 1,0,7,5(,5)xi,5,4,7,0
    I found that N'(1) terminated within 10,000 steps, but N'(0) did not terminate within 10,000 steps (this was a known case) and all N'(n>2) also did not terminate within 10,000 steps.
    
    I expanded this out for i = 0 ... 999 using only 1000 steps for testing, and I did not find any new numbers terminating.

	----------------------
	Pa(n,1) = 2^n, 0, b-2^n, b-1, b-2^n-1, b-1, 2^n-1
	
	Pa(3,1) = 8, 0, b-8, b-1, b-9, b-1, 7
	s(Pa(3,1)) = 1, 5, 9, b-6, b-2, b-6, 0, 5
	s'(Pa(3,1)) = 6, 6, 4, b-7, b-7, 3, 5, 6
	s''(Pa(3,1)) = 1, 3, 1, 7, b-4, b-4, 8, 2, 2
	s'''(Pa(3,1)) = 3, 6, 0, 4, b-7, 3, 9, 5, 3
	s''''(Pa(3,1)) = 7, 1, 9, 7, b-4, 8, 0, 1, 6
	s'''''(Pa(3,1)) = 1, 3, 3, 0, 6, b-7, 5, 9, 3, 3
	s''''''(Pa(3,1)) = 4, 7, 2, 5, b-1, b-1, 6, 2, 6, 4
	s'''''''(Pa(3,1)) = 9, 3, 5, 2, b-1, b-1, 1, 5, 3, 8
	s''''''''(Pa(3,1)) = 1, 7, 7, 0, 4, b-1, b-2, 4, 0, 7, 7
	s'''''''''(Pa(3,1)) = 9, 4, 7, 5, 3, b-1, 2, 4, 8, 4, 8
	s''''''''''(Pa(3,1)) = 1, 7, 9, 5, 9, 6, b-2, 6, 0, 5, 9, 7
	s'''''''''''(Pa(3,1)) = 9, 7, 4, 6, 6, 5, 5, 5, 6, 5, 6, 8          = 1+8, 1+2+4, 4, 2+4,   2+4, 1+4,             1+4, 1+4,   2+4, 1+4, 2+4,     8
	s''''''''''''(Pa(3,1)) = 1, 8, 4, 0, 3, 2, 1, 1, 2, 3, 0, 4, 7      =   1,     8, 4,   0,   1+2,   2,      1,       1,     2, 1+2,   0,   4, 1+2+4
	s'''''''''''''(Pa(3,1)) = 9, 2, 4, 3, 5, 3, 2, 3, 5, 3, 5, 2, 8     = 1+8,     2, 4, 1+2,   1+4, 1+2,      2,     1+2,   1+4, 1+2, 1+4,   2,     8
	s''''''''''''''(Pa(3,1)) = 1, 7, 4, 9, 7, 0, 6, 4, 7, 0, 6, 9, 5, 7 =   1, 1+2+4, 4, 1+8, 1+2+4,   0, 2+4,        2+2, 1+2+4,   0, 2+4, 1+8,   1+4, 1+2+4

    N = 1, 9, 5
    s(N) = 7, 8, 6
    s'(N) = 1, 4, 7, 3
    s''(N) = 5, 2, 1, 4
    s'''(N) = 9, 3, 3, 9

    N = 1, 9, 6
    s(N) = 8, 8, 7
    s'(N) = 1, 6, 7, 5
    s''(N) = 7, 4, 3, 6
    s'''(N) = 1, 3, 7, 8, 3


	----------------------
	Theorem: For x = 99 * n, r(x) = 99 * r(f(n)) ( => s(x) = 99*(n+r(f(n))) )
	TODO Define f(n)
	
	Examples:
	99 = 99 * 1 => 1
	297 = 99*3 => 8
	2970 = 99*30 => 8
	792 = 99*8 => 3
	3267 = 99*33 => 77
	32670 = 99*330
	7623 = 99*77
	7722 = 99*78
	2277 = 99*23
	8019 = 99*81
	9108 = 99*92
	29997 = 99*303
	79992 = 99*808
	32967 = 99*333
	329670 = 99*3330
	76923 = 99*777
	51777 = 99*523
	77715 = 99*785
	122166 = 99*1234
	661221 = 99*6679
	297297 = 99*3003
	792792 = 99*8008
	300267 = 99*3033
	762003 = 99*7697
	326997 = 99*3303
	799623 = 99*8077
	329967 = 99*3333
	769923 = 99*7777
	2999997 = 99*30303
	7999992 = 99*80808
	3002967 = 99*30333
	7692003 = 99*77697
	3270267 = 99*33033
	7620723 = 99*76977
	3296997 = 99*33303
	7996923 = 99*80777
	3299670 = 99*33330
	 769923 = 99*7777
	32999967 = 99*333333
	76999923 = 99*777777
	12,220,987,779 = 99*123444321
	97,778,902,221 = 99*987665679
	99*m = 100*m - m
	n = A, B, C, D, E = 99 * (a,b,c) = a,b,c-a,-b,-c
	=> r(n) = E,D,C,B,A = -c,-b,c-a,b,a = 99 * (-c, -b, -a)
	s(n) = [a]-(c),0,[c-a]+(c-a),0,(a)-[c] = 99 * (i,j,k)
	
	----------------------
	Proven:  To make a 5-digit palindrome number, n, that is equal to 99 * m, where m is a number of up to 4 digits, (a, b, c, d),
	m must follow one of the following 3 patterns, which are both necessary and sufficient to make n a palindrome number:
	
	A) m = 1001 (multiple of 11)
	B) a = 0, and d < 6
		a) b = 10-d, c = 5, d > 0 (multiple of 3*5)
		b) b = 11-d, c = 0, d > 1 (multiple of 11)
	
	n = A,B,C,B,A = 99*(a,b,c,d) = a,b,c-a,d-b,-c,-d
	Since A != 0 => d != 0
	n = a,b,c-a,d-b,-(c+1),10-d
	=> A = 10-d
	Since c+1 != 0
	n = a,b,c-a,d-b-1,10-(c+1),10-d
	n = a,b,c-a,d-b-1,9-c,10-d
	=> B = 9-c
	Case d-b-1<0:{
	    d<b+1
		n = a,b,c-a-1,10+(d-b-1),9-c,10-d
		C = 10+(d-b-1)
		Case c-a-1<0:{
			n = a,b-1,10+(c-a-1),10+(d-b-1),9-c,10-d
			B = 10+(c-a-1) = 9-c
			=> 2*c = a
			=> -c-1<0
			n = a,b-1,9-c,10+(d-b-1),9-c,10-d
			Case b==0:{
				n = a-1,9,9-c,10+(d-b-1),9-c,10-d
				A = 9 = 10-d;
				=> d = 1;
				n = a-1,9,9-c,10,9-c,9
				=> d-b-1 = 0, contradicting a premise
				=> This case not possible.
			}
			Case b>0:{
				n = a,b-1,9-c,10+(d-b-1),9-c,10-d
				A = b-1 = 10-d
				=> b = 11-d
				=> d > 1
				n = a,10-d,9-c,d*2-2,9-c,10-d
				=> a=0
				n = 10-d,9-c,d*2-2,9-c,10-d
				=> c-1 < 0, 2*d - 12 < 0
				=> c = 0, d < 6
				=> 11 - b < 6
				=> 5 < b
			}
			=> a = 0, b = 11-d, c = 0, d < 6
		}
		Case c-a-1>=0:{
			n = a,b,c-a-1,10+(d-b-1),9-c,10-d
			B = c-a-1 = 9-c
			=> 2*c-10 = a
			=> 9-c>=0
			n = a,b,9-c,10+(d-b-1),9-c,10-d
			=> b = 10-d
			n = a,10-d,9-c,2*d-1,9-c,10-d
			=> a = 0, 9 >= c
			=> 2*c-10 = 0
			=> c = 5
			n = 10-d,4,2*d-1,4,10-d
			d-b-1 < 0
			=> 2*d-11 < 0
			=> 2*d < 11
			=> d < 6
			=> a = 0, b = 10-d, c = 5, d < 6
		}
		=> a = 0, d < 6, (b = 10-d, c = 5) or (b = 11-d, c = 0)
	}
	Case d-b-1>=0:{
	    d>=b+1
		n = a,b,c-a,d-b-1,9-c,10-d
		C = d-b-1
		Case c-a<0:{
			n = a,b-1,10+(c-a),d-b-1,9-c,10-d
			B = 10+(c-a) = 9-c
			=> 1+2*c = a
			n = a,b-1,9-c,d-b-1,9-c,10-d
			Case b==0:{
				n = a-1,9,9-c,d-b-1,9-c,10-d
				A = 9 = 10-d
				=> d = 1;
				n = a-1,9,9-c,0,9-c,9
				=> a-1 = 0
				=> a = 1
				=> c-1 < 0
				=> c < 1
				=> c = 0;
				=> a = 1, b = 0, c = 0, d = 1
				=> n = 99*1001
			}
			Case b>0:{
				n = a,b-1,9-c,d-b-1,9-c,10-d
				A = b-1 = 10-d
				=> b = 11 - d
				n = a,10-d,9-c,d-b-1,9-c,10-d
				=> a = 0
				n = 10-d,9-c,d-b-1,9-c,10-d
				=> c < 0
				=> This case not possible.
			}
			=> a = 1, b = 0, c = 0, d = 1
			=> n = 99*1001
		}
		Case c-a>=0:{
			n = a,b,c-a,d-b-1,9-c,10-d
			B = c-a = 9-c
			=> 2*c-9 = a
			n = a,b,9-c,d-b-1,9-c,10-d
			A = b = 10 - d
			a = 0
			=> 2*c-9 = 0
			=> 2*c = 9
			=> This case not possible.
		}
		=> a = 1, b = 0, c = 0, d = 1
		=> n = 99*1001
	}
	
	----------------------
	TBD
	n = A,B,C,D,...,D,C,B,A = 99*(z,y,x,w,,...,d,c,b,a) = z,y,x-z,w-y,...,b-d,a-c,-b,-a
	(Note: the number of digits in z,y,x,w,,...,d,c,b,a is one fewer than the number of digits in A,B,C,D,...,D,C,B,A.
	It will only be possible for z to be one or zero in these cases, but I want to allow for that possibility.)
	Since A != 0 => a != 0
	n = z,y,x-z,w-y,...,b-d,a-c,-(b+1),10-a
	=> A = 10-a
	Since b+1 != 0
	n = z,y,x-z,w-y,...,b-d,a-c-1,10-(b+1),10-a
	n = z,y,x-z,w-y,...,b-d,a-c-1,9-b,10-a
	=> B = 9-b
	
	All the non-boundary digits have one of four basic forms:
	a-c (if the digit to the right did not borrow and a-c>=0)
	a-c-1 (if the digit to the right did borrow and a-c-1>=0)
	10+(a-c) (if the digit to the right did not borrow and a-c<0)
	10+(a-c-1) = 9+a-c (if the digit to the right did borrow and a-c-1<0)
	
	The z column must be zero.
	When the y column borrows, then z-1 = 0 => z = 1.
	When the y column doesn't borrow, then z = 0.
	The y column must equal A.
	The y column will have one of 4 forms:
	y = 10-a => z = 0
	y-1 = 10-a => z = 0, y > 0
	10+y = 10-a => z = 1 => Not possible, as y borrowing here implies y<0
	10+y-1 = 9+y = 10-a => z = 1, y = 0 (because borrowing implies y-1 < 0) => a = 1
	
	Case z = 1:{
		=> y = 0, a = 1
		n = 9,10+x-1,w,...,b-d,-c,9-b,9 = 99 * (1, 0, ..., 1)
		Case w column borrows:{
			n = 9,10+x-2,10-w,...,b-d,-c,9-b,9 = 99 * (1, 0, ..., 1)
			=> x = 0 or 1
			Case x = 0:{
				n = 9,8,10-w,...,b-d,-c,9-b,9 = 99 * (1, 0, 0, ..., 1)
				=> b = 1
				n = 9,8,10-w,...,1-d,-c,8,9 = 99 * (1, 0, 0, ..., 1, 1)
			}
			Case x = 1:{
				n = 9,9,10-w,...,b-d,-c,9-b,9 = 99 * (1, 0, 1, ..., 1)
				=> b = 0
				n = 9,9,10-w,...,-d,-c,9,9 = 99 * (1, 0, 1, ..., 0, 1)
			}
		}
		Case w column doesn't borrow:{
			n = 9,10+x-1,w,...,b-d,-c,9-b,9 = 99 * (1, 0, ..., 1)
			=> x = 0
			n = 9,9,w,v,...,b-d,-c,9-b,9 = 99 * (1, 0, ..., 1)
			=> b = 0
		}
	}
	Case z = 0:{
		n = y,x,x-y,...,b-d,a-c-1,9-b,10-a
		Case y = 10-a:{
		}
		Case y-1 = 10-a:{
		}
	}
	
	----------------------
1,...,0
1,...,1 or 2,...,1
2,...,2 or 3,...,2 or (3,...,3) or (4,...,3)
4,...,4	or 5,...,4 or (5,...,5) or (6,...,5)
8,...,8	or 9,...,8 or 9,...,9 or 1,...,9
1,...,6 or 1,...,7 or 1,...,8 or (1,...,0)
7,...,7 or 8,...,7 or 8,...,8 or 9,...,8 or (9,...,9) or (1,...,9)
1,...,4 or 1,...,5 or (1,...,6) or (1,...,7)
5,...,5 or 6,...,5 or 6,...,6 or 7,...,6
(1,...,0) or (1,...,1) or 1,...,2 or 1,...,3
3,...,3 or 4,...,3 or (4,...,4) or (5,...,4)
6,...,6 or 7,...,6 or (7,...,7) or, (8,...,7)
(1,...,2) or (1,...,3)

1,0->1,1 or 2,1
1,1->2,2 or 3,2
1,2->3,3 or 4,3
1,3->4,4 or 5,4
1,4->5,5 or 6,5
1,5->6,6 or 7,6
1,6->7,7 or 8,7
1,7->8,8 or 9,8
1,8->9,9 or 1,9
1,9->1,0
2,1->3,3 or 4,3
2,2->4,4 or 5,4
3,2->5,5 or 6,5
3,3->6,6 or 7,6
4,3->7,7 or 8,7
4,4->8,8 or 9,8
5,4->9,9 or 1,9
5,5->1,0
6,5->1,1
6,6->1,2
7,6->1,3
7,7->1,4
8,7->1,5
8,8->1,6
9,8->1,7
9,9->1,8

26/99 possible combinations of leading/trailing digits
Only 6,5 leads to a possible palindrome with carry. 

	
	ANALOGY WITH BASE 4
	
	A number is divisible by 3 iff the sum of its digits is divisible by 3, and hence.
	Equivalently, a number is divisible by 3 iff its digital root is 3.
	(Analogous to base 10's 9.)
	

1: LychrelNumber [base=10, digits=[9, 9, 9, 9]]
 1: LychrelNumber [base=8, digits=[7, 7, 7, 7]]
 1: LychrelNumber [base=4, digits=[3, 3, 3, 3]]

2: LychrelNumber [base=10, digits=[1, 9, 9, 9, 8]]
 2: LychrelNumber [base=8, digits=[1, 7, 7, 7, 6]]
 2: LychrelNumber [base=4, digits=[1, 3, 3, 3, 2]]

3: LychrelNumber [base=10, digits=[1, 0, 9, 9, 8, 9]]
 3: LychrelNumber [base=8, digits=[1, 0, 7, 7, 6, 7]]
 3: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3]]

4: LychrelNumber [base=10, digits=[1, 0, 9, 9, 8, 9, 0]]
 4: LychrelNumber [base=8, digits=[1, 0, 7, 7, 6, 7, 0]]
 4: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3, 0]]

5: 2, 0, n-2, n-1, n-3, n-1, 1
5: LychrelNumber [base=10, digits=[2, 0, 8, 9, 7, 9, 1]]
 5: LychrelNumber [base=8, digits=[2, 0, 6, 7, 5, 7, 1]]
 5: LychrelNumber [base=4, digits=[2, 0, 2, 3, 1, 3, 1]]

6: 4, 0, n-4, n-1, n-5, n-1, 3
6: LychrelNumber [base=10, digits=[4, 0, 6, 9, 5, 9, 3]]
 6: LychrelNumber [base=8, digits=[4, 0, 4, 7, 3, 7, 3]]

7: 8, 0, n-8, n-1, n-9, n-1, 7
7: LychrelNumber [base=10, digits=[8, 0, 2, 9, 1, 9, 7]]

8: 1, 5, n-1, 2*n-16, n-2, 2*n-16, 0, 5
8: LychrelNumber [base=10, digits=[1, 5, 9, 4, 8, 4, 0, 5]]
 7: LychrelNumber [base=8, digits=[1, 0, 0, 0, 6, 7, 7, 7]]
 6: LychrelNumber [base=4, digits=[1, 0, 0, 0, 2, 3, 3, 3]]

9: 6, 6, 2*n-16, 2*n-17, 2*n-17, 2*n-17, 5, 6
9: LychrelNumber [base=10, digits=[6, 6, 4, 3, 3, 3, 5, 6]]

16: 1, 7, 7, 47*n-470, 154*n-1536, 213*n-2121, 213*n-2122, 154*n-1536, 47*n-470, 7, 7
10: 1, 3, 1,              4*n-33,   4*n-34,     4*n-34,     4*n-32,              2, 2
10: 1, 3, 1, 4*n-33, 4*n-34, 4*n-34, 4*n-32, 2, 2
*10: LychrelNumber [base=10, digits=[1, 3, 1, 7, 6, 6, 8, 2, 2]]
*  Carry pattern                     0, 0, 2, 1, 1, 1, 0, 0, 0
*  8: LychrelNumber [base=8, digits=[1, 0, 7, 7, 6, 7, 0, 0, 0]]
*  Carry pattern                     0, 0, 2, 1, 1, 1, 0, 0, 0
*  7: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3, 0, 0, 0]]
*  Carry pattern                     0, 0, 2, 1, 1, 1, 0, 0, 0

11: 3, 6, 3*n-30, 7*n-66, 7*n-67, 7*n-67, 4*n-31, 5, 3
11: LychrelNumber [base=10, digits=[3, 6, 0, 4, 3, 3, 9, 5, 3]]
  Carry pattern                     0, 1, 0, 0, 0, 0, 2, 1, 0
  9: LychrelNumber [base=8, digits=[1, 1, 0, 7, 5, 6, 7, 0, 1]]
  Carry pattern                     0, 0, 2, 1, 1, 1, 0, 0, 0
  8: LychrelNumber [base=4, digits=[1, 1, 0, 3, 1, 2, 3, 0, 1]]
  Carry pattern                     0, 0, 2, 1, 0, 1, 0, 0, 0

12: 7, 1, 7*n-61, 14*n-133, 14*n-134, 14*n-132, 6*n-60, 1, 6
12: LychrelNumber [base=10, digits=[7, 1, 9, 7, 6, 8, 0, 1, 6]]
  Carry pattern                     1, 0, 2, 1, 1, 1, 0, 0, 1
 10: LychrelNumber [base=8, digits=[2, 2, 0, 6, 3, 5, 7, 1, 2]]
  Carry pattern                     0, 0, 2, 1, 0, 1, 0, 0, 0
  9: LychrelNumber [base=4, digits=[2, 2, 0, 1, 3, 1, 3, 1, 2]]
  Carry pattern                     1, 0, 0, 0, 1, 0, 0, 2, 1
 11: LychrelNumber [base=8, digits=[4, 4, 0, 3, 7, 3, 7, 3, 4]]
  Carry pattern                     1, 0, 0, 0, 1, 0, 2, 2, 1

13: 1, 3, 3, 12*n-120, 27*n-264, 27*n-267, 27*n-265, 13*n-121, 3, 3
13: LychrelNumber [base=10, digits=[1, 3, 3, 0, 6, 3, 5, 9, 3, 3]]
  Carry pattern                     0, 0, 1, 0, 0, 0, 0, 1, 0, 0
 12: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 6, 7, 0, 0, 0]]
  Carry pattern                     0, 0, 2, 1, 1, 1, 1, 0, 0, 0
 10: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 2, 3, 0, 0, 0]]
  Carry pattern                     1, 0, 0, 1, 1, 1, 1, 0, 0, 1

14: 4, 7, 12*n-118, 39*n-385, 54*n-531, 54*n-531, 39*n-384, 12*n-118, 6, 4
14: LychrelNumber [base=10, digits=[4, 7, 2, 5, 9, 9, 6, 2, 6, 4]]
  Carry pattern                     0, 1, 0, 1, 1, 1, 1, 0, 1, 0
 13: LychrelNumber [base=8, digits=[1, 1, 0, 7, 6, 6, 6, 7, 0, 1]]
 11: LychrelNumber [base=4, digits=[1, 1, 0, 3, 2, 2, 2, 3, 0, 1]]
  Carry pattern                     0, 0, 0, 1, 1, 1, 1, 0, 0, 0

15: 9, 3, 24*n-235, 77*n-768, 107*n-1061, 107*n-1061, 77*n-769, 24*n-235, 3, 8
15: LychrelNumber [base=10, digits=[9, 3, 5, 2, 9, 9, 1, 5, 3, 8]]
  Carry pattern                     1, 0, 1, 0, 1, 1, 0, 1, 0, 1
 14: LychrelNumber [base=8, digits=[2, 2, 0, 6, 5, 5, 5, 7, 1, 2]]
 12: LychrelNumber [base=4, digits=[2, 2, 0, 2, 1, 1, 1, 3, 1, 2]]
  Carry pattern                     1, 0, 0, 1, 1, 1, 1, 0, 0, 1
 15: LychrelNumber [base=8, digits=[4, 4, 0, 4, 3, 3, 3, 7, 3, 4]]

16: 1, 7, 7, 47*n-470, 154*n-1536, 213*n-2121, 213*n-2122, 154*n-1536, 47*n-470, 7, 7
*16: LychrelNumber [base=10, digits=[1, 7, 7, 0, 4, 9, 8, 4, 0, 7, 7]]
   Carry pattern                     1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1
* 16: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 6, 7, 0, 0, 0, 0]]
   Carry pattern                     0, 0, 2, 2, 1, 1, 1, 0, 0, 0, 0
* 13: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 2, 3, 0, 0, 0, 0]]
   Carry pattern                     0, 0, 2, 2, 1, 1, 1, 0, 0, 0, 0

14: LychrelNumber [base=4, digits=[1, 1, 0, 0, 3, 1, 2, 3, 3, 0, 1]]
 Carry pattern                     0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0

17: LychrelNumber [base=10, digits=[9, 4, 7, 5, 3, 9, 2, 4, 8, 4, 8]]
  Carry pattern                     1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1

15: LychrelNumber [base=4, digits=[2, 2, 0, 0, 1, 3, 1, 3, 3, 1, 2]]
 Carry pattern                     1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1

18: LychrelNumber [base=10, digits=[1, 7, 9, 5, 9, 6, 8, 6, 0, 5, 9, 7]]
  Carry pattern                     0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0
19: LychrelNumber [base=10, digits=[9, 7, 4, 6, 6, 5, 5, 5, 6, 5, 6, 8]]
  Carry pattern                     1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1
  
20: LychrelNumber [base=10, digits=[1, 8, 4, 0, 3, 2, 1, 1, 2, 3, 0, 4, 7]]
  Carry pattern                     0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0
21: LychrelNumber [base=10, digits=[9, 2, 4, 3, 5, 3, 2, 3, 5, 3, 5, 2, 8]]
  Carry pattern                     1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1
22: LychrelNumber [base=10, digits=[1, 7, 4, 9, 7, 0, 6, 4, 7, 0, 6, 9, 5, 7]]
  Carry pattern                     0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0
23: LychrelNumber [base=10, digits=[9, 3, 4, 5, 7, 8, 1, 0, 7, 8, 6, 4, 2, 8]]
  Carry pattern                     1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1


24: LychrelNumber [base=10, digits=[1, 7, 5, 9, 2, 6, 5, 1, 2, 6, 6, 1, 8, 6, 7]]
  Carry pattern                     0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0
25: LychrelNumber [base=10, digits=[9, 4, 4, 0, 9, 2, 7, 2, 8, 2, 9, 1, 4, 3, 8]]
  Carry pattern                     1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1
26: LychrelNumber [base=10, digits=[1, 7, 7, 8, 2, 8, 5, 5, 5, 5, 5, 8, 1, 8, 8, 7]]
  Carry pattern                     0, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0
27: LychrelNumber [base=10, digits=[9, 6, 6, 0, 1, 4, 1, 1, 1, 1, 4, 1, 0, 6, 5, 8]]
28: LychrelNumber [base=10, digits=[1, 8, 2, 2, 0, 2, 8, 2, 2, 2, 2, 8, 2, 1, 3, 2, 7]]
29: LychrelNumber [base=10, digits=[9, 0, 5, 3, 3, 1, 0, 4, 4, 5, 1, 0, 2, 3, 6, 0, 8]]
30: LychrelNumber [base=10, digits=[1, 7, 1, 1, 6, 5, 1, 1, 9, 8, 9, 1, 1, 5, 7, 1, 1, 7]]
31: LychrelNumber [base=10, digits=[8, 8, 2, 9, 1, 6, 3, 1, 8, 8, 0, 2, 7, 1, 8, 2, 8, 8]]

32: LychrelNumber [base=10, digits=[1, 7, 6, 5, 7, 3, 3, 5, 2, 7, 6, 1, 6, 3, 3, 7, 5, 7, 6]]
33: LychrelNumber [base=10, digits=[8, 5, 2, 3, 0, 6, 9, 6, 9, 4, 8, 6, 9, 7, 1, 3, 2, 4, 7]]
34: LychrelNumber [base=10, digits=[1, 5, 9, 4, 6, 2, 4, 9, 3, 7, 9, 8, 3, 9, 3, 1, 6, 5, 0, 5]]
35: LychrelNumber [base=10, digits=[6, 6, 5, 0, 7, 6, 4, 3, 2, 7, 7, 2, 3, 3, 5, 8, 1, 4, 5, 6]]
36: LychrelNumber [base=10, digits=[1, 3, 1, 9, 2, 6, 1, 7, 6, 5, 5, 4, 4, 6, 8, 2, 5, 2, 0, 2, 2]]
37: LychrelNumber [base=10, digits=[3, 5, 2, 1, 7, 9, 0, 4, 1, 0, 1, 0, 1, 3, 9, 8, 8, 1, 1, 5, 3]]
38: LychrelNumber [base=10, digits=[7, 0, 3, 3, 6, 7, 9, 7, 2, 0, 2, 0, 2, 8, 0, 8, 5, 2, 4, 0, 6]]
39: LychrelNumber [base=10, digits=[1, 3, 0, 7, 6, 2, 6, 0, 5, 4, 0, 4, 0, 5, 6, 0, 6, 1, 5, 7, 1, 3]]
40: LychrelNumber [base=10, digits=[4, 4, 8, 2, 7, 8, 6, 7, 0, 4, 4, 4, 5, 0, 6, 6, 8, 8, 2, 7, 4, 4]]
41: LychrelNumber [base=10, digits=[8, 9, 5, 5, 6, 7, 3, 3, 0, 9, 8, 8, 9, 1, 4, 3, 7, 5, 5, 5, 8, 8]]

42: LychrelNumber [base=10, digits=[1, 7, 8, 1, 1, 2, 4, 6, 7, 2, 9, 7, 7, 8, 1, 7, 7, 5, 2, 1, 1, 8, 6]]
43: LychrelNumber [base=10, digits=[8, 5, 9, 2, 3, 8, 2, 3, 9, 1, 7, 5, 7, 0, 9, 4, 1, 7, 3, 3, 0, 5, 7]]
44: LychrelNumber [base=10, digits=[1, 6, 0, 9, 5, 7, 5, 3, 8, 8, 2, 5, 1, 4, 2, 8, 7, 4, 5, 6, 6, 0, 1, 5]]
45: LychrelNumber [base=10, digits=[6, 7, 1, 6, 2, 3, 0, 1, 7, 0, 6, 6, 6, 7, 1, 7, 1, 0, 3, 2, 5, 0, 7, 6]]
46: LychrelNumber [base=10, digits=[1, 3, 4, 2, 1, 4, 6, 0, 3, 4, 2, 4, 3, 3, 3, 2, 4, 2, 0, 6, 5, 1, 2, 5, 2]]
47: LychrelNumber [base=10, digits=[3, 8, 6, 3, 7, 0, 6, 2, 7, 6, 5, 7, 6, 7, 5, 6, 7, 2, 7, 0, 6, 3, 6, 8, 3]]
48: LychrelNumber [base=10, digits=[7, 7, 2, 7, 3, 1, 3, 5, 5, 3, 1, 5, 3, 5, 1, 3, 4, 5, 3, 1, 3, 7, 3, 6, 6]]
49: LychrelNumber [base=10, digits=[1, 4, 3, 6, 4, 6, 2, 7, 0, 9, 6, 3, 0, 7, 0, 2, 7, 0, 0, 6, 2, 7, 4, 6, 4, 3]]
50: LychrelNumber [base=10, digits=[4, 9, 0, 1, 1, 8, 8, 7, 1, 6, 8, 3, 7, 7, 3, 9, 6, 0, 7, 8, 9, 2, 0, 9, 8, 4]]
51: LychrelNumber [base=10, digits=[9, 7, 9, 1, 4, 8, 7, 4, 2, 3, 7, 7, 5, 4, 7, 8, 2, 2, 5, 7, 7, 3, 2, 0, 7, 8]]

52: LychrelNumber [base=10, digits=[1, 8, 4, 9, 3, 8, 6, 4, 9, 4, 6, 6, 5, 0, 0, 5, 5, 5, 5, 0, 5, 5, 7, 4, 0, 5, 7]]
53: LychrelNumber [base=10, digits=[9, 3, 5, 4, 1, 4, 1, 5, 5, 0, 2, 1, 5, 0, 6, 2, 2, 0, 4, 5, 2, 4, 1, 3, 5, 3, 8]]

*16: LychrelNumber [base=10, digits=[1, 7, 7, 0,                                             4, 9, 8, 4,        0, 7, 7]]
 54: LychrelNumber [base=10, digits=[1, 7, 7, 0, 7, 2, 8, 4, 0, 9, 0, 4, 4, 1, 1, 1, 3, 4, 1, 0, 0, 3, 8, 2, 8, 0, 7, 7]]
17: LychrelNumber [base=10, digits=[9, 4, 7, 5, 3, 9, 2, 4, 8, 4, 8]]
55: LychrelNumber [base=10, digits=[9, 4, 7, 9, 0, 1, 1, 4, 1, 0, 4, 7, 5, 2, 2, 5, 7, 5, 0, 0, 5, 2, 0, 9, 8, 8, 4, 8]]
18: LychrelNumber [base=10, digits=[1, 7, 9, 5, 9, 6, 8, 6, 0, 5, 9, 7]]
56: LychrelNumber [base=10, digits=[1, 7, 9, 6, 7, 9, 1, 3, 9, 1, 1, 0, 5, 0, 4, 5, 1, 4, 9, 0, 1, 9, 3, 2, 0, 8, 5, 9, 7]]
19: LychrelNumber [base=10, digits=[9, 7, 4, 6, 6, 5, 5, 5, 6, 5, 6, 8]]
57: LychrelNumber [base=10, digits=[9, 7, 5, 4, 8, 1, 5, 3, 0, 2, 0, 4, 6, 5, 8, 5, 6, 5, 0, 2, 1, 2, 5, 1, 8, 5, 5, 6, 8]]
20: LychrelNumber [base=10, digits=[1, 8, 4, 0, 3, 2, 1, 1, 2, 3, 0, 4, 7]]
58: LychrelNumber [base=10, digits=[1, 8, 4, 1, 0, 6, 3, 0, 5, 1, 4, 1, 0, 3, 1, 7, 1, 2, 9, 0, 4, 1, 6, 0, 3, 7, 0, 1, 4, 7]]
59: LychrelNumber [base=10, digits=[9, 2, 5, 1, 7, 9, 3, 6, 6, 5, 5, 0, 2, 4, 8, 8, 4, 3, 0, 4, 5, 6, 6, 3, 9, 7, 1, 6, 2, 8]]

60: LychrelNumber [base=10, digits=[1, 7, 5, 1, 3, 5, 8, 7, 3, 3, 0, 9, 0, 5, 9, 7, 6, 8, 5, 1, 0, 1, 3, 2, 7, 9, 4, 3, 1, 5, 7]]
61: LychrelNumber [base=10, digits=[9, 2, 6, 4, 8, 5, 5, 9, 6, 4, 1, 0, 6, 4, 6, 5, 6, 3, 6, 0, 0, 4, 7, 0, 6, 4, 7, 4, 7, 2, 8]]
62: LychrelNumber [base=10, digits=[1, 7, 5, 3, 9, 6, 0, 2, 0, 3, 8, 1, 1, 2, 8, 3, 1, 2, 8, 2, 0, 1, 9, 4, 0, 2, 0, 5, 9, 3, 5, 7]]
21: LychrelNumber [base=10, digits=[9, 2, 4, 3, 5, 3, 2, 3, 5, 3, 5, 2, 8]]
63: LychrelNumber [base=10, digits=[9, 2, 9, 3, 4, 6, 2, 2, 5, 2, 9, 1, 4, 1, 0, 4, 5, 1, 0, 3, 2, 0, 2, 4, 2, 2, 7, 5, 2, 9, 2, 8]]
22: LychrelNumber [base=10, digits=[1, 7, 4, 9, 7, 0, 6, 4, 7, 0, 6, 9, 5, 7]]
64: LychrelNumber [base=10, digits=[1, 7, 5, 8, 6, 0, 3, 4, 4, 9, 4, 9, 3, 7, 1, 1, 9, 9, 1, 1, 7, 3, 9, 4, 9, 4, 5, 3, 9, 6, 8, 5, 7]]
23: LychrelNumber [base=10, digits=[9, 3, 4, 5, 7, 8, 1, 0, 7, 8, 6, 4, 2, 8]]
65: LychrelNumber [base=10, digits=[9, 3, 4, 5, 5, 3, 8, 9, 4, 4, 4, 3, 0, 8, 3, 1, 9, 0, 2, 9, 1, 3, 4, 4, 3, 8, 8, 4, 6, 5, 4, 2, 8]]
24: LychrelNumber [base=10, digits=[1, 7, 5, 9, 2, 6, 5, 1, 2, 6, 6, 1, 8, 6, 7]]
66: LychrelNumber [base=10, digits=[1, 7, 5, 9, 1, 1, 8, 7, 7, 7, 8, 8, 6, 2, 7, 5, 2, 8, 1, 6, 7, 1, 6, 8, 8, 8, 8, 6, 8, 2, 0, 8, 6, 7]]
25: LychrelNumber [base=10, digits=[9, 4, 4, 0, 9, 2, 7, 2, 8, 2, 9, 1, 4, 3, 8]]
67: LychrelNumber [base=10, digits=[9, 4, 3, 9, 4, 0, 5, 6, 6, 6, 7, 4, 8, 0, 3, 7, 1, 0, 7, 3, 9, 8, 5, 7, 6, 6, 6, 4, 9, 4, 0, 4, 3, 8]]

26: LychrelNumber [base=10, digits=[1, 7, 7, 8, 2, 8, 5, 5, 5, 5, 5, 8, 1, 8, 8, 7]]
68: LychrelNumber [base=10, digits=[1, 7, 7, 7, 9, 9, 0, 0, 3, 3, 3, 5, 0, 6, 9, 7, 4, 1, 2, 4, 7, 0, 7, 0, 5, 3, 3, 2, 9, 9, 8, 9, 7, 8, 7]]
27: LychrelNumber [base=10, digits=[9, 6, 6, 0, 1, 4, 1, 1, 1, 1, 4, 1, 0, 6, 5, 8]]
69: LychrelNumber [base=10, digits=[9, 6, 5, 7, 8, 8, 9, 2, 6, 6, 8, 5, 7, 7, 7, 1, 6, 2, 7, 2, 6, 6, 7, 5, 8, 6, 6, 3, 0, 9, 8, 7, 5, 5, 8]]
28: LychrelNumber [base=10, digits=[1, 8, 2, 2, 0, 2, 8, 2, 2, 2, 2, 8, 2, 1, 3, 2, 7]]
70: LychrelNumber [base=10, digits=[1, 8, 2, 1, 5, 7, 7, 9, 6, 3, 3, 7, 1, 5, 4, 3, 4, 3, 5, 3, 4, 4, 4, 5, 1, 7, 3, 2, 6, 0, 8, 7, 5, 1, 2, 7]]
29: LychrelNumber [base=10, digits=[9, 0, 5, 3, 3, 1, 0, 4, 4, 5, 1, 0, 2, 3, 6, 0, 8]]
71: LychrelNumber [base=10, digits=[9, 0, 3, 7, 3, 5, 8, 5, 8, 7, 0, 8, 6, 9, 8, 7, 7, 8, 8, 7, 7, 8, 9, 6, 9, 0, 6, 9, 5, 8, 6, 2, 6, 4, 0, 8]]
30: LychrelNumber [base=10, digits=[1, 7, 1, 1, 6, 5, 1, 1, 9, 8, 9, 1, 1, 5, 7, 1, 1, 7]]
72: LychrelNumber [base=10, digits=[1, 7, 0, 8, 3, 6, 2, 7, 1, 8, 3, 1, 8, 3, 9, 7, 5, 5, 7, 7, 5, 5, 7, 9, 3, 7, 1, 4, 8, 1, 7, 1, 6, 3, 7, 1, 7]]
31: LychrelNumber [base=10, digits=[8, 8, 2, 9, 1, 6, 3, 1, 8, 8, 0, 2, 7, 1, 8, 2, 8, 8]]
73: LychrelNumber [base=10, digits=[8, 8, 8, 1, 9, 7, 9, 9, 0, 2, 4, 9, 2, 3, 7, 3, 1, 3, 5, 3, 1, 3, 7, 3, 1, 8, 5, 2, 9, 8, 9, 8, 0, 1, 7, 8, 8]]
32: LychrelNumber [base=10, digits=[1, 7, 6, 5, 7, 3, 3, 5, 2, 7, 6, 1, 6, 3, 3, 7, 5, 7, 6]]
74: LychrelNumber [base=10, digits=[1, 7, 7, 5, 3, 0, 6, 9, 7, 9, 5, 0, 7, 3, 7, 4, 6, 2, 7, 0, 6, 2, 7, 4, 6, 4, 7, 9, 5, 0, 8, 9, 5, 9, 3, 6, 7, 6]]
33: LychrelNumber [base=10, digits=[8, 5, 2, 3, 0, 6, 9, 6, 9, 4, 8, 6, 9, 7, 1, 3, 2, 4, 7]]
75: LychrelNumber [base=10, digits=[8, 5, 3, 9, 2, 6, 6, 7, 8, 5, 4, 8, 2, 0, 2, 1, 8, 8, 7, 7, 8, 9, 2, 2, 0, 1, 8, 5, 4, 8, 8, 5, 6, 2, 9, 4, 4, 7]]

34: LychrelNumber [base=10, digits=[1, 5, 9, 4, 6, 2, 4, 9, 3, 7, 9, 8, 3, 9, 3, 1, 6, 5, 0, 5]]
76: LychrelNumber [base=10, digits=[1, 5, 9, 8, 8, 5, 3, 2, 6, 7, 0, 0, 6, 3, 0, 4, 4, 8, 7, 5, 5, 7, 7, 3, 4, 0, 4, 7, 0, 0, 7, 6, 2, 2, 5, 8, 8, 0, 5]]
35: LychrelNumber [base=10, digits=[6, 6, 5, 0, 7, 6, 4, 3, 2, 7, 7, 2, 3, 3, 5, 8, 1, 4, 5, 6]]
77: LychrelNumber [base=10, digits=[6, 6, 8, 7, 3, 7, 5, 9, 3, 7, 0, 8, 0, 3, 4, 8, 2, 6, 3, 1, 3, 6, 1, 7, 4, 4, 0, 7, 0, 8, 3, 8, 5, 8, 4, 7, 7, 5, 6]]
36: LychrelNumber [base=10, digits=[1, 3, 1, 9, 2, 6, 1, 7, 6, 5, 5, 4, 4, 6, 8, 2, 5, 2, 0, 2, 2]]
78: LychrelNumber [base=10, digits=[1, 3, 2, 6, 4, 8, 6, 1, 7, 7, 5, 1, 5, 0, 7, 9, 5, 4, 2, 6, 2, 7, 2, 4, 5, 8, 7, 1, 5, 1, 5, 7, 8, 1, 5, 8, 5, 6, 2, 2]]
37: LychrelNumber [base=10, digits=[3, 5, 2, 1, 7, 9, 0, 4, 1, 0, 1, 0, 1, 3, 9, 8, 8, 1, 1, 5, 3]]
79: LychrelNumber [base=10, digits=[3, 5, 9, 2, 3, 3, 8, 0, 5, 2, 6, 6, 6, 8, 6, 4, 9, 6, 9, 8, 8, 9, 7, 0, 5, 5, 7, 6, 6, 7, 3, 4, 9, 8, 4, 3, 1, 8, 5, 3]]
80: LychrelNumber [base=10, digits=[7, 1, 7, 3, 6, 8, 6, 9, 9, 6, 4, 3, 3, 6, 2, 0, 0, 4, 9, 7, 7, 9, 4, 0, 0, 2, 6, 3, 3, 3, 6, 0, 0, 6, 7, 6, 4, 8, 0, 6]]
81: LychrelNumber [base=10, digits=[1, 3, 2, 5, 8, 3, 6, 3, 0, 0, 2, 7, 6, 7, 2, 4, 0, 0, 9, 9, 5, 5, 8, 8, 0, 0, 5, 2, 6, 6, 8, 3, 0, 0, 3, 6, 2, 8, 5, 2, 3]]
82: LychrelNumber [base=10, digits=[4, 5, 8, 4, 0, 9, 9, 3, 0, 4, 1, 4, 2, 9, 7, 4, 0, 9, 8, 5, 1, 5, 7, 8, 0, 4, 8, 0, 3, 4, 0, 3, 0, 4, 0, 0, 1, 3, 7, 5, 4]]
83: LychrelNumber [base=10, digits=[9, 1, 5, 7, 1, 9, 9, 7, 0, 7, 1, 8, 6, 0, 5, 8, 1, 8, 6, 0, 3, 1, 6, 7, 0, 9, 5, 9, 5, 8, 1, 7, 0, 7, 9, 9, 1, 8, 6, 0, 8]]
84: LychrelNumber [base=10, digits=[1, 7, 2, 2, 5, 3, 9, 9, 4, 1, 4, 3, 7, 2, 0, 1, 7, 2, 6, 2, 1, 6, 2, 3, 5, 2, 8, 1, 0, 2, 6, 3, 4, 1, 5, 9, 8, 3, 6, 1, 2, 7]]
85: LychrelNumber [base=10, digits=[8, 9, 3, 8, 9, 2, 9, 4, 5, 5, 7, 9, 9, 2, 1, 9, 9, 7, 9, 4, 7, 7, 4, 9, 7, 9, 9, 1, 2, 9, 9, 7, 5, 6, 5, 9, 1, 8, 8, 3, 9, 8]]

86: LychrelNumber [base=10, digits=[1, 7, 8, 7, 7, 7, 4, 9, 0, 2, 1, 5, 9, 8, 4, 3, 9, 9, 5, 8, 9, 5, 4, 9, 9, 5, 9, 8, 2, 5, 9, 9, 5, 1, 2, 0, 8, 4, 8, 6, 7, 9, 6]]
87: LychrelNumber [base=10, digits=[8, 7, 6, 4, 6, 2, 2, 9, 2, 3, 7, 5, 9, 3, 7, 2, 9, 5, 5, 8, 4, 1, 4, 8, 5, 5, 9, 1, 7, 4, 9, 4, 6, 3, 3, 0, 3, 2, 6, 4, 6, 6, 7]]
88: LychrelNumber [base=10, digits=[1, 6, 4, 2, 9, 2, 4, 5, 9, 5, 7, 4, 0, 8, 8, 4, 4, 9, 1, 1, 6, 8, 2, 9, 7, 1, 1, 8, 4, 4, 8, 9, 0, 3, 6, 5, 9, 5, 5, 2, 9, 3, 4, 5]]
38: LychrelNumber [base=10, digits=[7, 0, 3, 3, 6, 7, 9, 7, 2, 0, 2, 0, 2, 8, 0, 8, 5, 2, 4, 0, 6]]
89: LychrelNumber [base=10, digits=[7, 0, 8, 2, 1, 8, 0, 5, 5, 2, 0, 5, 0, 7, 2, 9, 3, 0, 2, 9, 6, 1, 1, 5, 8, 3, 1, 2, 9, 3, 6, 9, 5, 1, 2, 5, 4, 9, 8, 2, 1, 8, 0, 6]]
39: LychrelNumber [base=10, digits=[1, 3, 0, 7, 6, 2, 6, 0, 5, 4, 0, 4, 0, 5, 6, 0, 6, 1, 5, 7, 1, 3]]
90: LychrelNumber [base=10, digits=[1, 3, 1, 6, 3, 4, 7, 0, 0, 0, 4, 2, 1, 0, 3, 6, 8, 5, 1, 6, 8, 1, 2, 3, 2, 7, 5, 1, 6, 8, 6, 4, 0, 0, 1, 5, 1, 0, 0, 6, 3, 4, 6, 1, 3]]
40: LychrelNumber [base=10, digits=[4, 4, 8, 2, 7, 8, 6, 7, 0, 4, 4, 4, 5, 0, 6, 6, 8, 8, 2, 7, 4, 4]]
91: LychrelNumber [base=10, digits=[4, 4, 8, 0, 7, 0, 7, 0, 1, 5, 5, 2, 1, 5, 0, 5, 4, 6, 7, 4, 0, 4, 4, 5, 1, 3, 6, 7, 5, 4, 9, 4, 1, 2, 5, 5, 1, 0, 8, 0, 7, 0, 7, 4, 4]]
41: LychrelNumber [base=10, digits=[8, 9, 5, 5, 6, 7, 3, 3, 0, 9, 8, 8, 9, 1, 4, 3, 7, 5, 5, 5, 8, 8]]
92: LychrelNumber [base=10, digits=[8, 9, 5, 1, 4, 1, 5, 0, 3, 1, 0, 4, 3, 0, 0, 0, 0, 4, 3, 7, 1, 9, 8, 9, 1, 8, 4, 3, 9, 9, 9, 9, 2, 5, 1, 0, 2, 1, 5, 1, 4, 1, 5, 8, 8]]
93: LychrelNumber [base=10, digits=[1, 7, 8, 0, 2, 8, 3, 0, 1, 5, 1, 1, 9, 5, 9, 9, 9, 9, 7, 8, 5, 3, 9, 7, 8, 3, 5, 7, 7, 9, 9, 9, 9, 5, 9, 1, 1, 5, 2, 0, 2, 8, 3, 1, 8, 6]]
94: LychrelNumber [base=10, digits=[8, 5, 9, 4, 1, 0, 3, 2, 6, 6, 3, 1, 5, 5, 9, 9, 9, 7, 5, 3, 9, 2, 7, 7, 1, 9, 4, 5, 7, 9, 9, 9, 5, 5, 0, 2, 6, 6, 2, 4, 1, 0, 4, 0, 5, 7]]

95: LychrelNumber [base=10, digits=[1, 6, 0, 9, 8, 1, 1, 7, 5, 3, 2, 5, 2, 1, 1, 9, 9, 9, 5, 0, 8, 8, 4, 5, 4, 4, 8, 8, 1, 5, 9, 9, 9, 1, 0, 1, 6, 3, 2, 4, 7, 1, 1, 9, 0, 1, 5]]
96: LychrelNumber [base=10, digits=[6, 7, 1, 8, 9, 2, 9, 1, 7, 6, 8, 6, 2, 3, 1, 9, 9, 4, 6, 9, 7, 2, 9, 0, 9, 3, 6, 8, 7, 5, 9, 9, 0, 2, 2, 6, 8, 6, 8, 1, 8, 3, 0, 8, 0, 7, 6]]
97: LychrelNumber [base=10, digits=[1, 3, 4, 2, 6, 9, 6, 7, 3, 6, 3, 7, 2, 4, 5, 2, 9, 9, 0, 4, 8, 3, 6, 8, 1, 8, 6, 4, 8, 4, 0, 9, 8, 1, 5, 5, 3, 7, 3, 5, 3, 7, 6, 0, 6, 2, 5, 2]]
98: LychrelNumber [base=10, digits=[3, 8, 6, 8, 7, 6, 4, 0, 9, 0, 1, 0, 7, 9, 7, 1, 8, 9, 5, 3, 3, 0, 5, 0, 0, 5, 0, 3, 2, 5, 0, 9, 0, 6, 9, 8, 1, 0, 9, 9, 1, 4, 5, 6, 8, 6, 8, 3]]
99: LychrelNumber [base=10, digits=[7, 7, 3, 7, 4, 1, 8, 2, 8, 9, 1, 2, 6, 9, 3, 2, 8, 0, 0, 5, 6, 1, 0, 0, 1, 0, 0, 6, 6, 1, 0, 7, 2, 4, 9, 5, 1, 2, 0, 8, 1, 9, 2, 4, 7, 3, 6, 6]]
100: LychrelNumber [base=10, digits=[1, 4, 3, 7, 4, 8, 4, 7, 4, 6, 9, 3, 4, 2, 8, 7, 5, 5, 0, 2, 2, 2, 1, 0, 1, 1, 0, 2, 3, 1, 1, 1, 5, 4, 8, 9, 1, 3, 4, 0, 6, 4, 7, 3, 9, 4, 7, 4, 3]]

1: LychrelNumber [base=9, digits=[8, 8, 8, 8]]
2: LychrelNumber [base=9, digits=[1, 8, 8, 8, 7]]
3: LychrelNumber [base=9, digits=[1, 0, 8, 8, 7, 8]]
4: LychrelNumber [base=9, digits=[1, 0, 8, 8, 7, 8, 0]]
5: LychrelNumber [base=9, digits=[2, 0, 7, 8, 6, 8, 1]]
6: LychrelNumber [base=9, digits=[4, 0, 5, 8, 4, 8, 3]]
7: LychrelNumber [base=9, digits=[8, 0, 1, 8, 0, 8, 7]]
8: LychrelNumber [base=9, digits=[1, 6, 8, 2, 7, 2, 0, 6]]
9: LychrelNumber [base=9, digits=[7, 7, 2, 1, 1, 1, 6, 7]]
10: LychrelNumber [base=9, digits=[1, 6, 4, 3, 2, 2, 4, 5, 5]]
11: LychrelNumber [base=9, digits=[7, 2, 8, 5, 4, 6, 0, 2, 6]]
12: LychrelNumber [base=9, digits=[1, 4, 5, 0, 3, 0, 2, 8, 5, 4]]
13: LychrelNumber [base=9, digits=[6, 1, 4, 2, 3, 3, 3, 5, 0, 5]]
14: LychrelNumber [base=9, digits=[1, 2, 2, 0, 5, 6, 6, 6, 0, 2, 2]]
15: LychrelNumber [base=9, digits=[3, 4, 2, 7, 3, 4, 2, 6, 2, 4, 3]]
16: LychrelNumber [base=9, digits=[6, 8, 5, 4, 5, 8, 6, 4, 4, 8, 6]]
17: LychrelNumber [base=9, digits=[1, 4, 8, 1, 0, 3, 8, 3, 0, 1, 8, 3]]
18: LychrelNumber [base=9, digits=[5, 4, 0, 1, 4, 3, 2, 3, 2, 1, 3, 4]]
19: LychrelNumber [base=9, digits=[1, 0, 7, 1, 3, 7, 5, 5, 7, 3, 1, 8, 0]]
20: LychrelNumber [base=9, digits=[1, 8, 8, 5, 2, 4, 2, 4, 1, 4, 8, 8, 1]]
21: LychrelNumber [base=9, digits=[3, 8, 8, 0, 3, 8, 4, 8, 4, 1, 8, 7, 2]]
22: LychrelNumber [base=9, digits=[6, 7, 7, 1, 8, 8, 0, 7, 7, 2, 8, 6, 5]]
23: LychrelNumber [base=9, digits=[1, 3, 5, 6, 4, 7, 6, 1, 7, 6, 4, 7, 5, 2]]
24: LychrelNumber [base=9, digits=[4, 0, 4, 2, 2, 5, 7, 8, 6, 2, 2, 3, 8, 3]]
25: LychrelNumber [base=9, digits=[7, 8, 7, 4, 5, 3, 7, 7, 2, 4, 4, 7, 8, 7]]
26: LychrelNumber [base=9, digits=[1, 6, 8, 6, 0, 0, 6, 6, 5, 6, 1, 0, 6, 8, 5]]
27: LychrelNumber [base=9, digits=[7, 6, 5, 6, 1, 7, 3, 4, 2, 6, 1, 7, 6, 5, 6]]
28: LychrelNumber [base=9, digits=[1, 5, 3, 3, 4, 3, 4, 5, 8, 6, 4, 3, 5, 3, 3, 4]]
29: LychrelNumber [base=9, digits=[5, 8, 6, 8, 7, 8, 2, 5, 5, 1, 7, 7, 8, 6, 8, 5]]
30: LychrelNumber [base=9, digits=[1, 2, 8, 4, 8, 6, 6, 4, 2, 1, 4, 7, 6, 8, 4, 8, 1]]
31: LychrelNumber [base=9, digits=[3, 2, 4, 4, 6, 5, 1, 5, 4, 6, 2, 5, 6, 4, 4, 1, 2]]
32: LychrelNumber [base=9, digits=[5, 4, 0, 0, 4, 1, 4, 3, 0, 2, 4, 2, 3, 8, 8, 3, 5]]
33: LychrelNumber [base=9, digits=[1, 1, 7, 8, 8, 7, 3, 8, 5, 0, 5, 8, 3, 7, 8, 8, 8, 1]]
34: LychrelNumber [base=9, digits=[3, 1, 7, 8, 7, 2, 3, 4, 5, 6, 5, 3, 2, 7, 8, 7, 0, 2]]
35: LychrelNumber [base=9, digits=[5, 2, 6, 8, 5, 4, 7, 1, 3, 3, 0, 6, 5, 6, 8, 5, 1, 5]]
36: LychrelNumber [base=9, digits=[1, 1, 4, 3, 8, 3, 1, 4, 1, 6, 6, 2, 5, 1, 3, 8, 2, 4, 1]]
37: LychrelNumber [base=9, digits=[2, 5, 7, 3, 2, 4, 6, 6, 8, 3, 7, 6, 6, 5, 3, 2, 6, 5, 2]]
38: LychrelNumber [base=9, digits=[5, 2, 4, 5, 6, 1, 4, 4, 6, 7, 7, 4, 4, 0, 5, 6, 5, 1, 4]]
39: LychrelNumber [base=9, digits=[1, 0, 4, 1, 3, 2, 2, 0, 0, 5, 6, 4, 8, 8, 2, 3, 3, 0, 4, 0]]
40: LychrelNumber [base=9, digits=[1, 4, 4, 4, 6, 5, 1, 8, 5, 3, 2, 5, 0, 1, 4, 6, 4, 4, 4, 1]]
41: LychrelNumber [base=9, digits=[3, 0, 0, 0, 4, 0, 3, 0, 1, 5, 6, 1, 8, 3, 1, 3, 8, 8, 8, 2]]
42: LychrelNumber [base=9, digits=[5, 8, 8, 8, 7, 1, 6, 8, 3, 3, 2, 2, 8, 6, 1, 7, 8, 8, 8, 5]]
43: LychrelNumber [base=9, digits=[1, 2, 8, 8, 8, 5, 3, 4, 7, 5, 5, 5, 6, 8, 3, 3, 6, 8, 8, 8, 1]]
44: LychrelNumber [base=9, digits=[3, 2, 8, 8, 5, 8, 7, 4, 5, 2, 2, 2, 5, 3, 7, 0, 6, 8, 8, 1, 2]]
45: LychrelNumber [base=9, digits=[5, 4, 8, 8, 3, 0, 5, 8, 1, 4, 4, 5, 1, 8, 6, 0, 3, 8, 7, 3, 5]]
46: LychrelNumber [base=9, digits=[1, 1, 8, 7, 7, 6, 1, 3, 7, 3, 1, 0, 0, 3, 8, 2, 0, 7, 8, 6, 8, 1]]
47: LychrelNumber [base=9, digits=[3, 1, 6, 7, 5, 6, 4, 3, 1, 3, 1, 1, 4, 2, 2, 3, 7, 6, 7, 6, 0, 2]]
48: LychrelNumber [base=9, digits=[5, 2, 4, 6, 3, 4, 7, 5, 3, 7, 2, 2, 7, 3, 5, 8, 5, 3, 6, 3, 1, 5]]
49: LychrelNumber [base=9, digits=[1, 1, 3, 8, 3, 7, 1, 7, 1, 7, 5, 4, 5, 5, 7, 2, 7, 0, 7, 3, 7, 4, 1]]
50: LychrelNumber [base=9, digits=[2, 6, 2, 3, 1, 8, 0, 1, 0, 4, 2, 0, 2, 4, 0, 0, 8, 8, 2, 3, 1, 5, 2]]
51: LychrelNumber [base=9, digits=[5, 2, 3, 6, 4, 7, 8, 1, 0, 8, 4, 0, 4, 8, 0, 2, 0, 7, 3, 6, 4, 2, 4]]
52: LychrelNumber [base=9, digits=[1, 0, 4, 8, 3, 8, 5, 8, 3, 1, 7, 8, 1, 0, 7, 0, 4, 0, 5, 8, 3, 7, 5, 0]]
53: LychrelNumber [base=9, digits=[1, 6, 3, 3, 3, 4, 6, 3, 3, 8, 8, 1, 0, 7, 8, 4, 3, 6, 5, 3, 3, 2, 5, 1]]
54: LychrelNumber [base=9, digits=[3, 2, 5, 6, 7, 1, 3, 6, 8, 8, 6, 1, 2, 7, 7, 7, 7, 4, 0, 6, 6, 6, 2, 2]]
55: LychrelNumber [base=9, digits=[5, 5, 3, 4, 4, 1, 8, 5, 7, 7, 4, 3, 4, 5, 7, 7, 4, 7, 2, 5, 4, 2, 4, 5]]
56: LychrelNumber [base=9, digits=[1, 2, 0, 6, 0, 0, 4, 7, 1, 6, 6, 0, 7, 8, 1, 6, 6, 1, 6, 4, 0, 8, 6, 1, 1]]
57: LychrelNumber [base=9, digits=[2, 3, 7, 5, 0, 5, 1, 8, 8, 3, 8, 0, 5, 8, 8, 3, 8, 0, 1, 4, 1, 5, 6, 3, 2]]
58: LychrelNumber [base=9, digits=[4, 7, 5, 1, 2, 0, 3, 0, 7, 7, 8, 0, 2, 0, 7, 7, 7, 8, 3, 0, 2, 2, 4, 6, 4]]
59: LychrelNumber [base=9, digits=[1, 0, 5, 0, 3, 4, 0, 7, 0, 6, 6, 6, 0, 4, 1, 7, 6, 5, 8, 6, 0, 4, 4, 1, 4, 8]]
60: LychrelNumber [base=9, digits=[1, 0, 4, 6, 4, 7, 4, 7, 6, 6, 4, 4, 7, 4, 4, 8, 5, 3, 6, 6, 6, 4, 7, 4, 6, 5, 0]]
61: LychrelNumber [base=9, digits=[1, 6, 2, 2, 3, 3, 2, 5, 4, 1, 1, 4, 3, 0, 3, 4, 1, 1, 4, 5, 2, 3, 3, 2, 1, 5, 1]]
62: LychrelNumber [base=9, digits=[3, 2, 3, 4, 6, 6, 5, 1, 8, 2, 2, 8, 6, 0, 6, 8, 2, 3, 0, 1, 4, 6, 6, 4, 4, 2, 2]]
63: LychrelNumber [base=9, digits=[5, 4, 8, 0, 4, 4, 0, 2, 8, 5, 5, 8, 3, 1, 4, 7, 4, 5, 8, 3, 1, 4, 3, 8, 7, 4, 5]]
64: LychrelNumber [base=9, digits=[1, 2, 0, 6, 8, 7, 8, 1, 6, 8, 2, 1, 6, 7, 2, 8, 7, 1, 2, 7, 5, 1, 8, 8, 0, 7, 0, 1]]
65: LychrelNumber [base=9, digits=[2, 2, 7, 7, 8, 7, 0, 7, 5, 1, 4, 0, 6, 1, 1, 5, 8, 4, 2, 4, 7, 1, 7, 7, 6, 7, 2, 2]]
66: LychrelNumber [base=9, digits=[4, 5, 6, 5, 7, 5, 2, 6, 0, 4, 0, 0, 2, 2, 3, 2, 8, 8, 4, 1, 5, 2, 6, 7, 5, 5, 4, 4]]
67: LychrelNumber [base=9, digits=[1, 0, 1, 3, 2, 6, 2, 5, 2, 1, 8, 8, 8, 4, 5, 5, 4, 8, 8, 8, 2, 2, 5, 3, 6, 2, 3, 0, 8]]
68: LychrelNumber [base=9, digits=[1, 0, 0, 4, 6, 0, 0, 7, 7, 5, 1, 8, 8, 4, 1, 2, 1, 4, 8, 8, 0, 4, 7, 8, 0, 8, 5, 4, 1, 0]]
69: LychrelNumber [base=9, digits=[1, 1, 5, 1, 5, 1, 0, 6, 2, 6, 1, 8, 3, 5, 3, 3, 6, 4, 8, 0, 6, 3, 5, 8, 1, 6, 0, 4, 1, 1]]
70: LychrelNumber [base=9, digits=[2, 3, 0, 2, 2, 3, 0, 2, 6, 3, 2, 7, 8, 2, 6, 7, 2, 8, 7, 2, 3, 6, 2, 8, 3, 2, 2, 0, 2, 2]]
71: LychrelNumber [base=9, digits=[4, 5, 0, 4, 4, 6, 8, 5, 3, 6, 5, 6, 7, 5, 5, 4, 5, 8, 5, 4, 7, 3, 4, 8, 6, 4, 4, 0, 5, 4]]
72: LychrelNumber [base=9, digits=[1, 0, 1, 1, 0, 0, 4, 8, 0, 7, 5, 1, 3, 7, 2, 1, 1, 2, 7, 3, 1, 4, 7, 1, 8, 3, 8, 8, 1, 1, 8]]
73: LychrelNumber [base=9, digits=[1, 0, 1, 3, 0, 8, 4, 4, 0, 8, 2, 6, 5, 2, 0, 3, 2, 4, 1, 1, 4, 7, 2, 8, 1, 3, 4, 0, 0, 2, 2, 0]]
74: LychrelNumber [base=9, digits=[1, 2, 3, 3, 1, 3, 7, 6, 0, 2, 1, 1, 6, 3, 4, 5, 5, 4, 3, 7, 2, 1, 1, 8, 5, 8, 3, 0, 3, 3, 2, 1]]
75: LychrelNumber [base=9, digits=[2, 4, 6, 6, 1, 7, 7, 2, 8, 3, 2, 4, 4, 7, 0, 2, 1, 8, 7, 4, 3, 2, 4, 0, 3, 6, 6, 1, 6, 6, 4, 2]]
76: LychrelNumber [base=9, digits=[5, 0, 4, 3, 3, 5, 4, 5, 8, 7, 4, 8, 0, 5, 8, 3, 4, 0, 5, 8, 7, 4, 7, 8, 6, 5, 4, 3, 4, 3, 8, 4]]
77: LychrelNumber [base=9, digits=[1, 0, 8, 7, 7, 7, 1, 1, 3, 8, 6, 0, 7, 0, 1, 8, 7, 8, 0, 2, 0, 7, 0, 6, 8, 3, 1, 0, 6, 7, 8, 0, 0]]
78: LychrelNumber [base=9, digits=[1, 1, 8, 6, 4, 7, 2, 5, 3, 5, 6, 7, 7, 2, 2, 8, 6, 7, 1, 2, 7, 7, 7, 6, 2, 4, 2, 8, 5, 6, 7, 0, 1]]
79: LychrelNumber [base=9, digits=[2, 2, 7, 4, 1, 6, 5, 0, 6, 3, 5, 6, 5, 4, 4, 7, 4, 6, 3, 5, 6, 6, 5, 2, 6, 0, 5, 7, 1, 4, 6, 1, 2]]
80: LychrelNumber [base=9, digits=[4, 4, 4, 8, 3, 5, 1, 1, 3, 6, 2, 4, 3, 0, 8, 5, 0, 4, 8, 1, 3, 4, 1, 6, 3, 1, 2, 4, 3, 0, 4, 3, 4]]
81: LychrelNumber [base=9, digits=[8, 7, 8, 8, 7, 0, 3, 2, 7, 3, 3, 8, 6, 2, 8, 0, 1, 1, 7, 1, 6, 8, 4, 3, 6, 2, 4, 0, 6, 8, 8, 7, 8]]
82: LychrelNumber [base=9, digits=[1, 8, 6, 8, 8, 4, 0, 7, 5, 4, 6, 8, 8, 3, 4, 6, 1, 2, 2, 6, 4, 4, 7, 7, 7, 4, 4, 7, 1, 5, 8, 8, 6, 7]]
83: LychrelNumber [base=9, digits=[1, 0, 6, 6, 8, 4, 5, 8, 3, 1, 3, 5, 7, 3, 8, 1, 8, 3, 4, 0, 1, 8, 4, 7, 5, 3, 1, 2, 7, 6, 5, 8, 6, 5, 8]]
84: LychrelNumber [base=9, digits=[1, 0, 6, 4, 6, 5, 2, 4, 1, 4, 5, 0, 4, 3, 3, 0, 2, 3, 7, 3, 2, 1, 3, 3, 3, 8, 4, 5, 2, 4, 2, 5, 6, 3, 6, 0]]
85: LychrelNumber [base=9, digits=[1, 7, 1, 2, 2, 7, 6, 6, 7, 0, 4, 3, 7, 6, 4, 2, 6, 2, 1, 5, 2, 4, 6, 7, 4, 4, 8, 6, 6, 6, 8, 3, 2, 0, 6, 1]]
86: LychrelNumber [base=9, digits=[3, 4, 1, 4, 6, 7, 4, 4, 4, 8, 8, 8, 6, 3, 8, 5, 2, 3, 4, 2, 5, 0, 4, 5, 8, 0, 0, 5, 4, 4, 6, 5, 4, 2, 4, 2]]
87: LychrelNumber [base=9, digits=[5, 8, 4, 0, 3, 5, 0, 0, 1, 0, 0, 8, 2, 8, 0, 1, 4, 7, 7, 5, 1, 8, 8, 3, 8, 0, 0, 1, 0, 0, 5, 2, 8, 3, 8, 5]]
88: LychrelNumber [base=9, digits=[1, 2, 7, 7, 8, 6, 1, 0, 0, 2, 0, 1, 7, 6, 7, 8, 3, 1, 6, 6, 0, 3, 0, 7, 6, 7, 0, 0, 2, 0, 1, 1, 5, 8, 8, 8, 1]]
89: LychrelNumber [base=9, digits=[3, 2, 7, 7, 4, 7, 2, 0, 2, 2, 1, 0, 5, 4, 8, 2, 3, 8, 3, 7, 4, 2, 8, 5, 4, 8, 0, 2, 2, 0, 2, 8, 5, 7, 7, 1, 2]]
90: LychrelNumber [base=9, digits=[5, 4, 6, 6, 1, 6, 4, 0, 4, 4, 2, 0, 1, 1, 7, 4, 8, 6, 7, 6, 7, 5, 8, 1, 0, 8, 1, 4, 4, 0, 5, 7, 1, 6, 5, 3, 5]]
91: LychrelNumber [base=9, digits=[1, 1, 8, 3, 3, 3, 5, 0, 0, 8, 8, 3, 8, 1, 3, 7, 1, 7, 4, 6, 4, 7, 1, 6, 2, 1, 8, 3, 8, 8, 1, 1, 4, 3, 4, 2, 8, 1]]
92: LychrelNumber [base=9, digits=[3, 1, 1, 7, 6, 7, 6, 2, 0, 8, 3, 3, 0, 4, 1, 0, 0, 3, 2, 2, 3, 0, 0, 0, 4, 1, 3, 3, 7, 8, 1, 6, 7, 6, 8, 2, 0, 2]]
93: LychrelNumber [base=9, digits=[5, 1, 4, 7, 4, 6, 3, 4, 0, 6, 6, 6, 1, 8, 1, 0, 0, 6, 4, 4, 6, 0, 0, 1, 8, 1, 6, 7, 6, 8, 4, 4, 6, 4, 6, 3, 1, 5]]
94: LychrelNumber [base=9, digits=[1, 1, 2, 8, 5, 0, 3, 8, 0, 0, 4, 5, 3, 3, 7, 2, 0, 1, 4, 0, 0, 3, 0, 0, 3, 7, 3, 4, 5, 3, 8, 8, 8, 4, 0, 4, 7, 3, 1]]
95: LychrelNumber [base=9, digits=[2, 5, 1, 3, 5, 5, 3, 7, 8, 4, 1, 0, 7, 2, 1, 2, 0, 4, 4, 0, 4, 4, 0, 3, 2, 1, 7, 1, 0, 4, 0, 8, 2, 4, 6, 4, 0, 4, 2]]
96: LychrelNumber [base=9, digits=[5, 0, 1, 8, 3, 0, 6, 6, 8, 8, 1, 2, 5, 3, 3, 5, 0, 8, 8, 0, 8, 8, 0, 5, 3, 4, 5, 1, 2, 0, 0, 6, 6, 1, 2, 7, 2, 0, 4]]
97: LychrelNumber [base=9, digits=[1, 0, 0, 4, 6, 5, 2, 4, 3, 8, 8, 3, 4, 1, 7, 7, 1, 1, 8, 7, 1, 8, 7, 1, 1, 6, 8, 1, 3, 4, 0, 0, 4, 3, 1, 6, 6, 3, 1, 0]]
98: LychrelNumber [base=9, digits=[1, 1, 4, 2, 3, 6, 5, 8, 4, 0, 3, 6, 6, 1, 4, 8, 3, 0, 8, 0, 0, 7, 8, 3, 0, 5, 0, 5, 7, 3, 8, 3, 8, 5, 7, 4, 1, 3, 1, 1]]
99: LychrelNumber [base=9, digits=[2, 2, 7, 3, 8, 5, 2, 7, 7, 8, 7, 5, 2, 2, 0, 8, 7, 0, 6, 0, 1, 6, 8, 7, 0, 0, 2, 3, 4, 6, 8, 8, 8, 2, 4, 7, 3, 7, 2, 2]]
100: LychrelNumber [base=9, digits=[4, 5, 5, 7, 7, 0, 5, 7, 7, 8, 5, 0, 5, 4, 1, 0, 6, 0, 3, 1, 2, 4, 0, 5, 8, 0, 4, 6, 1, 5, 8, 7, 6, 5, 1, 6, 7, 5, 4, 4]]


1: LychrelNumber [base=4, digits=[3, 3, 3, 3]]
2: LychrelNumber [base=4, digits=[1, 3, 3, 3, 2]]
3: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3]]
4: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3, 0]]
5: LychrelNumber [base=4, digits=[2, 0, 2, 3, 1, 3, 1]]
6: LychrelNumber [base=4, digits=[1, 0, 0, 0, 2, 3, 3, 3]]
*7: LychrelNumber [base=4, digits=[1, 0, 3, 3, 2, 3, 0, 0, 0]]
8: LychrelNumber [base=4, digits=[1, 1, 0, 3, 1, 2, 3, 0, 1]]
9: LychrelNumber [base=4, digits=[2, 2, 0, 1, 3, 1, 3, 1, 2]]
10: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 2, 3, 0, 0, 0]]
11: LychrelNumber [base=4, digits=[1, 1, 0, 3, 2, 2, 2, 3, 0, 1]]
12: LychrelNumber [base=4, digits=[2, 2, 0, 2, 1, 1, 1, 3, 1, 2]]
13: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 2, 3, 0, 0, 0, 0]]
14: LychrelNumber [base=4, digits=[1, 1, 0, 0, 3, 1, 2, 3, 3, 0, 1]]
15: LychrelNumber [base=4, digits=[2, 2, 0, 0, 1, 3, 1, 3, 3, 1, 2]]
16: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0]]
17: LychrelNumber [base=4, digits=[1, 1, 0, 0, 3, 2, 2, 2, 3, 3, 0, 1]]
18: LychrelNumber [base=4, digits=[2, 2, 0, 0, 2, 1, 1, 1, 3, 3, 1, 2]]
19: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0]]
20: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 3, 1, 2, 3, 3, 3, 0, 1]]
21: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 1, 3, 1, 3, 3, 3, 1, 2]]
22: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0]]
23: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 0, 1]]
24: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 1, 2]]
25: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0]]
26: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 0, 1]]
27: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 1, 2]]
28: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0]]
29: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 0, 1]]
30: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 1, 2]]
31: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0]]
32: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 0, 1]]
33: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 1, 2]]
34: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0]]
35: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 0, 1]]
36: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 1, 2]]
37: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0]]
38: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 0, 1]]
39: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 1, 2]]
40: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0]]
41: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 0, 1]]
42: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1, 2]]
43: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
44: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
45: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
46: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
47: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
48: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
49: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
50: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
51: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
52: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
53: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
54: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
55: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
56: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
57: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
58: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
59: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
60: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
61: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
62: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
63: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
64: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
65: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
66: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
67: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
68: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
69: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
70: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
71: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
72: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
73: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
74: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
75: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
76: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
77: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
78: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
79: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
80: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
81: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
82: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
83: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
84: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
85: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
86: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
87: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
88: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
89: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
90: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
91: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
92: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
93: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
94: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
95: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
96: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
97: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
98: LychrelNumber [base=4, digits=[1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1]]
99: LychrelNumber [base=4, digits=[2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2]]
100: LychrelNumber [base=4, digits=[1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]

1: LychrelNumber [base=5, digits=[4, 4, 4, 4]]
2: LychrelNumber [base=5, digits=[1, 4, 4, 4, 3]]
3: LychrelNumber [base=5, digits=[1, 0, 4, 4, 3, 4]]
4: LychrelNumber [base=5, digits=[1, 0, 4, 4, 3, 4, 0]]
5: LychrelNumber [base=5, digits=[2, 0, 3, 4, 2, 4, 1]]
6: LychrelNumber [base=5, digits=[4, 0, 1, 4, 0, 4, 3]]
7: LychrelNumber [base=5, digits=[1, 2, 4, 2, 3, 2, 0, 2]]
8: LychrelNumber [base=5, digits=[3, 3, 2, 1, 1, 1, 2, 3]]
9: LychrelNumber [base=5, digits=[1, 2, 0, 3, 2, 2, 4, 1, 1]]
10: LychrelNumber [base=5, digits=[2, 4, 0, 1, 0, 0, 4, 3, 2]]
11: LychrelNumber [base=5, digits=[1, 0, 2, 4, 1, 0, 2, 0, 2, 4]]
12: LychrelNumber [base=5, digits=[1, 0, 2, 3, 1, 1, 2, 1, 2, 3, 0]]
13: LychrelNumber [base=5, digits=[1, 3, 4, 4, 3, 2, 3, 4, 4, 3, 1]]

17: LychrelNumber [base=8, digits=[1, 1, 0, 0, 7, 5, 6, 7, 7, 0, 1]]
18: LychrelNumber [base=8, digits=[2, 2, 0, 0, 6, 3, 5, 7, 7, 1, 2]]
19: LychrelNumber [base=8, digits=[4, 4, 0, 0, 3, 7, 3, 7, 7, 3, 4]]
20: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0]]
21: LychrelNumber [base=8, digits=[1, 1, 0, 0, 7, 6, 6, 6, 7, 7, 0, 1]]
22: LychrelNumber [base=8, digits=[2, 2, 0, 0, 6, 5, 5, 5, 7, 7, 1, 2]]
23: LychrelNumber [base=8, digits=[4, 4, 0, 0, 4, 3, 3, 3, 7, 7, 3, 4]]
24: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0]]
25: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 7, 5, 6, 7, 7, 7, 0, 1]]
26: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 6, 3, 5, 7, 7, 7, 1, 2]]
27: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 3, 7, 3, 7, 7, 7, 3, 4]]
28: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0]]
29: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 7, 6, 6, 6, 7, 7, 7, 0, 1]]
30: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 6, 5, 5, 5, 7, 7, 7, 1, 2]]
31: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 4, 3, 3, 3, 7, 7, 7, 3, 4]]
32: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0, 0]]
33: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 0, 7, 5, 6, 7, 7, 7, 7, 0, 1]]
34: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 0, 6, 3, 5, 7, 7, 7, 7, 1, 2]]
35: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 0, 3, 7, 3, 7, 7, 7, 7, 3, 4]]
36: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0, 0]]
37: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 0, 7, 6, 6, 6, 7, 7, 7, 7, 0, 1]]
38: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 0, 6, 5, 5, 5, 7, 7, 7, 7, 1, 2]]
39: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 0, 4, 3, 3, 3, 7, 7, 7, 7, 3, 4]]
40: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0, 0, 0]]
41: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 0, 0, 7, 5, 6, 7, 7, 7, 7, 7, 0, 1]]
42: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 0, 0, 6, 3, 5, 7, 7, 7, 7, 7, 1, 2]]
43: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 0, 0, 3, 7, 3, 7, 7, 7, 7, 7, 3, 4]]
44: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0, 0, 0]]
45: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 7, 7, 7, 7, 7, 0, 1]]
46: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 0, 0, 6, 5, 5, 5, 7, 7, 7, 7, 7, 1, 2]]
47: LychrelNumber [base=8, digits=[4, 4, 0, 0, 0, 0, 0, 4, 3, 3, 3, 7, 7, 7, 7, 7, 3, 4]]
48: LychrelNumber [base=8, digits=[1, 0, 7, 7, 7, 7, 7, 7, 7, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0]]
49: LychrelNumber [base=8, digits=[1, 1, 0, 0, 0, 0, 0, 0, 7, 5, 6, 7, 7, 7, 7, 7, 7, 0, 1]]
50: LychrelNumber [base=8, digits=[2, 2, 0, 0, 0, 0, 0, 0, 6, 3, 5, 7, 7, 7, 7, 7, 7, 1, 2]]

1: n-1, n-1, n-1, n-1
2: 1, n-1, n-1, n-1, n-2
3: 1, 0, n-1, n-1, n-2, n-1
4: 1, 0, n-1, n-1, n-2, n-1, 0
5: 2, 0, n-2, n-1, n-3, n-1, 1

	*/
	
}
