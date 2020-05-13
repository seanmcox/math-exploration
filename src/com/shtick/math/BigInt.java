/**
 * 
 */
package com.shtick.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author scox
 *
 */
public class BigInt {
	int base;
	/**
	 * Digits in order from most significant to least significant.
	 */
	protected int[] digits;

	/**
	 * 
	 * @param base
	 * @param number A non-empty numeric string appropriate to the base.
	 * @throws NumberFormatException
	 */
	public BigInt(int base, String number){
		if(number.length()==0)
			throw new IllegalArgumentException("Empty number not acceptable.");
		this.base=base;
		if(number.equals("0")) {
			digits = new int[0];
		}
		else {
			digits = new int[number.length()];
			try {
				for(int i=0;i<digits.length;i++) {
					digits[i] = Integer.parseInt(""+number.charAt(i),base);
				}
			}
			catch(NumberFormatException t) {
				throw new IllegalArgumentException(t);
			}
			if(digits[0]==0)
				throw new IllegalArgumentException("Number cannot start with a 0.");
		}
	}

	/**
	 * 
	 * @param base Must be greater than 1.
	 * @param value Must be greater than 0.
	 * @throws IllegalArgumentException
	 */
	public BigInt(int base, long value){
		if(base<2)
			throw new IllegalArgumentException("Invalid base: "+base);
		if(value<0)
			throw new IllegalArgumentException("Invalid value: "+value);
		int digitCount = 0;
		for(long v2=value;v2>0;v2/=base)
			digitCount++;
		int[] digits = new int[digitCount];
		for(int i=digitCount-1;i>=0;i--) {
			digits[i] = (int)(value%base);
			value /= base;
		}
		this.base = base;
		this.digits = digits;
	}

	/**
	 * 
	 * @param base Must be greater than 1.
	 * @param digits A non-empty array of digits in the range of 0 to base-1 inclusive. The digit at index zero is the most significant digit and must not be zero.
	 *               A number with a value of zero can be represented with an empty array.
	 * @throws IllegalArgumentException
	 */
	public BigInt(int base, int[] digits) {
		if(base<2)
			throw new IllegalArgumentException("Invalid base: "+base);
		if((digits.length>0)&&(digits[0]==0))
			throw new IllegalArgumentException("Digits begin with 0.");
		for(int digit:digits)
			if((digit>=base)||(digit<0))
				throw new IllegalArgumentException("Digit ("+digit+") innappropriate for base ("+base+").");
		this.base = base;
		this.digits = digits;
	}
	
	/**
	 * @return the base
	 */
	public int getBase() {
		return base;
	}
	
	/**
	 * 
	 * @return The number of digits in the number.
	 */
	public int getDigitCount() {
		return digits.length;
	}
	
	/**
	 * 
	 * @return A copy of the digits.
	 */
	public int[] getDigits() {
		return Arrays.copyOf(digits, digits.length);
	}
	
	/**
	 * 
	 * @return The sum of the digits.
	 */
	public long getDigitSum() {
		long retval = 0;
		for(int digit:digits)
			retval+=digit;
		return retval;
	}

	/**
	 * 
	 * @return The sum of the digits.
	 */
	public long getDigitalRoot() {
		long current = getDigitSum();
		while(current>=base) {
			long newSum = 0;
			for(long c = current;c>0;c/=base) {
				newSum += c%base;
			}
			current = newSum;
		}
		return current;
	}
	
	/**
	 * 
	 * @return A map of prime numbers to the number of time the prime number factors into this LychrelNumber.
	 */
	public Map<Long,Integer> getPrimefactors() {
		HashMap<Long,Integer> retval = new HashMap<>();
		BigInt workingCopy = this;
		long p = 2L;
		if((base%2)==0) {
			while(workingCopy.digits[workingCopy.digits.length-1]%2 == 0) {
				DivisionResult div = workingCopy.dividedBy(2);
				workingCopy = div.getNumber();
				if(retval.containsKey(2L)) {
					retval.put(2L, retval.get(2L)+1);
				}
				else {
					retval.put(2L, 1);
				}
			}
		}
		else {
			workingCopy = recordFactors(workingCopy,p,retval);
		}
		p = 3L;
		if((base==10)||(base==4)) {
			while(workingCopy.getDigitSum()%3 == 0) {
				DivisionResult div = workingCopy.dividedBy(3);
				workingCopy = div.getNumber();
				if(retval.containsKey(3L)) {
					retval.put(3L, retval.get(3L)+1);
				}
				else {
					retval.put(3L, 1);
				}
			}
		}
		else {
			workingCopy = recordFactors(workingCopy,p,retval);
		}
		p = 5L;
		if((base%5)==0) {
			while(workingCopy.digits[workingCopy.digits.length-1]%5 == 0) {
				DivisionResult div = workingCopy.dividedBy(5);
				workingCopy = div.getNumber();
				if(retval.containsKey(5L)) {
					retval.put(5L, retval.get(5L)+1);
				}
				else {
					retval.put(5L, 1);
				}
			}
		}
		else {
			workingCopy = recordFactors(workingCopy,p,retval);
		}
		p = 7L;
		while(true) {
			if((workingCopy.digits.length == 1)&&(workingCopy.digits[0] == 1))
				break;
			workingCopy = recordFactors(workingCopy,p,retval);
			p+=2;
		}
		return retval;
	}
	
