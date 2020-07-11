/**
 * 
 */
package com.shtick.math.lychrel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.shtick.math.lychrel.pattern.AdditionGroup;
import com.shtick.math.lychrel.pattern.BaseConstant;
import com.shtick.math.lychrel.pattern.ExpressionPattern;
import com.shtick.math.lychrel.pattern.MultiplicationGroup;
import com.shtick.math.lychrel.pattern.PatternSize;

/**
 * @author scox
 *
 */
public class PatternedLychrel implements LychrelNumber{
	/**
	 * 
	 */
	public static final int BASE_TYPE_INFINITE = 0;
	/**
	 * 
	 */
	public static final int BASE_TYPE_UNKNOWN = 1;
	private List<ExpressionPattern> numberPatternDefinition;
	private int baseType;

	/**
	 * @param baseType Indicates what the value of the base is supposed to be for the purposes of identifying potential Lychrel stops. Must be greater than or equal to zero. A value of zero indicates the infinite base and a value of one means that the value should be treated as an unknown positive integer, like other constants.
	 * @param numberPatternDefinition
	 */
	public PatternedLychrel(int baseType, List<ExpressionPattern> numberPatternDefinition) {
		if(numberPatternDefinition.size()==0)
			throw new IllegalArgumentException("Empty pattern invalid.");
		if(baseType<0)
			throw new IllegalArgumentException("Invalid base type.");
		this.baseType = baseType;
		this.numberPatternDefinition = new LinkedList<>(numberPatternDefinition);
	}
	
	/**
	 * 
	 * @param baseType Indicates what the value of the base is supposed to be for the purposes of identifying potential Lychrel stops. Must be greater than or equal to zero. A value of zero indicates the infinite base and a value of one means that the value should be treated as an unknown positive integer, like other constants.
	 * @param numberPatternDefinition
	 */
	public PatternedLychrel(int baseType, ExpressionPattern ... numberPatternDefinition) {
		if(numberPatternDefinition.length==0)
			throw new IllegalArgumentException("Empty pattern invalid.");
		if(baseType<0)
			throw new IllegalArgumentException("Invalid base type.");
		this.baseType = baseType;
		this.numberPatternDefinition = new LinkedList<>();
		for(ExpressionPattern pattern:numberPatternDefinition)
			this.numberPatternDefinition.add(pattern);
	}

