package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.trascender.framework.util.Util;

/**
 * @author jsantacruz
 */
@MappedSuperclass
public abstract class TipoPlanta implements Serializable{

	public static final long serialVersionUID = -4990696782132376959L;
	
	protected TipoPlanta(){
		
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_PLANTA")
	private Tipo tipoPlanta = null;
	
	/**
	 *Tipos {URBANA,
		SUBURBANA,
		RURAL,
		SUBRURAL}
	 */
	public enum Tipo{
		URBANA,
		SUBURBANA,
		RURAL,
		SUBRURAL;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}

	public Tipo getTipoPlanta() {
		return tipoPlanta;
	}

	public void setTipoPlanta(Tipo tipoPlanta) {
		this.tipoPlanta = tipoPlanta;
	}
	
}
