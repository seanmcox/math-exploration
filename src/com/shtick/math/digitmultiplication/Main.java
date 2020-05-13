package com.shtick.math.digitmultiplication;

import java.util.HashMap;

import com.shtick.math.BigInt;

/**
 * See: "What's special about 277777788888899? - Numberphile", https://www.youtube.com/watch?v=Wim9WJeDTHQ
 * 
 * @author scox
 *
 */
public class Main {
	private static final int[] SUB_10_PRIMES = new int[] {2,3,7};
	private static final long[] SMALLEST_KNOWN_NSTEP = new long[] {0,10,25,39,77,679,6788,68889,2677889,26888999,3778888999L,277777788888899L};
	
	private static HashMap<BigInt,StepTracer> stepTracers = new HashMap<>();
	
	/**
	 * Code entry point for digit multiplication investigation code.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Handle huge numbers
		System.out.println(multiplyDigits(new BigInt(10,277777788888899L)));
		int depth = 500;
		BigInt[][] multiples = new BigInt[SUB_10_PRIMES.length][];
		for(int i=0;i<multiples.length;i++) {
			multiples[i] = new BigInt[depth];
			multiples[i][0] = new BigInt(10,1);
			for(int j=1;j<depth;j++)
				multiples[i][j] = multiples[i][j-1].multipliedBy(SUB_10_PRIMES[i]);
			System.out.println("m: "+multiples[i][depth-1]);
		}
		for(int i=1;i<depth*depth*depth;i++) {
			BigInt n = new BigInt(10,1);
			int t = i;
			int[] primes = new int[multiples.length];
			for(int j=0;j<multiples.length;j++) {
				primes[j] = t%depth;
				n=n.multipliedBy(multiples[j][t%depth]);
				t/=depth;
			}
			if(n.getDigitCount()<2) { // Already done. Zero steps.
				continue;
			}
			String sn = n.toSimpleString();
			if(sn.indexOf('0')>=0) { // Number contains zero and halt in a single step.
				continue;
			}
			if((sn.indexOf('5')>=0)&&((sn.indexOf('2')>=0)||(sn.indexOf('4')>=0)||(sn.indexOf('6')>=0)||(sn.indexOf('8')>=0))){ // Subsequent number will be multiple of 10. Will halt in 2 steps.
				continue;
			}
			stepTracers.put(n, new StepTracer(n));
		}
		BigInt[] keys = stepTracers.keySet().toArray(new BigInt[stepTracers.size()]);
		for(BigInt n:keys) {
			int steps = stepTracers.get(n).getSteps();
			if(steps<0)
				System.out.println(""+n+" - "+steps+" - "+multiplyDigits(stepTracers.get(n).n));
			else if(steps>7)
				System.out.println(""+n+" - "+steps);
		}
	}
	
	private static BigInt multiplyDigits(BigInt l) {
		BigInt retval = new BigInt(l.getBase(),1);
		for(int digit:l.getDigits())
			retval=retval.multipliedBy(digit);
		return retval;
	}
	
	/*
	 * Definitions:
	 * d(n) is the digit multiplication of n.
	 * 
	 * The prime factorization of any d(n) is made up of only primes less than the base of the number.
	 * 
	 * ------------------------------------------
	 * Theorem: 5^n = [0-9]*25 for n>=2
	 * Hence, for any 5^n for n>=2, the digit multiplication process will terminate within 2 steps.
	 * 
	 * Proof:
	 * 5^0=1
	 * 5^1=5
	 * 5^2=25
	 * 5^3=125
	 * 
	 * For x = a, b, ... , c, 2, 5
	 * => x*5 = 5a/10, 5a%10+5b/10,...,5c%10+1,2,5 = A, B, ... , C, 2, 5
	 * 
	 * 
	 */
	
	private static class StepTracer {
		private int steps = -1;
		public BigInt n;
		
		public StepTracer(BigInt n) {
			this.n = n;
		}
		
		public StepTracer(BigInt n, int steps) {
			this.steps = steps;
			this.n = n;
		}
		
		public int getSteps() {
			if(steps<0) {
				BigInt dn = multiplyDigits(n);
				if(dn.getDigitCount()<2) {
					steps = 1;
					return steps;
				}
				StepTracer nextStep = stepTracers.get(dn);
				if(nextStep==null) {
					String sn = ""+dn.toSimpleString();
					if(sn.indexOf('0')>=0) // Number contains zero and halt in a single step.
						steps=2;
					else if((sn.indexOf('5')>=0)&&((sn.indexOf('2')>=0)||(sn.indexOf('4')>=0)||(sn.indexOf('6')>=0)||(sn.indexOf('8')>=0))) // Subsequent number will be multiple of 10. Will halt in 2 steps.
						steps=3;
					else{
						nextStep = new StepTracer(dn);
						stepTracers.put(dn, nextStep);
					}
				}
				if(nextStep!=null) {
					steps = nextStep.getSteps();
					if(steps>=0)
						steps++;
				}
			}
			return steps;
		}
	}
}