	/**
	 * @return the baseType
	 */
	public int getBaseType() {
		return baseType;
	}

	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.LychrelNumber#nextLychrelNumber()
	 */
	@Override
	public PatternedLychrel nextLychrelNumber() {
		// TODO For baseType>1 perform carry based on the assumed actual value of b and when hard constants add up to baseType. (Keep the b constant unresolved in the actual representations of the numbers.)
		// It is probably inappropriate to use non-base constants when baseType>1
		ListIterator<ExpressionPattern> forwardIterator = numberPatternDefinition.listIterator();
		ListIterator<ExpressionPattern> reverseIterator = numberPatternDefinition.listIterator(numberPatternDefinition.size()-1);
		reverseIterator.next();
		
		LinkedList<ExpressionPattern> next = new LinkedList<>();
		
		int leftAdvance = 0;
		int rightAdvance = 0;
		while(forwardIterator.hasNext()) {
			ExpressionPattern forward = forwardIterator.next();
			ExpressionPattern reverse = reverseIterator.previous();
			PatternSize leftSize = forward.getPatternSize();
			PatternSize rightSize = reverse.getPatternSize();
			int leftLeft;
			int rightLeft;
			if((leftSize.getConstantGroup()!=null)&&(rightSize.getConstantGroup()!=null)&&(leftSize.getConstantGroup()!=rightSize.getConstantGroup()))
				throw new RuntimeException("PatternedLychrel: Mismatched infinite constant groups cannot be added. Must construct PatternedLychrels with matching constant groups on opposite sides of the pattern.");
			if(leftSize.getConstantGroup()==null) {
				leftLeft = leftSize.getModifier()-leftAdvance;
			}
			else if(leftSize.getConstantGroup().getSize()>0) {
				leftLeft = leftSize.getConstantGroup().getSize()+leftSize.getModifier()-leftAdvance;
			}
			else if(leftAdvance<0) {
				leftLeft = -leftAdvance;
			}
			else {
				leftLeft = -1;
			}
			if(rightSize.getConstantGroup()==null) {
				rightLeft = rightSize.getModifier()-rightAdvance;
			}
			else if(rightSize.getConstantGroup().getSize()>0) {
				rightLeft = rightSize.getConstantGroup().getSize()+rightSize.getModifier()-rightAdvance;
			}
			else if(leftAdvance<0) {
				rightLeft = -rightAdvance;
			}
			else {
				rightLeft = -1;
			}
			
			if((leftLeft>0)&&(rightLeft>0)) {
				int count = Math.min(leftLeft, rightLeft);
				ExpressionPattern newPattern = digitReverseAndSumPattern(forward, reverse, count, leftAdvance, rightAdvance);
				next.add(newPattern);
				if(leftLeft>count) {
					leftAdvance+=count;
					forwardIterator.previous();
				}
				else {
					leftAdvance=0;
				}
				if(rightLeft>count) {
					rightAdvance+=count;
					reverseIterator.next();
				}
				else {
					rightAdvance=0;
				}
			}
			else if(leftLeft>0) {
				int count = leftLeft;
				ExpressionPattern newPattern = digitReverseAndSumPattern(forward, reverse, count, leftAdvance, rightAdvance);
				next.add(newPattern);
				leftAdvance=0;
				rightAdvance+=count;
				reverseIterator.next();
			}
			else if(rightLeft>0) {
				int count = rightLeft;
				ExpressionPattern newPattern = digitReverseAndSumPattern(forward, reverse, count, leftAdvance, rightAdvance);
				next.add(newPattern);
				leftAdvance+=count;
				forwardIterator.previous();
				rightAdvance=0;
			}
			else {
				int leftSizeModifier = leftSize.getModifier() - leftAdvance;
				int rightSizeModifier = rightSize.getModifier() - rightAdvance;
				int newModifier = Math.min(leftSizeModifier, rightSizeModifier);
				ExpressionPattern newPattern = digitReverseAndSumPattern(forward, reverse, newModifier, leftAdvance, rightAdvance);
				next.add(newPattern);
				leftAdvance=newModifier - leftSizeModifier;
				rightAdvance=newModifier - rightSizeModifier;
				if(leftAdvance!=0)
					forwardIterator.previous();
				if(rightAdvance!=0)
					reverseIterator.next();
			}
		}
		
		// Handle carries.
		reverseIterator = next.listIterator(next.size()-1);
		reverseIterator.next();
		boolean carried=false;
		while(reverseIterator.hasPrevious()){
			ExpressionPattern current = reverseIterator.previous();
			reverseIterator.remove();
			ExpressionPattern carriedExpression = new ExpressionPattern(current.getPatternSize(), current.getOffset(), current.getExpression().add(new AdditionGroup(new MultiplicationGroup[]{new MultiplicationGroup(1, null)})));
			if(carried) {
				int carryStatus = carriedExpression.getExpression().getCarryStatus();
				if(carryStatus==1) {
					current = new ExpressionPattern(current.getPatternSize(), 0, carriedExpression.getExpression().add(new AdditionGroup(new MultiplicationGroup[]{new MultiplicationGroup(-1, new BaseConstant())})));
				}
				else if(carryStatus==-1) {
					if(current.getPatternSize().getModifier()!=1) {
						// Separate out the furthest right carried element to push back into the list.
						ExpressionPattern righty = carriedExpression.getAbsoluteElement(0);
						reverseIterator.add(righty);
						reverseIterator.previous();
						// Push an uncarried version of the remaining elements back on.
						current = new ExpressionPattern(new PatternSize(current.getPatternSize().getConstantGroup(),current.getPatternSize().getModifier()-1), current.getOffset()+1, current.getExpression());
					}
					else {
						current = carriedExpression;
					}
					carried = false;
				}
				else {
					throw new RuntimeException("Ambiguous carry status.");
				}
			}
			else {
				int carryStatus = current.getExpression().getCarryStatus();
				if(carryStatus==1) {
					carried = true;
					current = new ExpressionPattern(current.getPatternSize(), 0, current.getExpression().add(new AdditionGroup(new MultiplicationGroup[]{new MultiplicationGroup(-1, new BaseConstant())})));
					if(current.getPatternSize().getModifier()!=1) {
						// Separate out the furthest right uncarried element to push back into the list.
						ExpressionPattern righty = current.getAbsoluteElement(0);
						reverseIterator.add(righty);
						reverseIterator.previous();
						// Push a carried version of the remaining elements back on.
						current = new ExpressionPattern(new PatternSize(current.getPatternSize().getConstantGroup(),current.getPatternSize().getModifier()-1), current.getOffset()+1, current.getExpression().add(new AdditionGroup(new MultiplicationGroup[]{new MultiplicationGroup(1, null)})));
					}
				}
				else if(carryStatus==0) {
					throw new RuntimeException("Ambiguous carry status.");
				}
			}
			reverseIterator.add(current);
			reverseIterator.previous();
		}
		if(carried)
			reverseIterator.add(new ExpressionPattern(new PatternSize(null, 1), 0, new AdditionGroup(new MultiplicationGroup[]{new MultiplicationGroup(1, null)})));
		
		return new PatternedLychrel(baseType,next);
	}
	
