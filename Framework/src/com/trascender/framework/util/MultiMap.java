package com.trascender.framework.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * "Mapa" con 2 llaves.
 * @author fgareis
 *
 * @param K1 Tipo de la llave 1
 * @param K2 de la llave 2
 * @param V Valor
 */
public class MultiMap<K1, K2, V> {

	private HashMap<K1, HashMap<K2, V>> mapaDeMapas = new HashMap<K1, HashMap<K2, V>>();
	
	public void put(K1 key1, K2 key2, V value){
		HashMap<K2, V> locMapaKey2 = mapaDeMapas.get(key1);
		if (locMapaKey2 == null) locMapaKey2 = new HashMap<K2, V>();
		locMapaKey2.put(key2, value);
		mapaDeMapas.put(key1, locMapaKey2);
	}
	
	public V get(K1 key1, K2 key2){
		HashMap<K2, V> locMapaKey2 = mapaDeMapas.get(key1);
		if (locMapaKey2 == null) return null;
		return locMapaKey2.get(key2);
	}
	
	public void remove(K1 key1, K2 key2){
		HashMap<K2, V> locMapaKey2 = mapaDeMapas.get(key1);
		if (locMapaKey2 != null){
			locMapaKey2.remove(key2);
		}
	}
	
	public void removeKey1(K1 key1){
		mapaDeMapas.remove(key1);
	}
	
	public Set<K1> keySet1(){
		return this.mapaDeMapas.keySet();
	}
	
	public Set<K2> keySet2(K1 k1){
		return this.mapaDeMapas.get(k1).keySet();
	}
	
	public Set<V> values(){
		HashSet<V> values = new HashSet<V>();
		for (K1 k1 : this.keySet1()){
			values.addAll(this.mapaDeMapas.get(k1).values());
		}
		return values;
	}
	
}
