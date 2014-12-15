package com.trascender.habilitaciones.util;

/**
 * @author jsantacruz
 */
public abstract class TransitoUtils {
	
	/**
	 * Valida que una patente sea correcta en formato XXX-999
	 * Admite tambien XXX999 preferentemente no usarlo
	 * @param pPatente
	 * @return
	 */
	public static boolean validarPatente(String pPatente){
		pPatente = pPatente.trim().replaceAll("-", "");
			if(pPatente.length() != 6){
				return false;
			}
			
			
			char[] listaCaracteresPatente = pPatente.toCharArray();
			
			int flagLetraNumero = 0;
			for (Character cadaCaracter : listaCaracteresPatente) {
				
				if(flagLetraNumero < 3){
					if(!Character.isLetter(cadaCaracter)){
						return false;
					}
				}else {
					if(!Character.isDigit(cadaCaracter)){
						return false;
					}
				}
				flagLetraNumero++;
			}
			
		return true;
	}
	
	/**
	 * Valida que el formato de la patente sea XXX-999
	 * @param pPatente
	 * @return
	 */
	public static boolean validarFormatoPatente(String pPatente){
		if(validarPatente(pPatente)){
			if(pPatente.length() == 6){
				return false;
			}
			
			if((pPatente.length() == 7) && (pPatente.charAt(3) == '-')){
				return true;
			}
		}else {
			return false;
		}
		return false;
	}
}
