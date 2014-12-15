package com.trascender.framework.recurso.persistent.dinamicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BOOLEANO")
public class AtributoDinamicoBooleano extends AtributoDinamico<Boolean>{
	
	private static final long serialVersionUID = -4273262854116062584L;
	
	@Column(name = "valor_booleano")
	private Boolean valorBooleano;

	@Override
	public Boolean getValor() {
		return valorBooleano;
	}

	@Override
	public void setValor(Boolean pValor) {
		this.valorBooleano = pValor;
	}

	@Override
	public void setValorString(String pValor) throws Exception {
		if (pValor == null || pValor.isEmpty()){
			this.valorBooleano = null;
		} else {
			this.valorBooleano = pValor.equals("Si");
		}
	}

	@Override
	public String getValorString() {
		return this.valorBooleano == null ? null : (this.valorBooleano ? "Si" : "No");
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorBooleano";
	}

}
