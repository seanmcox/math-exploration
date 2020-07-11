/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * @author scox
 *
 */
public class ConstantGroup {
	private char symbol;
	private int size;
	
	/**
	 * 
	 * @param size -1 if unbound. Otherwise, must be greater than 0.
	 * @param symbol 
	 */
	public ConstantGroup(int size) {
		if((size==0)||(size<-1))
			throw new IllegalArgumentException("Invalid ConstantGroup size.");
		this.size = size;
		this.symbol = '\u03B1'; // Alpha
	}
	
	/**
	 * 
	 * @param size -1 if unbound. Otherwise, must be greater than 0.
	 * @param symbol 
	 */
	public ConstantGroup(int size, char symbol) {
		if((size==0)||(size<-1))
			throw new IllegalArgumentException("Invalid ConstantGroup size.");
		this.size = size;
		this.symbol = symbol;
	}

	/**
	 * @return -1 if unbound. Otherwise, must be greater than 0.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}
}
