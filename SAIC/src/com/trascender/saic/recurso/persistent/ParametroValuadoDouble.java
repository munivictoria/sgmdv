package com.trascender.saic.recurso.persistent;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DOUBLE")
public class ParametroValuadoDouble extends ParametroValuado {

	private static final long serialVersionUID = 7710875606328662156L;
	
	@Basic(optional = false)
	@Column(name = "VALOR_PARAMETRO_DOUBLE")
	private Double valorParametro;

	@Override
	public Double getValorParametro() {
		return this.valorParametro;
	}
	
	public void setValorParametro(Double pValorParametro) {
		this.valorParametro = pValorParametro;
	}

}