	private BigInt recordFactors(BigInt workingCopy, long factor, Map<Long,Integer> factorMap) {
		while(true) {
			if((workingCopy.digits.length == 1)&&(workingCopy.digits[0] == 1))
				break;
			DivisionResult div = workingCopy.dividedBy(factor);
			if(div.getRemainder() == 0) {
				workingCopy = div.getNumber();
				if(factorMap.containsKey(factor)) {
					factorMap.put(factor, factorMap.get(factor)+1);
				}
				else {
					factorMap.put(factor, 1);
				}
			}
			else {
				break;
			}
		}
		return workingCopy;
	}
	
	/**
	 * 
	 * @param n
	 * @return A DivisionResult object representing the result of integer division of this number by n.
	 */
	public DivisionResult dividedBy(long n) {
		long r = 0;
		LinkedList<Integer> retval = new LinkedList<>();
		for(int i=0;i<digits.length;i++) {
			r*=base;
			r+=digits[i];
			retval.add((int)(r/n));
			r%=n;
		}
		while(retval.getFirst() == 0) {
			retval.removeFirst();
		}
		if(retval.size()==0)
			return new DivisionResult(null,r);
		int[] digits = new int[retval.size()];
		int i=0;
		while(retval.size()>0) {
			digits[i] = retval.removeFirst();
			i++;
		}
		return new DivisionResult(new BigInt(base,digits),r);
	}
	
	/**
	 * 
	 * @param n
	 * @return A number representing the multiplication of this number by n.
	 */
	public BigInt multipliedBy(long n) {
		if(n==0)
			return new BigInt(base,0);
		if(n==1)
			return this;
		BigInt multiplier = new BigInt(base,n);
		return multipliedBy(multiplier);
	}
	
	/**
	 * 
	 * @param multiplier
	 * @return A number representing the multiplication of this number by multiplier.
	 */
	public BigInt multipliedBy(BigInt multiplier) {
		if(base!=multiplier.getBase())
			throw new IllegalArgumentException("Bases of numbers being multiplied must match.");
		if(getDigitCount()==0)
			return multiplier;
		if(multiplier.getDigitCount()==0)
			return multiplier;
		if((multiplier.getDigitCount()==1)&&(multiplier.digits[0]==1))
			return this;
		long[][] parts = new long[digits.length][multiplier.digits.length];
		for(int i=0;i<digits.length;i++) {
			for(int j=0;j<multiplier.digits.length;j++) {
				parts[i][j]=digits[i]*multiplier.digits[j];
			}
		}
		for(int i=0;i<(digits.length-1);i++) {
			for(int j=1;j<multiplier.digits.length;j++) {
				parts[i+1][j-1] += parts[i][j];
				parts[i][j] = 0;
			}
		}
		for(int i=multiplier.digits.length-1;i>0;i--) {
			parts[digits.length-1][i-1] += parts[digits.length-1][i]/base;
			parts[digits.length-1][i]%=base;
		}
		for(int i=digits.length-1;i>0;i--) {
			parts[i-1][0] += parts[i][0]/base;
			parts[i][0]%=base;
		}
		int[] newDigits = new int[digits.length+multiplier.digits.length];
		int i=0;
		if(parts[0][0]>=base) {
			newDigits = new int[digits.length+multiplier.digits.length];
			newDigits[0] = (int)(parts[0][0]/base);
			parts[0][0]%=base;
			i++;
		}
		else {
			newDigits = new int[digits.length+multiplier.digits.length-1];
		}
		for(int j=0;j<digits.length;j++) {
			newDigits[i] = (int)parts[j][0];
			i++;
		}
		for(int j=1;j<multiplier.digits.length;j++) {
			newDigits[i] = (int)parts[digits.length-1][j];
			i++;
		}
		return new BigInt(base,newDigits);
	}
	
	/**
	 * 
	 * @return A simple string representation of this number in the number's base.
	 * @see toString Gives a more detailed output, identifiying the base and the array of digits (each digit being represented in base 10)
	 */
	public String toSimpleString() {
		String retval = "";
		for(int digit:digits) 
			retval+=Integer.toString(digit, base);
		return retval;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BigInt [base=" + base + ", digits=" + Arrays.toString(digits) + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + base;
		result = prime * result + Arrays.hashCode(digits);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigInt other = (BigInt) obj;
		if (base != other.base)
			return false;
		if (!Arrays.equals(digits, other.digits))
			return false;
		return true;
	}
}
