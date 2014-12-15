package com.trascender.catastro.enums;

import java.io.Serializable;

import com.trascender.framework.util.Util;
/**
 * @author jsantacruz
 */
public abstract class ClasificacionCatSegunSuUso implements Serializable{
	
	public static final long serialVersionUID = -7230565151927256564L;

	/**
	 *Tipos:		
	 	DENSIDAD_ALTA,
		DENSIDAD_MEDIA,
		DENSIDAD_MEDIA_ARQ_ESPECIAL,
		DENSIDAD_BAJA,
		DENSIDAD_BAJA_BARRIO_JARDIN,
		DENSIDAD_BAJA_ZONA_COSTA,
		DENSIDAD_BAJA_ZONA_INUNDABLE,
		ESPECIAL_PLANES_VIVIENDAS;
	 */
	public enum  Residencial {
		DENSIDAD_ALTA,
		DENSIDAD_MEDIA,
		DENSIDAD_MEDIA_ARQ_ESPECIAL,
		DENSIDAD_BAJA,
		DENSIDAD_BAJA_BARRIO_JARDIN,
		DENSIDAD_BAJA_ZONA_COSTA,
		DENSIDAD_BAJA_ZONA_INUNDABLE,
		ESPECIAL_PLANES_VIVIENDAS;
		
		@Override
		public String toString() {
			switch (Residencial.valueOf(this.name())) {
				case DENSIDAD_MEDIA: return "Densidad Media (Arquitectura Especial)"; 
				case DENSIDAD_BAJA_BARRIO_JARDIN: return "Densidad Baja (Barrio Jardin)";
				case DENSIDAD_BAJA_ZONA_COSTA: return "Densidad Baja (Zona Costa)";
				case DENSIDAD_BAJA_ZONA_INUNDABLE: return "Densidad Baja (Zona Inundable)";
				case ESPECIAL_PLANES_VIVIENDAS: return "Especial (Planes Viviendas)";
				default: return com.trascender.framework.util.Util.capitalizeEnumName(this.name());
			}
		}
	}
	
	/**
	 *Tipos: CENTRAL, CENTRAL_COMPLEMENTARIA, VECINAL;
	 */
	public enum Comercial{
		CENTRAL,
		CENTRAL_COMPLEMENTARIA,
		VECINAL;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 *Tipos: GENERAL,ESPECIAL;
	 */
	public enum Equipamiento{
		GENERAL,
		ESPECIAL;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 * Tipos: USO_INTENSIVO,ANEGADIZO;
	 */
	public enum Rural{
		USO_INTENSIVO,
		ANEGADIZO;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	/**
	 *Tipos: URBANIZACION_FUTURA,
		URBANO_PARQUE,
		URBANO_RECREATIVO,
		ZONA_DE_SEGURIDAD,
		ZONA_DE_RIESGO,
		CIUDAD_PUERTO,
		PARQUE_TECNOLOGICO
	 */
	public enum Varios{
		URBANIZACION_FUTURA,
		URBANO_PARQUE,
		URBANO_RECREATIVO,
		ZONA_DE_SEGURIDAD,
		ZONA_DE_RIESGO,
		CIUDAD_PUERTO,
		PARQUE_TECNOLOGICO;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

}
