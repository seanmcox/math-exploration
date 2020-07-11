/**
 * 
 */
package com.shtick.math.lychrel;

import com.shtick.math.BigInt;

/**
 * @author scox
 *
 */
public class BigIntLychrel extends BigInt implements LychrelNumber{
	/**
	 * Constructs the base 10, 196 Lychrel number.
	 * @throws IllegalArgumentException
	 */
	public BigIntLychrel() throws IllegalArgumentException{
		super(10,196);
	}
	
	/**
	 * @param base Must be greater than 1.
	 * @param digits A non-empty array of digits in the range of 0 to base-1 inclusive. The digit at index zero is the most significant digit and must not be zero.
	 *               A number with a value of zero can be represented with an empty array.
	 * @throws IllegalArgumentException
	 */
	public BigIntLychrel(int base, int[] digits) {
		super(base, digits);
	}

	/**
	 * @param base Must be greater than 1.
	 * @param value Must be greater than 0.
	 * @throws IllegalArgumentException
	 */
	public BigIntLychrel(int base, long value) {
		super(base, value);
	}

	/**
	 * @param base
	 * @param number A non-empty numeric string appropriate to the base.
	 * @throws NumberFormatException
	 */
	public BigIntLychrel(int base, String number) {
		super(base, number);
	}

	public boolean isLychrelStop() {
		for(int i=0;i<digits.length/2;i++)
			if(digits[i] != digits[digits.length-1-i])
				return false;
		return true;
	}

	public BigIntLychrel nextLychrelNumber() {
		int[] rawDigits = new int[digits.length];
		int base = getBase();
		for(int i=0;i<digits.length;i++)
			rawDigits[i] = digits[i] + digits[digits.length-1-i];
		for(int i=digits.length-1;i>0;i--) {
			if(rawDigits[i]>=base) {
				rawDigits[i]-=base;
				rawDigits[i-1]++;
			}
		}
		int[] newDigits = rawDigits;
		if(rawDigits[0]>=base) {
			newDigits = new int[rawDigits.length+1];
			for(int i=0;i<rawDigits.length;i++)
				newDigits[i+1] = rawDigits[i];
			newDigits[0] = 1;
			newDigits[1] -= base;
		}
		return new BigIntLychrel(base, newDigits);
	}
}
