/**
 * 
 */
package com.shtick.math.lychrel.pattern;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author scox
 *
 */
public class AdditionGroup {
	private MultiplicationGroup[] multiplicationGroups;
	
	/**
	 * @param multiplicationGroups
	 */
	public AdditionGroup(MultiplicationGroup[] multiplicationGroups) {
		super();
		this.multiplicationGroups = new MultiplicationGroup[multiplicationGroups.length];
		System.arraycopy(multiplicationGroups, 0, this.multiplicationGroups, 0, multiplicationGroups.length);
		Arrays.sort(this.multiplicationGroups);
	}
	
	/**
	 * 
	 * @return A copy of the MultiplicationGroups in the AdditionGroup.
	 */
	public MultiplicationGroup[] getMultiplicationGroups() {
		MultiplicationGroup[] retval = new MultiplicationGroup[multiplicationGroups.length];
		System.arraycopy(multiplicationGroups, 0, retval, 0, multiplicationGroups.length);
		return retval;
	}
	
	/**
	 * 
	 * @return -1 if this digit doesn't carry, (value < b)
	 *         1 if this digit does carry, and (value >= b)
	 *         0 if carry is ambiguous (eg. value = b - c1 +c2)
	 */
	public int getCarryStatus() {
		boolean hasSubtraction = false;
		boolean hasAddition = false;
		int baseCount = 0;
		for(MultiplicationGroup multiplicationGroup:multiplicationGroups) {
			if(multiplicationGroup.getConstant() instanceof BaseConstant)
				baseCount = multiplicationGroup.getFactor();
			else if(multiplicationGroup.getFactor()<0)
				hasSubtraction = true;
			else if(multiplicationGroup.getFactor()>0)
				hasAddition = true;
		}
		if(baseCount == 0)
			return -1;
		if(!hasSubtraction)
			return 1;
		if(baseCount>1)
			return 1;
		if(hasAddition)
			return 0;
		return -1;
	}
	
	/**
	 * 
	 * @param offset
	 * @return A copy of this AdditionGroup with all RelativeConstant offsets adjusted by offset.
	 */
	public AdditionGroup getTransposed(int offset) {
		MultiplicationGroup[] transposedMultiplicationGroups = new MultiplicationGroup[multiplicationGroups.length];
		for(int i=0;i<multiplicationGroups.length;i++)
			transposedMultiplicationGroups[i] = multiplicationGroups[i].getTransposed(offset);
		return new AdditionGroup(transposedMultiplicationGroups);
	}
	
	/**
	 * 
	 * @param origin
	 * @param constantGroup
	 * @return A copy of this AdditionGroup with all RelativeConstants translated to absolute elements by taking the relative elements to be relative to origin.
	 */
	AdditionGroup getAbsoluteElement(int origin, ConstantGroup constantGroup) {
		MultiplicationGroup[] absoluteMultiplicationGroups = new MultiplicationGroup[multiplicationGroups.length];
		for(int i=0;i<multiplicationGroups.length;i++)
			absoluteMultiplicationGroups[i] = multiplicationGroups[i].getAbsoluteElement(origin, constantGroup);
		return new AdditionGroup(absoluteMultiplicationGroups);
	}
	
	/**
	 * 
	 * @return true is the AdditionGroup contains a RelativeConstant and false otherwise.
	 */
	public boolean hasRelativeConstants() {
		for(MultiplicationGroup multiplicationGroup:multiplicationGroups)
			if(multiplicationGroup.hasRelativeConstant())
				return true;
		return false;
	}

	/**
	 * 
	 * @param otherGroup
	 * @return
	 */
	public AdditionGroup add(AdditionGroup otherGroup) {
		int i0 = 0;
		int i1 = 0;
		LinkedList<MultiplicationGroup> combined = new LinkedList<>();
		while((i0<multiplicationGroups.length)&&(i1<otherGroup.multiplicationGroups.length)) {
			int comp = multiplicationGroups[i0].compareTo(otherGroup.multiplicationGroups[i1]);
			if(comp<0) {
				combined.add(multiplicationGroups[i0]);
				i0++;
			}
			else if(comp>0) {
				combined.add(otherGroup.multiplicationGroups[i1]);
				i1++;
			}
			else{
				if(multiplicationGroups[i0].getFactor()!=-otherGroup.multiplicationGroups[i1].getFactor()) // They cancel out if they have opposite factors.
					combined.add(multiplicationGroups[i0].add(otherGroup.multiplicationGroups[i1]));
				i0++;
				i1++;
			}
		}
		while(i0<multiplicationGroups.length) {
			combined.add(multiplicationGroups[i0]);
			i0++;
		}
		while(i1<otherGroup.multiplicationGroups.length) {
			combined.add(otherGroup.multiplicationGroups[i1]);
			i1++;
		}
		return new AdditionGroup(combined.toArray(new MultiplicationGroup[combined.size()]));
	}
	
