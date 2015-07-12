/**
 * 
 * © Copyright 2015, CoDeSoft Todos los derechos reservados.
 * 
 */

package com.trascender.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TListMap<K, V> extends HashMap<K, List<V>> {

	private static final long serialVersionUID = 1935843772006198006L;

	public void add(K key, V valor) {
		List locLista = this.get(key);
		if(locLista == null) {
			locLista = new ArrayList<V>();
			this.put(key, locLista);
		}
		
		locLista.add(valor);
	}

	public static void main(String... args) {
		TListMap<Integer, String> listMap = new TListMap<Integer, String>();
		listMap.add(1, "hola");
		listMap.add(1, "chau");
		listMap.add(2, "nico");

		System.out.println(listMap.get(1)); // [hola, chau]
		System.out.println(listMap.get(1).get(0)); // hola
		System.out.println(listMap.get(2));// [nico]
	}

}