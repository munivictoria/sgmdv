package com.trascender.saic.recurso.persistent;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STRING")
public class ParametroValuadoAlicuotaString extends ParametroValuadoAlicuota{
	private static final long serialVersionUID = -899733505811047908L;

	@Basic(optional = false)
	@Column(name = "VALOR_PARAMETRO_STRING")
	private String valorParametro;

	@Override
	public Object getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String pValorParametro){
		this.valorParametro = pValorParametro;
	}

}