	private ExpressionPattern digitReverseAndSumPattern(ExpressionPattern forward, ExpressionPattern reverse, int sizeModifier, int leftAdvance, int rightAdvance) {
		PatternSize leftSize = forward.getPatternSize();
		PatternSize rightSize = reverse.getPatternSize();
		if(leftSize.getConstantGroup()!=null) {
			forward = new ExpressionPattern(new PatternSize(leftSize.getConstantGroup(), sizeModifier), forward.getOffset()+leftAdvance, reverse.getExpression());
			if(rightSize.getConstantGroup()!=null) {
				// Reverse and adjust the right pattern to be addable to the left.
				int adjustedOffset = -1-reverse.getOffset()+((reverse.getOffset()>0)?(-rightAdvance):rightAdvance);
				reverse = new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), forward.getOffset(), reverse.getExpression().getTransposed(adjustedOffset-forward.getOffset()));
				return new ExpressionPattern(new PatternSize(null, sizeModifier), forward.getOffset(),forward.getExpression().add(reverse.getExpression()));
			}
			return new ExpressionPattern(new PatternSize(null, sizeModifier), forward.getOffset(),forward.getExpression().add(reverse.getExpression()));
		}
		if(rightSize.getConstantGroup()!=null) {
			reverse = new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), -1-reverse.getOffset()+((reverse.getOffset()>0)?(-rightAdvance):rightAdvance), reverse.getExpression());
			return new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), reverse.getOffset(),forward.getExpression().add(reverse.getExpression()));
		}
		return new ExpressionPattern(new PatternSize(null, sizeModifier), 0,forward.getExpression().add(reverse.getExpression()));
	}

	/* (non-Javadoc)
	 * @see com.shtick.math.lychrel.LychrelNumber#isLychrelStop()
	 */
	@Override
	public boolean isLychrelStop() {
		ListIterator<ExpressionPattern> forwardIterator = numberPatternDefinition.listIterator();
		ListIterator<ExpressionPattern> reverseIterator = numberPatternDefinition.listIterator(numberPatternDefinition.size()-1);
		reverseIterator.next();

		int leftAdvance = 0;
		int rightAdvance = 0;
		while(forwardIterator.hasNext()) {
			ExpressionPattern forward = forwardIterator.next();
			ExpressionPattern reverse = reverseIterator.previous();
			
			PatternSize leftSize = forward.getPatternSize();
			PatternSize rightSize = reverse.getPatternSize();
			int leftLeft;
			int rightLeft;
			if((leftSize.getConstantGroup()!=null)&&(rightSize.getConstantGroup()!=null)&&(leftSize.getConstantGroup()!=rightSize.getConstantGroup()))
				throw new RuntimeException("PatternedLychrel: Mismatched infinite constant groups cannot be evaluated as potential Lychrel stops. Must construct PatternedLychrels with matching constant groups on opposite sides of the pattern.");
			if(leftSize.getConstantGroup()==null) {
				leftLeft = leftSize.getModifier()-leftAdvance;
			}
			else if(leftSize.getConstantGroup().getSize()>0) {
				leftLeft = leftSize.getConstantGroup().getSize()+leftSize.getModifier()-leftAdvance;
			}
			else if(leftAdvance<0) {
				leftLeft = -leftAdvance;
			}
			else {
				leftLeft = -1;
			}
			if(rightSize.getConstantGroup()==null) {
				rightLeft = rightSize.getModifier()-rightAdvance;
			}
			else if(rightSize.getConstantGroup().getSize()>0) {
				rightLeft = rightSize.getConstantGroup().getSize()+rightSize.getModifier()-rightAdvance;
			}
			else if(leftAdvance<0) {
				rightLeft = -rightAdvance;
			}
			else {
				rightLeft = -1;
			}

			if((leftLeft>0)&&(rightLeft>0)) {
				int count = Math.min(leftLeft, rightLeft);
				if(!digitsCanMatch(forward, reverse, count, leftAdvance, rightAdvance))
					return false;
				if(leftLeft>count) {
					leftAdvance+=count;
					forwardIterator.previous();
				}
				else {
					leftAdvance=0;
				}
				if(rightLeft>count) {
					rightAdvance+=count;
					reverseIterator.next();
				}
				else {
					rightAdvance=0;
				}
			}
			else if(leftLeft>0) {
				int count = leftLeft;
				if(!digitsCanMatch(forward, reverse, count, leftAdvance, rightAdvance))
					return false;
				leftAdvance=0;
				rightAdvance+=count;
				reverseIterator.next();
			}
			else if(rightLeft>0) {
				int count = rightLeft;
				if(!digitsCanMatch(forward, reverse, count, leftAdvance, rightAdvance))
					return false;
				leftAdvance+=count;
				forwardIterator.previous();
				rightAdvance=0;
			}
			else {
				int leftSizeModifier = leftSize.getModifier() - leftAdvance;
				int rightSizeModifier = rightSize.getModifier() - rightAdvance;
				int newModifier = Math.min(leftSizeModifier, rightSizeModifier);
				if(!digitsCanMatch(forward, reverse, newModifier, leftAdvance, rightAdvance))
					return false;
				leftAdvance=newModifier - leftSizeModifier;
				rightAdvance=newModifier - rightSizeModifier;
				if(leftAdvance!=0)
					forwardIterator.previous();
				if(rightAdvance!=0)
					reverseIterator.next();
			}
		}
		return true;
	}
	
	private boolean digitsCanMatch(ExpressionPattern forward, ExpressionPattern reverse, int sizeModifier, int leftAdvance, int rightAdvance) {
		// TODO Keep track of a minimum value for the base in order to have even gotten this far to further constrain the unknown base (might even be useful to know for the infinite base)
		// TODO Logically deduce values of constants, where possible, in order to see if an internally consistent set of values is possible across all the constants.
		PatternSize leftSize = forward.getPatternSize();
		PatternSize rightSize = reverse.getPatternSize();
		ExpressionPattern difference;
		if(leftSize.getConstantGroup()!=null) {
			forward = new ExpressionPattern(new PatternSize(leftSize.getConstantGroup(), sizeModifier), forward.getOffset()+leftAdvance, reverse.getExpression());
			if(rightSize.getConstantGroup()!=null) {
				// Reverse and adjust the right pattern to be addable to the left.
				int adjustedOffset = -1-reverse.getOffset()+((reverse.getOffset()>0)?(-rightAdvance):rightAdvance);
				reverse = new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), forward.getOffset(), reverse.getExpression().getTransposed(adjustedOffset-forward.getOffset()));
				difference = new ExpressionPattern(new PatternSize(null, sizeModifier), forward.getOffset(),forward.getExpression().subtract(reverse.getExpression()));
			}
			else {
				difference = new ExpressionPattern(new PatternSize(null, sizeModifier), forward.getOffset(),forward.getExpression().subtract(reverse.getExpression()));
			}
		}
		else {
			if(rightSize.getConstantGroup()!=null) {
				reverse = new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), -1-reverse.getOffset()+((reverse.getOffset()>0)?(-rightAdvance):rightAdvance), reverse.getExpression());
				difference = new ExpressionPattern(new PatternSize(rightSize.getConstantGroup(), sizeModifier), reverse.getOffset(),forward.getExpression().subtract(reverse.getExpression()));
			}
			else {
				difference = new ExpressionPattern(new PatternSize(null, sizeModifier), 0,forward.getExpression().subtract(reverse.getExpression()));
			}
		}
		AdditionGroup elements = difference.getExpression();
		int constant=0;
		boolean negativeConst = false;
		boolean positiveConst = false;
		for(MultiplicationGroup group:elements.getMultiplicationGroups()) {
			if(group.getConstant()==null) {
				constant+=group.getFactor();
			}
			else {
				if(group.getConstant() instanceof BaseConstant) {
					if(baseType==BASE_TYPE_INFINITE)
						return false;
					if(baseType>1) {
						constant+=group.getFactor()*baseType;
						continue;
					}
				}
				if(group.getFactor()<0)
					negativeConst=true;
				else
					positiveConst=true;
			}
		}
		return ((positiveConst||(constant>0))&&(negativeConst||(constant<0)))||((constant==0)&&!negativeConst&&!positiveConst);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retval="";
		for(ExpressionPattern expression:numberPatternDefinition) {
			if(retval.length()>0)
				retval+=", ";
			retval+=expression.toString();
		}
		return retval;
	}
	
}
