package com.trascender.framework.recurso.persistent.dinamicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CADENA")
public class AtributoDinamicoCadena extends AtributoDinamico<String>{
	
	private static final long serialVersionUID = -4786482651323926863L;
	
	@Column(name = "VALOR_CADENA")
	private String valorCadena;
	
	public void setValor(String pValor) {
		valorCadena = pValor;
	}

	@Override
	public String getValor() {
		return valorCadena;
	}

	@Override
	public void setValorString(String pValor) {
		if (pValor != null && !pValor.trim().isEmpty()){
			this.valorCadena = pValor;
		} else {
			this.valorCadena = null;
		}
	}

	@Override
	public String getValorString() {
		return valorCadena;
	}

	@Override
	public String getNombreAtributoValor() {
		return "valorCadena";
	}

}
