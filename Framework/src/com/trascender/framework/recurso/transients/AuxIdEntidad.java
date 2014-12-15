package com.trascender.framework.recurso.transients;

import java.io.Serializable;

import com.trascender.framework.util.Util;

/**
 * 
 * @author fer
 *
 */
public class AuxIdEntidad implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4752829636759657003L;
	private long id;
	private String valor;
	
	public AuxIdEntidad(long id, String valor){
		this.id = id;
		this.valor = valor;
		if (this.valor != null) {
			this.valor = Util.capitalize(valor);
		}
	}
	
	public AuxIdEntidad(long id, String valor, boolean capitalizar){
		this.id = id;
		this.valor = valor;
		if (capitalizar && this.valor != null) {
			this.valor = Util.capitalize(valor);
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
