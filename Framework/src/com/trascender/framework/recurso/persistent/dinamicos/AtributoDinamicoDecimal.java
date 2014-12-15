package com.trascender.framework.recurso.persistent.dinamicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "DECIMAL")
public class AtributoDinamicoDecimal extends AtributoDinamico<Double>{
	
	private static final long serialVersionUID = 4982836497348872039L;
	
	@Column(name = "VALOR_DECIMAL")
	private Double valorDecimal;
	
	@Override
	public void setValor(Double pValor) {
		valorDecimal = pValor;
	}

	@Override
	public Double getValor() {
		return valorDecimal;
	}

	@Override
	public void setValorString(String pValor) {
		if (pValor != null && !pValor.trim().isEmpty()){
			this.valorDecimal = Double.valueOf(pValor);
		} else {
			this.valorDecimal = null;
		}
	}

	@Override
	public String getValorString() {
		return this.valorDecimal == null ? null : this.valorDecimal.toString();
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorDecimal";
	}

}
