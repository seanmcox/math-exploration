package com.shtick.math.lychrel.pattern;

/**
 * 
 * @author scox
 *
 */
public class ExpressionPattern implements PatternItem{
	private PatternSize patternSize;
	private int offset;
	private AdditionGroup expression;
	
	/**
	 * @param patternSize Captures both the ConstantGroup of the pattern, and the size of the pattern relative to that ConstantGroup.
	 *                    If no ConstantGroup is specified then the expression for this pattern cannot use RelativeConstant elements.
	 * @param offset If 0 or positive, then the value of the first element has RelativeConstants interpreted as being relative to the constant at the given offset from the beginning. (eg. 0 is the first constant in a group and 1 the constant after that.)
	 *               If negative, then the value of the first element has RelativeConstants interpreted as being relative to the given offset from the end and subsequent positions have the constant index decremented rather than incremented. (eg. -1 is the last constant in a group and -2 the constant before that.)
	 * @param expression 
	 */
	public ExpressionPattern(PatternSize patternSize, int offset, AdditionGroup expression) {
		super();
		this.patternSize = patternSize;
		this.offset = offset;
		if((patternSize.getConstantGroup()==null)&&expression.hasRelativeConstants())
			throw new IllegalArgumentException("A pattern that isn't sized to a ConstantGroup cannot contain relative constants.");
		this.expression = expression;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @return the expression
	 */
	public AdditionGroup getExpression() {
		return expression;
	}

	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.pattern.PatternItem#getPatternSize()
	 */
	@Override
	public PatternSize getPatternSize() {
		return patternSize;
	}

	/**
	 * 
	 * @param offset If 0 or positive, then 0 indicates the first element of the group, 1 the second, and so on.
	 *               If negative, then -1 indicates the last element of the group, -2 the next to the last element, and so on.
	 * @return The absolute version of the given element.
	 */
	public ExpressionPattern getAbsoluteElement(int offset) {
		if(patternSize.getConstantGroup()==null)
			return new ExpressionPattern(new PatternSize(null,1), 0, expression);
		int origin;
		if(offset>=0) {
			if(this.offset>=0)
				origin = offset+this.offset;
			else
				origin = this.offset-offset;
		}
		else {
			if(this.offset<0)
				origin = -patternSize.getModifier()+this.offset-offset;
			else
				origin = patternSize.getModifier()+this.getOffset()+offset;
		}
		return new ExpressionPattern(new PatternSize(null,1), 0, expression.getAbsoluteElement(origin, patternSize.getConstantGroup()));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retval = "";
		if(patternSize.getModifier()<=0) {
			if(patternSize.getConstantGroup().getSize()>0) {
				for(int i=0;i<patternSize.getConstantGroup().getSize()+patternSize.getModifier();i++) {
					if(retval.length()>0)
						retval+=", ";
					retval+=getAbsoluteElement(i).toString();
				}
			}
			else {
				retval=getAbsoluteElement(0).toString();
				retval+=", ..., ";
				retval+=getAbsoluteElement(-1).toString();
			}
		}
		else {
			for(int i=0;i<patternSize.getModifier();i++) {
				if(retval.length()>0)
					retval+=", ";
				retval+=expression.toString();
			}
		}
		return retval;
	}
}
