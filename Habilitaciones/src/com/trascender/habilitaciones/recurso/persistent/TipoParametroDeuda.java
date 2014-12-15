package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@DiscriminatorValue(value = "PARAMETRO_DEUDA")
public class TipoParametroDeuda extends TipoParametro{

	public static final long serialVersionUID = 5559838864367129427L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoDeuda atributoDeuda;
	
	
	public enum AtributoDeuda{
		ES_ALGUN_PROPIETARIO_MOROSO_TGI, ES_MOROSO_SHPS, ES_ALGUN_PROPIETARIO_MOROSO_PFO, BUEN_CONTRIBUYENTE,
		ES_ALGUN_PROPIETARIO_MOROSO_OSP, ES_MOROSO_OSP, ES_MOROSO_CEMENTERIO, ES_MOROSO_AUTOMOTOR;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	public AtributoDeuda getAtributoCementerio() {
		return atributoDeuda;
	}

	public void setAtributoCementerio(AtributoDeuda atributoDeuda) {
		this.atributoDeuda = atributoDeuda;
	}

	@Override
	public Object getValor(
			DocHabilitanteEspecializado pDocHabilitanteEspecializado) throws Exception {
		
		switch(atributoDeuda){
		case ES_ALGUN_PROPIETARIO_MOROSO_OSP: return 0d;
		case ES_MOROSO_OSP: return 0d;
		case ES_MOROSO_CEMENTERIO: return 0d;
		//Por defecto todos son buen contribuyente
		case BUEN_CONTRIBUYENTE: return 1d;
		case ES_ALGUN_PROPIETARIO_MOROSO_PFO: return 0d;
		case ES_MOROSO_SHPS: return 0d;
		case ES_ALGUN_PROPIETARIO_MOROSO_TGI: return 0d;
		case ES_MOROSO_AUTOMOTOR: return 0d;
	}
	return 0d;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoDeuda = AtributoDeuda.valueOf(pNombreAtributo);
	}
}
