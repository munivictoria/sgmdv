package com.trascender.framework.util;

import java.util.HashMap;

public class THashMap<K> extends HashMap<K, Double>{

	private static final long serialVersionUID = -5912810008163471889L;
	
	/**
	 * Si existe un valor con la clave en cuestion, lo toma y le suma lo nuevo. Si no 
	 * existiera, lo añade con put.
	 * @param key
	 * @param value
	 */
	public void add(K key, Double value){
		Double locValue = this.get(key);
		if (locValue == null){
			this.put(key, value);
		} else {
			this.put(key, locValue + value);
		}
	}

}
