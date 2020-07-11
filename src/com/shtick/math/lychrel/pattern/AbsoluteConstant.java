/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * @author scox
 *
 */
public class AbsoluteConstant extends Constant{
	private ConstantGroup constantGroup;
	private int offset;
	
	/**
	 * @param constantGroup The ConstantGroup this constant belongs to.
	 * @param offset If 0 or positive, then this is relative to the beginning of the group. If negative, then the offset is relative to the end of the group. (eg. -1 is the last constant in a group.)
	 */
	public AbsoluteConstant(ConstantGroup constantGroup, int offset) {
		super();
		this.constantGroup = constantGroup;
		this.offset = offset;
	}

	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.pattern.Constant#compareDetail(com.shtick.math.lychrel.pattern.Constant)
	 */
	@Override
	public int compareDetail(Constant c) {
		AbsoluteConstant ac = (AbsoluteConstant)c;
		int retval = constantGroup.toString().compareTo(ac.constantGroup.toString());
		if(retval!=0)
			return retval;
		if((offset<0)^(ac.offset<0))
			return (offset<0)?1:-1;
		return offset-ac.offset;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if((constantGroup.getSize()>0)&&(offset<0))
			return ""+constantGroup.getSymbol()+"["+(constantGroup.getSize()+offset)+"]";
		return ""+constantGroup.getSymbol()+"["+offset+"]";
	}
}
