/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * @author scox
 *
 */
public class RelativeConstant extends Constant{
	private int offset;
	
	/**
	 * @param offset Defines this constant relative to position of an element within an ExpressionPattern.
	 *               Each ExpressionPattern that allows RelativeConstants, aligns its elements to a ConstantGroup such that a RelativeConstant with offset 0
	 *               corresponds to a specific element of the ConstantGroup. A RelativeConstant with offset -1 is the previous constant in the ConstantGroup,
	 *               whereas a RelativeConstant with offset 1 is the next constant in the ConstantGroup. Other offsets refer to other elements some steps before or
	 *               after the RelativeConstant with offset 0.
	 */
	public RelativeConstant(int offset) {
		super();
		this.offset = offset;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.pattern.Constant#compareDetail(com.shtick.math.lychrel.pattern.Constant)
	 */
	@Override
	public int compareDetail(Constant c) {
		RelativeConstant rc = (RelativeConstant)c;
		return offset-rc.offset;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String offsetString = "i";
		if(offset>0)
			offsetString+="+"+offset;
		else if(offset<0)
			offsetString+=offset;
		return "c["+offsetString+"]";
	}
}
