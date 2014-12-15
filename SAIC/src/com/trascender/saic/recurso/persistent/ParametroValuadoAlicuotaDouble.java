package com.trascender.saic.recurso.persistent;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DOUBLE")
public class ParametroValuadoAlicuotaDouble extends ParametroValuadoAlicuota{
	private static final long serialVersionUID = 2057917389379653796L;

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
