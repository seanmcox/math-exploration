/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * A hard constant value that represents the undefined base of the number being patterned.
 * 
 * It is sometimes referred to as the infinite base. Patterned Lychrels progress from one value to the next as if in the limit where this base it taken toward infinity.
 * 
 * eg.
 * (Commas are used here for convenience to separate the digits of the number being patterned.
 * b is used to represent the BaseConstant.)
 * 
 * BaseConstant - Constant => Doesn't carry (ie. 1,0,(b - 5) + 2 = 1,0,b-3)
 * BaseConstant => Carries (ie. (b - 1) + 2 = 11 = 1,1)
 * BaseConstant + BaseConstant - Constant => Carries (ie. (b - 1) + (b - 1) = 1,(b-2))
 * 
 * Despite the description above, perturbations caused when, for some limited purpose, a constant expression is taken to be greater than b, will be of great interest. 
 * 
 * @author scox
 *
 */
public class BaseConstant extends Constant{
	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.pattern.Constant#compareDetail(com.shtick.math.lychrel.pattern.Constant)
	 */
	@Override
	public int compareDetail(Constant c) {
		// All BaseConstant instances are equivalent.
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "b";
	}
}
