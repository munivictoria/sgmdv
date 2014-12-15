package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

/**
 * 
 * @author nico
 * 
 * Clase "Interna" que se utiliza en la reliquidacion, para poder reliquidar con valores de parametros forzados.
 * 
 */
public class ParametroReliquidacion implements Serializable{

	private static final long serialVersionUID = 1845037318293022793L;

	private String nombreParametro;
	
	public enum TipoValor {ACTUAL, FIJO};
	
	private String valor;

	private TipoValor tipoValor = TipoValor.ACTUAL;
	
	public String getNombreParametro() {
		return nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(TipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreParametro == null) ? 0 : nombreParametro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ParametroReliquidacion other = (ParametroReliquidacion) obj;
		if(nombreParametro == null) {
			if(other.nombreParametro != null)
				return false;
		} else if(!nombreParametro.equals(other.nombreParametro))
			return false;
		return true;
	}
}
