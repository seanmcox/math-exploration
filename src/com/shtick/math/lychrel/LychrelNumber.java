/**
 * 
 */
package com.shtick.math.lychrel;

/**
 * @author scox
 *
 */
public interface LychrelNumber {
	/**
	 * 
	 * @return The result of adding each digit in the number to the digit in the opposite position in the number. 
	 */
	LychrelNumber nextLychrelNumber();

	/**
	 * 
	 * @return true if the number is a palindrome and false otherwise.
	 */
	boolean isLychrelStop();
}
