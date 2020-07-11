/**
 * 
 */
package com.shtick.math.lychrel.pattern;

/**
 * @author scox
 *
 */
public abstract class Constant implements Comparable<Constant>{
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Constant o) {
		int retval = this.getClass().getCanonicalName().compareTo(o.getClass().getCanonicalName());
		if(retval != 0)
			return retval;
		return compareDetail(o);
	}
	
	/**
	 * 
	 * @param c Must be of the exact same class as the instance compareDetail is called on.
	 * @return 
	 */
	public abstract int compareDetail(Constant c);

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Constant))
			return false;
		Constant c = (Constant)obj;
		return compareTo(c)==0;
	}
}
