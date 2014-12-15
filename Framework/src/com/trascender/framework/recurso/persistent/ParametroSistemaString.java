package com.trascender.framework.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CADENA")
public class ParametroSistemaString extends ParametroSistema{

/**
	 * 
	 */
	private static final long serialVersionUID = 559003644256066813L;
@Column(name = "CADENA", nullable = false)
private String cadena;

	@Override
	public String toString() {
		return "parametroString [cadena=" + cadena + ", idParametro="
				+ idParametroSistema + "]";
	}
	
	
	public String getCadena() {
		return cadena;
	}

	public void setCadena(String pCadena) {
		cadena = pCadena;
	}

	@Override
	public Object getValor() {
		return cadena;
		
	}

}
