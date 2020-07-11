/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * @author scox
 *
 */
public class MultiplicationGroup implements Comparable<MultiplicationGroup>{
	private int factor;
	private Constant constant;
	
	/**
	 * @param factor
	 * @param constant
	 */
	public MultiplicationGroup(int factor, Constant constant) {
		super();
		this.factor = factor;
		this.constant = (factor==0)?null:constant;
	}
	
	/**
	 * 
	 * @return true is the MultiplicationGroup contains a RelativeConstant and false otherwise.
	 */
	public boolean hasRelativeConstant() {
		return constant instanceof RelativeConstant;
	}
	
	/**
	 * 
	 * @param offset
	 * @return A copy of this AdditionGroup with all RelativeConstant offsets adjusted by offset.
	 */
	public MultiplicationGroup getTransposed(int offset) {
		if(!(constant instanceof RelativeConstant))
			return this;
		return new MultiplicationGroup(factor,new RelativeConstant(((RelativeConstant)constant).getOffset()+offset));
	}

	/**
	 * 
	 * @param origin
	 * @param constantGroup
	 * @return A copy of this AdditionGroup with all RelativeConstant offsets adjusted by offset.
	 */
	MultiplicationGroup getAbsoluteElement(int origin, ConstantGroup constantGroup) {
		if(!(constant instanceof RelativeConstant))
			return this;
		return new MultiplicationGroup(factor,new AbsoluteConstant(constantGroup,origin+((RelativeConstant)constant).getOffset()));
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}
	
	/**
	 * @return the constant
	 */
	public Constant getConstant() {
		return constant;
	}

	/**
	 * 
	 * @return true if there are no elements and false otherwise. This would make the MultiplicationGroup a simple number with the value of the factor property.
	 */
	public boolean isSimpleNumber() {
		return constant==null;
	}

	/**
	 * Checks to see if otherGroup can be simply added to this MultiplicationGroup by adding the factors.
	 * 
	 * @param otherGroup
	 * @return true if every element of otherGroup matches the corresponding element of this MultiplicationGroup. false otherwise.
	 */
	public boolean canCombine(MultiplicationGroup otherGroup) {
		if((constant==null)&&(otherGroup.constant==null))
			return true;
		if((constant==null)||(otherGroup.constant==null))
			return false;
		return constant.equals(otherGroup.constant);
	}
	
	/**
	 * 
	 * @param otherGroup
	 * @return A copy of this MultiplicationGroup with a factor equal to the factor of this MultiplicationGroup plus the factor of otherGroup.
	 * @throws IllegalArgumentException if this.canCombine(otherGroup) returns false.
	 */
	public MultiplicationGroup add(MultiplicationGroup otherGroup) throws IllegalArgumentException{
		if(!canCombine(otherGroup))
			throw new IllegalArgumentException("Groups not combinable.");
		return new MultiplicationGroup(factor+otherGroup.factor, constant);
	}
	
	/**
	 * 
	 * @param otherGroup
	 * @return A MultiplicationGroup that represents otherGroup subtracted from this instance.
	 *         This can only be done if canCombine(otherGroup) would return true. 
	 */
	public MultiplicationGroup subtract(MultiplicationGroup otherGroup) {
		if(!canCombine(otherGroup))
			throw new IllegalArgumentException("Groups not combinable.");
		return new MultiplicationGroup(factor-otherGroup.factor, constant);
	}
	
	/**
	 * 
	 * @return A copy of this MultiplicationGroup with the factor multiplied by -1.
	 */
	public MultiplicationGroup getNegative() {
		return new MultiplicationGroup(-factor, constant);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MultiplicationGroup o) {
		if(constant == o.constant)
			return 0;
		if(constant == null)
			return -1;
		if(o.constant == null)
			return 1;
		return constant.compareTo(o.constant);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retval = "";
		if(constant!=null) {
			if(factor==1)
				retval=constant.toString();
			else
				retval=factor+"*"+constant.toString();
		}
		else {
			retval = ""+factor;
		}
		return retval;
	}
}
