/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * The pattern size is described as the size of a ConstantGroup plus a modifier.
 * 
 * The ConstantGroup could be infinite, or rather, of some non-specific size, but it is specific enough that it can be used to align to other Pattern sizes utilizing the same ConstantGroup's non-specific size.
 * 
 * @author scox
 *
 */
public class PatternSize {
	private ConstantGroup constantGroup;
	private int modifier;

	/**
	 * 
	 * @param constantGroup Can be null.
	 * @param modifier If constantGroup is null, then modifier must be greater than 0. Otherwise, the size must be less than or equal to 0;
	 */
	public PatternSize(ConstantGroup constantGroup, int modifier) {
		if((constantGroup==null)&&(modifier<=0))
			throw new IllegalArgumentException("The overall size of the pattern is zero or less, which is an invalid pattern size.");
		// TODO If the modifier is negative and the constantGroup is not null, then perhaps a minimum viable constantGroup size should be noted.
		// Perhaps as patterns evolve, we need to be able to split them on different cases based on supposed constantGroup sizes, in case they affect the evolution of a pattern.
		this.constantGroup = constantGroup;
		this.modifier = modifier;
	}

	/**
	 * 
	 * @return A ConstantGroup, or null.
	 */
	public ConstantGroup getConstantGroup() {
		return constantGroup;
	}
	
	/**
	 * 
	 * @return If constantGroup is null, then modifier must be greater than 0 and refers to the absolute size of the pattern.
	 *         Otherwise, the modifier represents the size of the pattern with respect to the size of the ConstantGroup.
	 */
	public int getModifier() {
		return modifier;
	}
}
