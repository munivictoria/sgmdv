package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("SIMPLE")
public class VariableFormulaSimple extends VariableFormula{
	
	@Transient
	private Double valor;
	
	@Column(name = "VARIABLE_DE_ALICUOTA")
	private boolean variableDeAlicuota = false;
	
	public boolean isVariableDeAlicuota() {
		return variableDeAlicuota;
	}

	public void setVariableDeAlicuota(boolean variableDeAlicuota) {
		this.variableDeAlicuota = variableDeAlicuota;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
