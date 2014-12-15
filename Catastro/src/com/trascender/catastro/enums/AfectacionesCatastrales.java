package com.trascender.catastro.enums;

import java.io.Serializable;

import com.trascender.framework.util.Util;

/**
 * @author jsantacruz
 */
public abstract class AfectacionesCatastrales implements Serializable{
	
	public static final long serialVersionUID = 7950406409825884417L;

	private AfectacionesCatastrales(){}
	
	/**
	 *Tipos: ENSANCHES_Y_APERTURAS_DE_CALLES,
		TRAZAS_DE_AUTOPISTA,
		BAJADAS,
		SUBIDAS_Y_COLECTORAS;
	 */
	public enum Explicitas{
		ENSANCHES_Y_APERTURAS_DE_CALLES,
		TRAZAS_DE_AUTOPISTA,
		BAJADAS,
		SUBIDAS_Y_COLECTORAS;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 *Tipos: EXPROPIACIONES_POR_PLAZA,
		EXPROPIACIONES_POR_ESCUELA;
	 */
	public enum NoExplicitas{
		EXPROPIACIONES_POR_PLAZA,
		EXPROPIACIONES_POR_ESCUELA;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 *Tipos: CORREDOR_AEREO,
		CUNTURON_DIGITAL,
		FRANJA_NO_EDIFICABLE_DE_AUTOPISTA;
	 */
	public enum RestriccionesDominioExplicitas{
		CORREDOR_AEREO,
		CINTURON_DIGITAL,
		FRANJA_NO_EDIFICABLE_DE_AUTOPISTA;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 *Tipos: PROHIBICION_DE_FRACCIONAR,
		PROHIBICION_DE_UNIFICAR;
	 */
	public enum RestriccionesDominioNoExplicitas{
		PROHIBICION_DE_FRACCIONAR,
		PROHIBICION_DE_UNIFICAR;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
	}
		
	}
}
