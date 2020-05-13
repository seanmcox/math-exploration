/**
 * 
 */
package com.shtick.math;

/**
 * @author scox
 *
 */
public class DivisionResult {
	private BigInt number;
	private long remainder;
	
	/**
	 * 
	 * @param number
	 * @param remainder
	 */
	public DivisionResult(BigInt number, long remainder) {
		super();
		this.number = number;
		this.remainder = remainder;
	}

	/**
	 * @return the number
	 */
	public BigInt getNumber() {
		return number;
	}

	/**
	 * @return the remainder
	 */
	public long getRemainder() {
		return remainder;
	}
	
}
