package com.trascender.saic.recurso.transients;

import java.io.Serializable;

/**
 * Historial de pago por año
 * @author nela
 *
 */
public class LineaHistorialPago implements Serializable{

	private int anio;
	
	private Character[] listaEstadoPagos = new Character[12];
	
	
	private int auxUltimaPos = 0;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public Character[] getListaEstadoPagos() {
		return listaEstadoPagos;
	}
	public void setListaEstadoPagos(Character[] listaEstadoPagos) {
		this.listaEstadoPagos = listaEstadoPagos;
	}

	public void addEstado(Character pChar){
		if (this.auxUltimaPos < 12) {
			this.listaEstadoPagos[auxUltimaPos] = pChar;
			auxUltimaPos++;
		}
	}
	// la lista de estados se muestra alrevez
	public String getListaEstadosString(){
		StringBuffer locCadenaRetorno = new StringBuffer("");
		for(int i=11;i >= 0;i--){
			Character cadaChar = this.listaEstadoPagos[i];
			if(cadaChar != null){
				locCadenaRetorno.append("     "+cadaChar);
			}
		}
		return locCadenaRetorno.toString();
	}
	
	public LineaHistorialPago(int pAnio){
		this.anio = pAnio;
	}

	public LineaHistorialPago(){
		
	}
	
}
