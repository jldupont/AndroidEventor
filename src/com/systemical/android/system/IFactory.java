/**
 * @author jldupont
 */
package com.systemical.android.system;

public interface IFactory {

	/**
	 * Get (build) an object 
	 * Works for both singleton & multiton
	 * 
	 * @param classe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object get(java.lang.Class classe);
	
	/**
	 * Stuff a singleton in the Factory
	 * 
	 * @param classe
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void set(java.lang.Class classe, Object obj);
	
}//