	/**
	 * 
	 * @param otherGroup
	 * @return 
	 */
	public AdditionGroup subtract(AdditionGroup otherGroup) {
		int i0 = 0;
		int i1 = 0;
		LinkedList<MultiplicationGroup> combined = new LinkedList<>();
		while((i0<multiplicationGroups.length)&&(i1<otherGroup.multiplicationGroups.length)) {
			int comp = multiplicationGroups[i0].compareTo(otherGroup.multiplicationGroups[i1]);
			if(comp<0) {
				combined.add(multiplicationGroups[i0]);
				i0++;
			}
			else if(comp>0) {
				combined.add(otherGroup.multiplicationGroups[i1].getNegative());
				i1++;
			}
			else { // They cancel out if they have equal factors.
				if(multiplicationGroups[i0].getFactor()!=otherGroup.multiplicationGroups[i1].getFactor())
					combined.add(multiplicationGroups[i0].subtract(otherGroup.multiplicationGroups[i1]));
				i0++;
				i1++;
			}
		}
		while(i0<multiplicationGroups.length) {
			combined.add(multiplicationGroups[i0]);
			i0++;
		}
		while(i1<otherGroup.multiplicationGroups.length) {
			combined.add(otherGroup.multiplicationGroups[i1].getNegative());
			i1++;
		}
		return new AdditionGroup(combined.toArray(new MultiplicationGroup[combined.size()]));
	}
	
	/**
	 * 
	 * @param otherGroup
	 * @return
	 */
	public boolean maybeEqual(AdditionGroup otherGroup) {
		int strictlyEqualParts = 0;
		int combinableUnequalParts = 0;
		int combinableUnequalPartsThisMore = 0;
		int combinableUnequalPartsThatMore = 0;
		int novelParts = 0;
		int novelPartsThisMore = 0;
		int novelPartsThatMore = 0;
		int i0 = 0;
		int i1 = 0;
		while((i0<multiplicationGroups.length)&&(i1<otherGroup.multiplicationGroups.length)) {
			int comp = multiplicationGroups[i0].compareTo(otherGroup.multiplicationGroups[i1]);
			if(comp<0) {
				i0++;
				novelParts++;
				if(multiplicationGroups[i0].getFactor()>0)
					novelPartsThisMore++;
				else
					novelPartsThatMore++;
			}
			else if(comp>0) {
				i1++;
				novelParts++;
				if(otherGroup.multiplicationGroups[i1].getFactor()>0)
					novelPartsThatMore++;
				else
					novelPartsThisMore++;
			}
			else {
				if(multiplicationGroups[i0].getFactor()==otherGroup.multiplicationGroups[i1].getFactor()) { // They cancel out if they have equal factors.
					strictlyEqualParts++;
				}
				else {
					combinableUnequalParts++;
					if(multiplicationGroups[i0].getFactor() > otherGroup.multiplicationGroups[i1].getFactor()) {
						combinableUnequalPartsThisMore++;
					}
					else {
						combinableUnequalPartsThatMore++;
					}
				}
				i0++;
				i1++;
			}
		}
		if((combinableUnequalPartsThisMore+novelPartsThisMore>0)&&(combinableUnequalPartsThatMore+novelPartsThatMore>0))
			return true;
		if((combinableUnequalPartsThisMore+novelPartsThisMore)==(combinableUnequalPartsThatMore+novelPartsThatMore))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retval = "";
		for(MultiplicationGroup group:multiplicationGroups) {
			if((group.getFactor()>0)&&(retval.length()>0)) {
				retval+="+";
			}
			retval+=group.toString();
		}
		if(retval.length()==0)
			retval="0";
		return retval;
	}
}
