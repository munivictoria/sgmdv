package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.trascender.framework.exception.TrascenderException;

@Entity
@DiscriminatorValue(value = "PFO")
public class TipoParametroPFO extends TipoParametro {

	
	public enum TipoAtributoPFO{
		;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = 995482769638831074L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributoPFO atributoPFO;
	
	public TipoAtributoPFO getAtributoPFO() {
		return atributoPFO;
	}
	public void setAtributoPFO(TipoAtributoPFO atributoPFO) {
		this.atributoPFO = atributoPFO;
	}
	@Override
	public Double getValor(
			DocHabilitanteEspecializado pDocHabilitanteEspecializado)
			throws TrascenderException {
		
		
		switch(atributoPFO){		
		}
		return 0d;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoPFO = TipoAtributoPFO.valueOf(pNombreAtributo);
	}
}
