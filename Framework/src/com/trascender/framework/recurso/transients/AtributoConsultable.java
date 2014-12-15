package com.trascender.framework.recurso.transients;

import java.io.Serializable;

import com.trascender.framework.util.Util;

public class AtributoConsultable implements Serializable {
	
	private static final long serialVersionUID = -5777126896302341110L;
	
	private String nombreInterno;
	private String nombreExterno;
	private Tipo tipo = Tipo.TEXTO;
	private Integer anchoColumna = 0; 
	
	public enum Tipo{
		TEXTO, TEXTO_LARGO, FECHA, FECHA_HORA, MONTO, BOOLEANO;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	public AtributoConsultable(String nombreInterno, String nombreExterno, Tipo pTipo){
		this.nombreExterno = nombreExterno;
		this.nombreInterno = nombreInterno;
		this.tipo = pTipo;
	}
	
	public AtributoConsultable(String nombreInterno, String nombreExterno, Tipo pTipo, Integer pAncho){
		this.nombreExterno = nombreExterno;
		this.nombreInterno = nombreInterno;
		this.tipo = pTipo;
		this.anchoColumna = pAncho;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo pTipo) {
		tipo = pTipo;
	}
	
	public String getNombreInterno() {
		return nombreInterno;
	}
	
	public void setNombreInterno(String pNombreInterno) {
		nombreInterno = pNombreInterno;
	}
	
	public String getNombreExterno() {
		return nombreExterno;
	}
	
	public void setNombreExterno(String pNombreExterno) {
		nombreExterno = pNombreExterno;
	}

	public Integer getAnchoColumna() {
		return anchoColumna;
	}

	public void setAnchoColumna(Integer pAnchoColumna) {
		anchoColumna = pAnchoColumna;
	}
	
}