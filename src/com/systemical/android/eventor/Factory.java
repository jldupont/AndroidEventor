package com.systemical.android.eventor;

import java.util.HashMap;

import android.util.Log;

import com.systemical.android.system.IFactory;

public class Factory implements IFactory {

	final String TAG="Factory";
	
	@SuppressWarnings("unchecked")
	HashMap<java.lang.Class, Object> map=new HashMap<java.lang.Class, Object>();

	/**
	 * Check for an instance before building a new one
	 * Takes care of the 'singleton' type
	 */
	@SuppressWarnings("unchecked")
	public Object get(Class classe) {

		if (map.containsKey(classe)) {
			return map.get(classe);
		}
		
		try {
			return classe.newInstance();
		} catch (IllegalAccessException e) {
			Log.e(TAG, "IllegalAccessException: "+e.toString());
		} catch (InstantiationException e) {
			Log.e(TAG, "InstantiationException: "+e.toString());
		}
		return null;		
	}

	public void set(Class classe, Object obj) {
		map.put(classe, obj);
	}
	
}//
