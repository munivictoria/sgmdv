package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;

@Entity
@DiscriminatorValue(value = "PARAMETRO_CEMENTERIO")
public class TipoParametroCementerio extends TipoParametro{

	public static final long serialVersionUID = 7368476320033640968L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoCementerio atributoCementerio;
	
	
	public enum AtributoCementerio{
		;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	public AtributoCementerio getAtributoCementerio() {
		return atributoCementerio;
	}

	public void setAtributoCementerio(AtributoCementerio atributoCementerio) {
		this.atributoCementerio = atributoCementerio;
	}

	@Override
	public Object getValor(
			DocHabilitanteEspecializado pDocHabilitanteEspecializado) throws Exception {
		if (pDocHabilitanteEspecializado instanceof DocumentoCementerio){
			DocumentoCementerio locDocumento = (DocumentoCementerio) pDocHabilitanteEspecializado;
			
			switch(atributoCementerio){		
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El tipo de documento especializado para los parámetros de Cementerio debe ser DocumentoCementerio");
		}
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoCementerio = AtributoCementerio.valueOf(pNombreAtributo);
	}
}
