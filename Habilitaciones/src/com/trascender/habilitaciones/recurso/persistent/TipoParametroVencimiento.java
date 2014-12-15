package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.exception.TrascenderException;

@Entity
@DiscriminatorValue(value = "VENCIMIENTO")
public class TipoParametroVencimiento extends TipoParametro {


	public enum TipoAtributoVencimiento{
		IMPORTE_PRIMER_VENCIMIENTO,
		MESES_DESDE_INICIO_PERIODO,
		DIAS_DESDE_INICIO_PERIODO,
		MESES_DESDE_PRIMER_VENCIMIENTO,
		DIAS_DESDE_PRIMER_VENCIMIENTO;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
		public String getNombreVariable(){
			return com.trascender.framework.util.Util.getEnumNameFromString(this.toString());
		}
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = 2383425353616252158L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private TipoAtributoVencimiento atributoVencimiento;
	
	public TipoAtributoVencimiento getAtributoVencimiento() {
		return atributoVencimiento;
	}
	public void setAtributoVencimiento(TipoAtributoVencimiento atributoVencimiento) {
		this.atributoVencimiento = atributoVencimiento;
	}
	
	
	@Override
	public Double getValor(DocHabilitanteEspecializado docHabilitanteEspecializado)	throws TrascenderException {
		//ESTE NO ME SIRVE
		return null;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoVencimiento = TipoAtributoVencimiento.valueOf(pNombreAtributo);
	}
}
