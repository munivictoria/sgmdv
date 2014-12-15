package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.io.Serializable;

import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.AlcanceValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.Operadores;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion.TipoValidacion;
import com.trascender.framework.util.Util;

public abstract class PlantillasValidaciones implements Serializable{

	private static final long serialVersionUID = -6515039168397819798L;
	
	private static ValidacionDinamica validacion;
	
	private PlantillasValidaciones(){	
	}
	
	/**
	 * Devuelve una validacion para NO_NULO para un atributo
	 * @return la validacion preparada. solo setear el nombre del atributo atributo.
	 */
	public static ComponenteValidacion VALIDACION_NO_NULO(){
		return VALIDACION_NO_NULO(null);
	}
	
	/**
	 * Devuelve una validacion para NO_NULO para un atributo pasado por parametro.
	 * @param pAtributo
	 * @return
	 */
	public static ComponenteValidacion VALIDACION_NO_NULO(String pAtributo){
		ComponenteValidacion locComponente = new ComponenteValidacion();
		
			locComponente.setAlcance(AlcanceValidacion.INTEGRIDAD);
			locComponente.setAtributo(pAtributo);
			locComponente.setDescripcion("Validacion NO NULO para el atributo: " + Util.capitalize(pAtributo));
			locComponente.setOperador(Operadores.IGUAL);
			locComponente.setTipoValidacion(TipoValidacion.NO_NULO);
		
		return locComponente;
	}
	
	/**
	 * Devuelve una validacion para NO_NULO para un atributo
	 * @return la validacion preparada. solo setear el nombre del atributo atributo.
	 */
	public static ComponenteValidacion VALIDACION_UNICO(){
		return VALIDACION_UNICO(null);
	}
	
	/**
	 * Devuelve una validacion para NO_NULO para un atributo pasado por parametro.
	 * @param pAtributo
	 * @return
	 */
	public static ComponenteValidacion VALIDACION_UNICO(String pAtributo){
		ComponenteValidacion locComponente = new ComponenteValidacion();
		
			locComponente.setAlcance(AlcanceValidacion.VALIDACION_DB);
			locComponente.setAtributo(pAtributo);
			locComponente.setDescripcion("Validacion UNICIDAD para el atributo: " + ((pAtributo != null)?Util.capitalize(pAtributo):"Setee el atributo primero."));
			locComponente.setOperador(Operadores.IGUAL);
			locComponente.setTipoValidacion(TipoValidacion.UNICO);
		
		return locComponente;
	}
	
	/**
	 * Devuelve una validacion Basica para un atributo pasado por parametro.
	 * @param pAtributo
	 * @return
	 */
	public static ComponenteValidacion VALIDACION_BASICA(AlcanceValidacion pAlcance,
													String pAtributo, 
													ValorValidacion pValor, 
													Operadores pOperador, 
													TipoValidacion pConjuncionValidacion){
		ComponenteValidacion locComponente = new ComponenteValidacion();
		
			locComponente.setAlcance(pAlcance);
			locComponente.setAtributo(pAtributo);
			locComponente.setDescripcion("Ingrese una descripcion aqui.");
			locComponente.setOperador(pOperador);
			locComponente.setTipoValidacion(pConjuncionValidacion);
			locComponente.setValor(pValor);
		
		return locComponente;
	}
	

}
