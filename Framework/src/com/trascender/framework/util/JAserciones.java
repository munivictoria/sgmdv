package com.trascender.framework.util;

import java.util.Collection;

import org.junit.Assert;
/**
 * Clase con aserciones personalizadas para hacer los test mas ordenados y faciles de entender.
 * @author jsantacruz
 *
 */
public class JAserciones extends Assert{
	
	/**
	 * @param pLista
	 * @return True si esta vacia, False si tiene datos
	 * @throws Exception
	 */
	public static boolean assertIsEmpty(Collection<?> pLista) throws Exception{
		return assertIsEmpty("Lista Vacia", pLista);
	}
	
	/**
	 * @param pMessage 
	 * @param pLista
	 * @return True si esta vacia, False si tiene datos
	 * @throws Exception
	 */
	public static boolean assertIsEmpty(String pMessage, Collection<?> pLista) throws Exception{
		if(pLista.isEmpty()){
			throw new AssertionError(pMessage);
		}
		
		return pLista.isEmpty();
	}
	
	/**
	 * Hace una salida por consola de todos los elementos de una lista. 
	 * @param pClass.
	 * @param pCollection
	 * @throws Exception 
	 */
	public static void mostrarLista(Class<?> pClass, Collection<?> pCollection) throws Exception{
		assertIsEmpty(pCollection);
		for(Object cadaObject : pCollection){
			try{
			System.out.println( ((pClass != null)?pClass.cast(cadaObject) : cadaObject));
			}catch (ClassCastException ex) {
				System.out.println("No se puede castear -["+ cadaObject+"] - a ["+ pClass.getSimpleName()+"]");
			}catch (Exception e) {
				e.printStackTrace();
				fail("Excepcion al mostrar lista");
			}
		}
	}
	
	/**
	 * Hace una salida por consola de todos los elementos de una lista. 
	 * @param pCollection
	 * @throws Exception 
	 */
	public static void mostrarLista(Collection<?> pCollection) throws Exception{
		mostrarLista(null, pCollection);
	}
	
	
}